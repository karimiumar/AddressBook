package pragmatic.audacity.addressbook.persistence.jdbc;

import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class JdbcPersistenceServiceImpl<T> implements IJdbcPersistenceService<T> {

    private final JdbcConnectionManager connectionManager;

    public JdbcPersistenceServiceImpl(JdbcConnectionManager connectionManager, String schema) {
        this.connectionManager = connectionManager;
    }

    protected final Connection getConnection() {
        return connectionManager.getConnection();
    }

    protected PreparedStatement prepareStatement(String sql, Object ... params) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            setParameters(ps, params);
            return  ps;
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while preparing statement ", e);
        }
    }

    protected ResultSet getResults(PreparedStatement ps) {
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while getting ResultSet ", e);
        }
    }

    protected List<T> convertResultSet(PreparedStatement ps, ResultSet rs, RowMapper<T> rowMapper) {
        final List<T> result = new ArrayList<>();
        if(null != rs) {
            try {
                while (rs.next()){
                    result.add(rowMapper.map(rs));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Exception occurred while iterating ResultSet ", e);
            } finally {
                close(ps, rs);
            }
        }
        return result;
    }


    public void close(final PreparedStatement ps, final ResultSet rs) {
        try {
            if (null != rs && !(rs.isClosed()))
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Exception occurred while closing ResultSet: " + rs, e);
                }
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred. ResultSet is null or closed " + rs, e);
        }

        try {
            if (null != ps && !(ps.isClosed()))
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Exception occurred while closing PreparedStatement: " + rs, e);
                }
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred. PreparedStatement is null or closed " + ps, e);
        }
    }

    @Override
    public List<T> select(String sql, RowMapper<T> rowMapper, Object... params) {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        final List<T> result = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            setParameters(ps, params);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rowMapper.map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred ", e);
        } finally {
            close(ps, rs);
        }
        return result;
    }

    public int delete(String sql, Object ... params) {
        return save(sql, params);
    }

    @Override
    public int save(String sql, Object... params) {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = connection.prepareStatement(sql);
            setParameters(ps, params);
            result = ps.executeUpdate();
            connectionManager.commit();
        }catch (SQLException e) {
            connectionManager.rollback();
            throw new RuntimeException("Exception occurred ", e);
        } finally {
            close(ps, null);
        }
        return result;
    }

    private void setParameters(final PreparedStatement ps, final Object... params) throws SQLException {
        for (int i = 0, length = params.length; i < length; i++) {
            final Object param = params[i];
            final int paramIndex = i + 1;
            if (null == param) {
                ps.setObject(paramIndex, null);
            } else if (param instanceof Boolean) {
                ps.setBoolean(paramIndex, (Boolean) param);
            } else if (param instanceof Character) {
                ps.setString(paramIndex, String.valueOf(param));
            } else if (param instanceof Byte) {
                ps.setByte(paramIndex, (Byte) param);
            } else if (param instanceof Short) {
                ps.setShort(paramIndex, (Short) param);
            } else if (param instanceof Integer) {
                ps.setInt(paramIndex, (Integer) param);
            } else if (param instanceof Long) {
                ps.setLong(paramIndex, (Long) param);
            } else if (param instanceof Float) {
                ps.setFloat(paramIndex, (Float) param);
            } else if (param instanceof Double) {
                ps.setDouble(paramIndex, (Double) param);
            } else if (param instanceof String) {
                ps.setString(paramIndex, (String) param);
            } else if (param instanceof Date) {
                Date other = (Date)param;
                Timestamp ts = new Timestamp(other.getTime());
                ps.setTimestamp(paramIndex, new Timestamp(ts.getTime()));
            } else if (param instanceof Calendar) {
                ps.setDate(paramIndex, new
                        java.sql.Date(((Calendar) param).getTimeInMillis()));
            } else if (param instanceof BigDecimal) {
                ps.setBigDecimal(paramIndex, (BigDecimal)
                        param);
            } else {
                throw new IllegalArgumentException(String.format
                        ("Unknown type of the param is found. [param: %s, paramIndex:%s]", param, paramIndex));
            }
        }
    }

}

