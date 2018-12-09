package pragmatic.audacity.addressbook.persistence.jdbc.notused;

import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSelector<E> extends AbstractDatabaseHandler<E> {

    public DatabaseSelector(Class<E> clazz, JdbcConnectionManager connectionProvider) {
        super(clazz, connectionProvider);
    }

    @Override
    protected String createQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").append(" ");
        sb.append(super.getColumns(false));
        sb.append("FROM").append(" ");
        sb.append(type.getSimpleName()) ;
        return sb.toString();
    }


    public List<E> select()  {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        connection = connectionProvider.getConnection();
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException("Exception occured while creating Statement or getting ResultSet", e);
        } finally {
            try{
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException("Exception occurred while closing resources", e);
            }
        }
        return createList(rs);
    }

    private List<E> createList(ResultSet rs) {
        List<E> list = new ArrayList<>();
        try{
            while (rs.next()){
                E instance = type.newInstance();
                for(Field field: type.getDeclaredFields()) {
                    Object value = rs.getObject(field.getName());
                    PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = descriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        }catch (SQLException e){
            throw new RuntimeException("Exception occurred while iterating ResultSet", e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Instantiation Exception occurred while iterating ResultSet", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Illegal Access Exception occurred while iterating ResultSet", e);
        } catch (IntrospectionException e) {
            throw new RuntimeException("Introspection Exception occurred while creating Property Descriptor", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Exception occurred while invoking method on instance ", e);
        }
        return list;
    }

}
