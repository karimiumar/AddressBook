package pragmatic.audacity.addressbook.persistence.test.jdbc;

import pragmatic.audacity.addressbook.domain.classes.Address;
import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pragmatic.audacity.addressbook.persistence.jdbc.*;

import java.util.List;

public class AddressPersistenceTest {

    private IJdbcPersistenceService<Address> addressService;
    private IJdbcPersistenceService<pragmatic.audacity.addressbook.domain.classes.Country> countryService;
    private IJdbcPersistenceService<pragmatic.audacity.addressbook.domain.classes.City> cityService;
    private JdbcConnectionManager connectionManager;
    private String ADDRESS_INFO;
    private String CITY_INFO;
    private String COUNTRY_INFO;

    @BeforeClass
    public void setup() {
        ADDRESS_INFO = Tables.ADDRESS_INFO;
        COUNTRY_INFO = Tables.COUNTRY_INFO;
        CITY_INFO = Tables.CITY_INFO;
        connectionManager = JdbcConnectionManager.getJdbcUtil();
        addressService = new AddressPersistenceServiceImpl(connectionManager, "address");
        countryService = new CountryPersistenceServiceImpl(connectionManager, "address");
        cityService = new CityPersistenceServiceImpl(connectionManager, "address");
    }

    @AfterClass
    public void clear() {
        connectionManager.close();
        connectionManager = null;
    }

    @Test
    public void clearData() {
        String deleteAddress = "DELETE FROM " + ADDRESS_INFO;
        addressService.delete(deleteAddress);
        cityService.delete("DELETE FROM " + CITY_INFO);
        countryService.delete("DELETE FROM " + COUNTRY_INFO);
        List<Address> addresses = addressService.getAll();
        Assert.assertTrue(addresses.isEmpty());
    }
}
