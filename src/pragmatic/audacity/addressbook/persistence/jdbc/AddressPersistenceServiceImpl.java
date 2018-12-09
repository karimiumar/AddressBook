package pragmatic.audacity.addressbook.persistence.jdbc;

import pragmatic.audacity.addressbook.domain.classes.Address;
import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;

import java.util.List;
import java.util.Map;

public class AddressPersistenceServiceImpl extends JdbcPersistenceServiceImpl<Address> {

    public AddressPersistenceServiceImpl(JdbcConnectionManager connectionManager, String schema) {
        super(connectionManager, schema);
    }

    public Map<Person, List<Address>> findAllAddressesOf(Person person) {
        String SQL = "";
        return null;
    }

    @Override
    public Address find(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Address find(Address address) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Address> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
