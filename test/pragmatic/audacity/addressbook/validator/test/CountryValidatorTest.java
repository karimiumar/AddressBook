package pragmatic.audacity.addressbook.validator.test;

import pragmatic.audacity.addressbook.domain.classes.Country;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pragmatic.audacity.addressbook.validator.*;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.ValidationStrategy;
import pragmatic.audacity.validator.field.FieldValidationErrorService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class CountryValidatorTest {

	private final Date createdOn = new Date();
	private final ErrorMessageService<Field> errorMsg = new FieldValidationErrorService();
	private final ValidationStrategy<Country> strategy = new CountryValidator(errorMsg);
	
	@DataProvider
	public Object[][] validCountries() {
		return new Object[][]{
			new Country[] {new Country(new Random().nextLong(), "SINGAPORE", "SNG", createdOn)},
			new Country[] {new Country(new Random().nextLong(), "UNITED STATES", "US", createdOn)},
			new Country[] {new Country(new Random().nextLong(), "INDIA", "IND", createdOn)},
		};
	}
	
	@DataProvider
	public Object[][] inValidCountries() {
		return new Object[][]{
			new Country[] {new Country(new Random().nextLong(), "SINGAPORE", "", createdOn)},
			new Country[] {new Country(new Random().nextLong(), "UNITED STATES", null, createdOn)},
			new Country[] {new Country(new Random().nextLong(), null, "IND", createdOn)},
		};
	}
	
	@Test(dataProvider="inValidCountries")
	public void areInvalidCountries(Country country) {
		Collection<ValidationError> errors = strategy.validate(country, new ArrayList<>());
		Assert.assertFalse(errors.isEmpty());
		errors.forEach(System.out::println);
	}
	
	@Test(dataProvider="validCountries")
	public void areValidCountries(Country country) {
		Collection<ValidationError> errors = strategy.validate(country, new ArrayList<>());
		Assert.assertTrue(errors.isEmpty());
	}
}
