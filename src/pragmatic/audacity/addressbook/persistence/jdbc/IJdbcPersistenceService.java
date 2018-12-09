package pragmatic.audacity.addressbook.persistence.jdbc;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 3/18/14
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IJdbcPersistenceService<T> {

    List<T> select (String sql, RowMapper<T> rowMapper, Object ... params);
    int save(final String sql, final Object ... params);
    int delete(final String sql, final Object ... params);
    T find(Long id);
    T find(T t);
    List<T> getAll();
}
