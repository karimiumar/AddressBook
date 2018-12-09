package pragmatic.audacity.addressbook.validator.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pragmatic.audacity.addressbook.domain.classes.*;
import pragmatic.audacity.addressbook.validator.*;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.ValidationStrategy;
import pragmatic.audacity.validator.field.FieldValidationErrorService;

import java.lang.reflect.Field;
import java.util.*;

public class PersonValidatorTest {

	private final ErrorMessageService<Field> errorMsg = new FieldValidationErrorService();
	private final ValidationStrategy<Person> strategy = new PersonValidator(errorMsg);
	private Country country = new Country("China", "CH", new Date());
	private City city = new City(country, "Shanghai", "Zitsu Province", "ZI-567", new Date());
	private final Date createdOn = new Date();
	
	@DataProvider
	public Object [][] validPersons() {
		final List<Email> emails = Arrays.asList(new Email(new Random().nextLong(), "xyz@xyz.com", false, new Date()));
		final List<Mobile> mobiles = Arrays.asList(new Mobile(new Random().nextLong(), "+91-5942239092", true, new Date()));
		final List<Address> addresses = Arrays.asList(new Address(new Random().nextLong(), "114", "Water Gardens Sq", city, true, new Date(), AddressType.OTHER));
		return new Object[][] {
				new Person [] {new Person(new Random().nextLong(), "Rachel", "Jarrolt", "", new Date(), mobiles, new ArrayList<>(), addresses, new ArrayList<>(), emails, createdOn)},
				new Person [] {new Person(new Random().nextLong(), "Stacey", "Jarrolt", "", Calendar.getInstance().getTime(), mobiles, new ArrayList<>(), addresses, new ArrayList<>(), emails,  createdOn)},
				new Person [] {new Person(new Random().nextLong(), "Tracy", "Jarrolt", "T.", Calendar.getInstance().getTime(), mobiles, new ArrayList<>(), addresses, new ArrayList<>(), emails, createdOn)},
		};
	}
	
	@DataProvider
	public Object [][] inValidPersons() {
		final List<Email> emails = new ArrayList<Email>() {
			{
			add(new Email(new Random().nextLong(), "xyz@xyz.com", false, new Date()));	
		}};
		return new Object[][] {
				new Person [] {new Person(new Random().nextLong(), "", "Jarrolt", "", new Date(), new ArrayList<Mobile>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), emails, createdOn)},
				new Person [] {new Person(new Random().nextLong(), "Rachel", "", "", Calendar.getInstance().getTime(), new LinkedList<Mobile>(), new ArrayList<Webpage>(), new ArrayList<Address>(), new ArrayList<Phone>(), emails, createdOn)},
				new Person [] {new Person(new Random().nextLong(), "Rachel", "Jarrolt", "T.", null, new LinkedList<Mobile>(), new ArrayList<Webpage>(), new ArrayList<Address>(), new ArrayList<Phone>(), emails, createdOn)},
		};
	}
	
	@Test(dataProvider="validPersons")
	public void areValidPersons(Person person) {
		List<String> toIgnore = Arrays.asList("webpages", "phones", "midName");
		Collection<ValidationError> errors = strategy.validate(person, toIgnore);
		Assert.assertTrue(errors.isEmpty());
	}
	
	@Test(dataProvider="inValidPersons")
	public void areInvalidPersons(Person person) {
		List<String> toIgnore = Arrays.asList("webpages", "phones", "midName");
		Collection<ValidationError> errors = strategy.validate(person, toIgnore);
		Assert.assertFalse(errors.isEmpty());
		errors.forEach(System.out::println);
	}
}
