package pragmatic.audacity.addressbook.validator.test;

import pragmatic.audacity.addressbook.domain.classes.AddressBase;
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

public class AddressBaseValidatorTest {

	private final Date createdOn = new Date();
	private ErrorMessageService<Field> errorService = new FieldValidationErrorService();
	private final ValidationStrategy<AddressBase> strategy = new AddressBaseValidator(errorService);
	
	@DataProvider
	public Object[][] validBase() {
		AddressBase base = new AddressBase();
		base.setId(new Random().nextLong());
		base.setVersion(2);
		base.setCreatedOn(createdOn);
		AddressBase base2 = new AddressBase();
		base2.setId(2L);
		base2.setVersion(2);
		base2.setCreatedOn(createdOn);
		return new Object[][] {
			new AddressBase[] {base},
			new AddressBase[] {base2},
		};
	}
	
	@DataProvider
	public Object[][] inValidBase() {
		AddressBase base = new AddressBase();
		base.setId(null);
		base.setVersion(2);
		AddressBase base2 = new AddressBase();
		base2.setId(2L);
		base2.setVersion(2);
		return new Object[][] {
			new AddressBase[] {base},
			new AddressBase[] {base2},
		};
	}
	
	
	@Test(dataProvider="validBase") 
	public void areValidBases(AddressBase base) {
		Collection<ValidationError> errors = strategy.validate(base, new ArrayList<>());
		Assert.assertTrue(errors.isEmpty());
	}
	
	@Test(dataProvider="inValidBase") 
	public void areInvalidBases(AddressBase base) {
		Collection<ValidationError> errors = strategy.validate(base, new ArrayList<>());
		Assert.assertFalse(errors.isEmpty());
		errors.forEach(System.out::println);
	}
}
