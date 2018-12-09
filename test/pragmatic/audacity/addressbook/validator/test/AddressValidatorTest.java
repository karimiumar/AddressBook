package pragmatic.audacity.addressbook.validator.test;

import pragmatic.audacity.addressbook.domain.classes.Address;
import pragmatic.audacity.addressbook.domain.classes.AddressType;
import pragmatic.audacity.addressbook.domain.classes.City;
import pragmatic.audacity.addressbook.domain.classes.Country;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pragmatic.audacity.addressbook.validator.*;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.ValidationStrategy;
import pragmatic.audacity.validator.field.FieldValidationErrorService;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.*;

public class AddressValidatorTest {

	private final ErrorMessageService<Field> errorMsg = new FieldValidationErrorService();
	private final ValidationStrategy<Address> strategy = new AddressValidator(errorMsg);
	private Country country = new Country("China", "CH", new Date());
	private City city = new City(country, "Shanghai", "Zitsu Province", "ZI-567", new Date());
	private final Date createdOn = new Date();
	final Address address1 = new Address("1234", "Maryln Street", city, true, createdOn, AddressType.PERMANENT);
	final Address address2 = new Address("1234", "Maryln Street", city, true, createdOn, AddressType.PERMANENT);
	
	@DataProvider
	public Object [][] validAddress() {
		return new Object[][] {
			new Address [] {new Address(new Random().nextLong(), "1234", "abcd", city, true, createdOn, AddressType.PERMANENT)},
			new Address [] {new Address(new Random().nextLong(), "1234", "abcd", city, false, createdOn, AddressType.PERMANENT)},
		};
	}
	
	@DataProvider
	public Object [][] invalidAddress() {
		return new Object[][] {
			new Address [] {new Address(new Random().nextLong(), "", "abcd", null, false, createdOn, AddressType.OTHER)},
			new Address [] {new Address(new Random().nextLong(), "1234", "", city, false, createdOn, null)},
			new Address [] {new Address(new Random().nextLong(), "1234", "abcd", null, true, createdOn, AddressType.OTHER)},
		};
	}
	
	@Test(dataProvider="invalidAddress") 
	public void areInvalidAddress(Address address) {
		Collection<ValidationError> errors = strategy.validate(address, Collections.EMPTY_LIST);
		String errorMsg = "";
		for(ValidationError  error: errors) {
			errorMsg += "\n";
			errorMsg += error;
		}
		JOptionPane.showMessageDialog(null, "Address{Id:" + address.getId() + "} " + errorMsg, "Validation Error", JOptionPane.ERROR_MESSAGE);
		Assert.assertFalse(errors.isEmpty());
		errors.forEach(System.out::println);
	}
	
	@Test(dataProvider="validAddress") 
	public void areValidAddress(Address address) {
		Collection<ValidationError> errors = strategy.validate(address, Collections.EMPTY_LIST);
		Assert.assertTrue(errors.isEmpty());
	}
}
