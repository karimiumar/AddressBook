package pragmatic.audacity.addressbook.persistence.jdbc.notused;

import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 3/18/14
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseInserter<E> extends AbstractDatabaseHandler<E> {

    public DatabaseInserter(Class<E> clazz, JdbcConnectionManager connectionProvider) {
        super(clazz, connectionProvider);
    }

    @Override
    public String createQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO").append(" ");
        sb.append(type.getSimpleName()).append(" ");
        sb.append("(");
        sb.append(getColumns(false)).append(" ");
        sb.append(")").append(" ");
        sb.append("VALUES (");
        sb.append(getColumns(true)).append(" ");
        sb.append(")");
        return sb.toString();
    }


    public void insert(List<E> list) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        connection = connectionProvider.getConnection();
        try {
            pstmt = connection.prepareStatement(query);
            for(E instance: list) {
                int i = 0;
                for(Field field: type.getDeclaredFields()){
                    PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = descriptor.getReadMethod();
                    Object value = method.invoke(instance);
                    pstmt.setObject(++i, value);
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while creating PreparedStatement", e);
        } catch (IntrospectionException e) {
            throw new RuntimeException("Exception occurred while creating PropertyDescriptor", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Exception occurred while invoking method", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Illegal Access Exception occurred while invoking method ", e);
        }
        finally {
            try{
                pstmt.close();
            }catch (SQLException e){
                throw new RuntimeException("Exception occurred while closing PreparedStatement", e);
            }
        }
    }
}
