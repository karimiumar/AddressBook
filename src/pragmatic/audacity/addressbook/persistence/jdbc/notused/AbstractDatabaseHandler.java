package pragmatic.audacity.addressbook.persistence.jdbc.notused;

import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 3/18/14
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDatabaseHandler<E> {

    /**
     * The object to persist
     */
    protected Class<E> type;

    /**
     * Provides database connection
     */
    protected JdbcConnectionManager connectionProvider;

    protected final String query;

    protected AbstractDatabaseHandler(Class<E> clazz, JdbcConnectionManager connectionProvider) {
        this.connectionProvider = connectionProvider;
        this.type = clazz;
        this.query = createQuery();
    }

    /**
     * Create the statement to create/select from database
     * @return
     */
    protected abstract String createQuery();

    protected String getColumns(boolean usePlaceHolders){
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        /**
         * Iterate over column names
         */
        for (Field field: type.getDeclaredFields()) {
            if(first) first = false;
            else sb.append(",");
            if(usePlaceHolders)
                sb.append("?");
            else
                sb.append(field.getName());
        }
        return sb.toString();
    }
}
