package pragmatic.audacity.addressbook.domain.test;

import pragmatic.audacity.addressbook.domain.classes.Address;
import pragmatic.audacity.addressbook.domain.classes.AddressType;
import pragmatic.audacity.addressbook.domain.classes.City;
import pragmatic.audacity.addressbook.domain.classes.Country;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Date;

public class AddressTest {

	final Country country = new Country("China", "CH", new Date());
	final City shanghai = new City(country, "Shanghai", "Zitsu Province", "ZI-567", new Date());
    final City beijing = new City(country, "Beijing", "Dakota Province", "DA-567", new Date());
	final Date createdOn = new Date();
	final Address address1 = new Address("1234", "Maryln Street", shanghai, true, createdOn, AddressType.PERMANENT);
	final Address address2 = new Address("1234", "Maryln Street", shanghai, true, createdOn, AddressType.PERMANENT);
	final Address address3 = new Address("1234", "Maryln Street", beijing, false, null, AddressType.PERMANENT);
	
	@Test
	public void addressesAreEqual() {
		Assert.assertTrue(address1.equals(address2));
	}
	
	@Test
	public void addressesAreNotEqual() {
		Assert.assertFalse(address2.equals(address3));
		Assert.assertFalse(address1.equals(address3));
	}

    @Test
    public void citiesBelongToChina() {
        final City [] cities = {beijing, shanghai};
        Assert.assertTrue(country.getCities().containsAll(Arrays.asList(cities)));
    }

    @Test
    public void citiesDontBelongToChina() {
        Country country = new Country("United States", "US", new Date());
        City newJersey = new City(country, "New Jersey", "Jersey Province", "JA-567", new Date());
        final City [] cities = {newJersey};
        Assert.assertFalse(country.getCities().containsAll(Arrays.asList(cities)));
    }
}

class A {

    int returns5() {
        return 5;
    }

    int returns6() {
        return returns5() + 1;
    }

    int returns7() {
        return returns6() + 1;
    }
}

