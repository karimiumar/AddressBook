package pragmatic.audacity.addressbook.domain.test;

import pragmatic.audacity.addressbook.domain.classes.City;
import pragmatic.audacity.addressbook.domain.classes.Country;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User:
 * Date: 3/15/14
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class CityTest {

    @Test
    public void createCity() {
        City ohayo = new City(getCountry("US"), "Ohayo", "Ohayo", "SE-9086", new Date());
        City nainital = new City(getCountry("India"), "Nainital", "Uttrakhand", "263001", new Date());
        Assert.assertNotEquals(ohayo, nainital);
        Assert.assertNotEquals(ohayo.hashCode(), nainital.hashCode());
    }

    private Country getCountry(String name) {
        return new Country(name, name, new Date());
    }
}
