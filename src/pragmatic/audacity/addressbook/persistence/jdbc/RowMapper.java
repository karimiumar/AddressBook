package pragmatic.audacity.addressbook.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 3/19/14
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RowMapper<T> {
    T map (ResultSet resultSet) throws SQLException;
}
