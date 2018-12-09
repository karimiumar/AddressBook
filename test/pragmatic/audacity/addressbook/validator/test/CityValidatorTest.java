package pragmatic.audacity.addressbook.validator.test;

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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CityValidatorTest {

	private final Date createdOn = new Date();
	private final ErrorMessageService<Field> errorMsg = new FieldValidationErrorService();
	private final ValidationStrategy<City> strategy = new CityValidator(errorMsg);
	private Country country = new Country("CHINA", "CH", createdOn);
	
	@DataProvider
	public Object[][] validCities() {
		return new Object[][]{
			new City[] {new City(new Country("CANADA", "CAD", createdOn),"ZITSU PROVINCE","SHANGHAI","SH-SE45" , createdOn)},
			new City[] {new City(new Country("UNITED STATES", "US", createdOn),"ZITSU PROVINCE","Seattle","TK-SE45" , new Date())},
		};
	}
	
	@DataProvider
	public Object[][] inValidCities() {
		return new Object[][]{
			new City[] {new City(null,"ZITSU PROVINCE","SHANGHAI","SH-SE45" , createdOn)},
			new City[] {new City(country,"","SHANGHAI","SH-SE45" , null)},
			new City[] {new City(new Country("Japan", "JPY", createdOn),"ZITSU PROVINCE","","SH-SE45" , createdOn)},
			new City[] {new City(new Country("UNITED KINGDOM", "UK", createdOn),"","WINDSOR","" , null)},
		};
	}
	
	@Test(dataProvider="inValidCities")
	public void areInvalidCountries(City city) {
		Collection<ValidationError> errors = strategy.validate(city, new ArrayList<>());
		Assert.assertFalse(errors.isEmpty());
		errors.forEach(System.out::println);
	}
	
	@Test(dataProvider="validCities")
	public void areValidCountries(City city) {
		Collection<ValidationError> errors = strategy.validate(city, new ArrayList<>());
		Assert.assertTrue(errors.isEmpty());
	}
}
