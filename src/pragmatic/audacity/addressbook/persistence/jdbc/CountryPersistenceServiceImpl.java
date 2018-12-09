package pragmatic.audacity.addressbook.persistence.jdbc;

import pragmatic.audacity.addressbook.domain.classes.Country;
import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 3/25/14
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountryPersistenceServiceImpl extends JdbcPersistenceServiceImpl<Country>  {

    public CountryPersistenceServiceImpl(JdbcConnectionManager connectionManager, String schema) {
        super(connectionManager, schema);
    }

    @Override
    public Country find(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Country find(Country country) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Country> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
