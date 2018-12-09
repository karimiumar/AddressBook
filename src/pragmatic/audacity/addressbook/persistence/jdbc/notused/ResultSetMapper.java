package pragmatic.audacity.addressbook.persistence.jdbc.notused;

import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ResultSetMapper<T> {

    public List<T> mapResultSetToObject(ResultSet rs, Class mappingClass) {
        List<T> result = new ArrayList<>();
        if (null != rs) {
            try {
                if (mappingClass.isAnnotationPresent(Entity.class)) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    Field[] fields = mappingClass.getDeclaredFields();
                    while (rs.next()) {
                        T clazz = (T) mappingClass.newInstance();
                        for (int i = 0; i < rsmd.getColumnCount(); i++) {
                            String colName = rsmd.getColumnName(i + 1);
                            Object value = rs.getObject(i + 1);
                            assignValueToField(fields, value, clazz, colName);
                        }
                        result.add(clazz);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("SQL Exception", e);
            } catch (InstantiationException e) {
                throw new RuntimeException("Instantiation Exception", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Illegal Access Exception", e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Invocation Target Exception", e);
            }
        }
        return result;
    }

    private void assignValueToField(Field[] fields, Object value, T clazz, String colName) throws InvocationTargetException, IllegalAccessException {
        //Check if @Column annotation is present in mappingClass
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (null != value && column.name().equalsIgnoreCase(colName)) {
                    BeanUtils.setProperty(clazz, field.getName(), value);
                    break;
                }
            }
        }
    }
}
