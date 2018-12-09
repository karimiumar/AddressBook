package pragmatic.audacity.addressbook.persistence.jdbc;

import pragmatic.audacity.addressbook.domain.classes.City;
import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 3/25/14
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class CityPersistenceServiceImpl extends JdbcPersistenceServiceImpl<City> {

    public CityPersistenceServiceImpl(JdbcConnectionManager connectionManager, String schema) {
        super(connectionManager, schema);
    }

    @Override
    public City find(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public City find(City city) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<City> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
