package pragmatic.audacity.addressbook.validator.test;

import pragmatic.audacity.addressbook.domain.classes.Email;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pragmatic.audacity.addressbook.validator.*;
import pragmatic.audacity.addressbook.validator.errors.EmailErrorMessage;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.ValidationErrorGenerator;
import pragmatic.audacity.validator.ValidationStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class EmailValidatorTest {

	private String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private ValidationErrorGenerator generator = new ValidationErrorGenerator(regex);
	private ErrorMessageService errorMsgService = new EmailErrorMessage();
	private ValidationStrategy<Email> strategy = new EmailValidator(generator, errorMsgService, regex);
	
	@DataProvider
	public Object[][] validEmails(){
			return new Object[][] {
				new Email [] {new Email(new Random().nextLong(), "mkyong@yahoo.com", true, new Date())},
				new Email [] {new Email(new Random().nextLong(), "mkyong-100@yahoo.com", true, new Date())},
				new Email [] {new Email(new Random().nextLong(), "mkyong.100@yahoo.com", true, new Date())},
				new Email [] {new Email(new Random().nextLong(), "mkyong111@mkyong.com", true, new Date())},
				new Email [] {new Email(new Random().nextLong(), "mkyong-100@mkyong.net", true, new Date())},
				new Email [] {new Email(new Random().nextLong(), "mkyong.100@mkyong.com.au", true, new Date())},
				new Email [] {new Email(new Random().nextLong(), "mkyong@1.com", true, new Date())},
				new Email [] {new Email(new Random().nextLong(), "mkyong@gmail.com.com", false, new Date())},
				new Email [] {new Email(new Random().nextLong(), "mkyong+1203@gmail.com.com", false, new Date())},
				new Email [] {new Email(new Random().nextLong(), "mkyong-4556@gmail.com.com", false, new Date())},
			};
	}
	
	@DataProvider
	public Object[][] inValidEmails(){
		return new Object[][] {
			new Email [] {new Email(new Random().nextLong(), "mkyongyahoo.com", true, new Date())},
			new Email [] {new Email(new Random().nextLong(), "mkyong-100@.yahoo.com", true, new Date())},
			new Email [] {new Email(new Random().nextLong(), "mkyong.100@y.c", true, new Date())},
			new Email [] {new Email(new Random().nextLong(), "mkyong111@.com", true, new Date())},
			new Email [] {new Email(new Random().nextLong(), ".mkyong-100@mkyong.net", true, new Date())},
			new Email [] {new Email(new Random().nextLong(), "mkyong.100%@mkyong.com.au", true, new Date())},
			new Email [] {new Email(new Random().nextLong(), "mkyong..2002@1.com", true, new Date())},
			new Email [] {new Email(new Random().nextLong(), "mkyong.@gmail.com@com", false, new Date())},
			new Email [] {new Email(new Random().nextLong(), "mkyong+1203.@gmail.com.com", false, new Date())},
			new Email [] {new Email(new Random().nextLong(), "mkyong-4556@gmail.com.1a", false, new Date())},
		};
	}
	
	
	@Test(dataProvider="validEmails")
	public void areValidEmails(Email email) {
		Collection<ValidationError> errors = strategy.validate(email, new ArrayList<>());
		Assert.assertTrue(errors.isEmpty());
	}
	
	@Test(dataProvider="inValidEmails")
	public void areInValidEmails(Email email) {
		Collection<ValidationError> errors = strategy.validate(email, new ArrayList<>());
		Assert.assertFalse(errors.isEmpty());
		errors.forEach(System.out::println);
	}
}
