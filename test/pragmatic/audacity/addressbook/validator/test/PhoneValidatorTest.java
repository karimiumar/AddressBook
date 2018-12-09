/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.validator.test;

import pragmatic.audacity.addressbook.domain.classes.Phone;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pragmatic.audacity.addressbook.validator.*;
import pragmatic.audacity.addressbook.validator.errors.PhoneErrorMessage;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.ValidationErrorGenerator;
import pragmatic.audacity.validator.ValidationStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author
 */
public class PhoneValidatorTest {
	private ErrorMessageService errorMessageService = new PhoneErrorMessage();
	private String regex = "\\+\\d{2}-\\d{4}-\\d{6}";
    private final ValidationErrorGenerator generator = new ValidationErrorGenerator(regex);
    private final ValidationStrategy<Phone> strategy = new PhoneValidator(generator, errorMessageService,regex);
    
	@DataProvider
    private Object [][] validPhones() {
        return new Object[][] {
          new Phone [] { new Phone(new Random().nextLong(), "+91-5942-235083", true, new Date())},
          new Phone [] { new Phone(new Random().nextLong(), "+91-1242-347832", true, new Date())}
        };
    }
    
	@DataProvider
    private Object [][] inValidPhones() {
        return new Object[][] {
          new Phone [] { new Phone(new Random().nextLong(), "+1-5942-235062", true, new Date())},
          new Phone [] { new Phone(new Random().nextLong(), "+911-242-347832", true, new Date())},
          new Phone [] { new Phone(new Random().nextLong(), "+911-242-34783", true, new Date())},
          new Phone [] { new Phone(new Random().nextLong(), "+911-242347832", true, new Date())},
          new Phone [] { new Phone(new Random().nextLong(), "+91-242-6347832", false, new Date())},
        };
    }
    
    @Test(dataProvider = "validPhones")
    public void areValidPhones(Phone phone) {
        Collection<ValidationError> errors = strategy.validate(phone, new ArrayList<>());
        Assert.assertTrue(errors.isEmpty());
    }
    
    @Test(dataProvider = "inValidPhones")
    public void areInvalidPhones(Phone phone) {
        Collection<ValidationError> errors = strategy.validate(phone, new ArrayList<>());
        Assert.assertFalse(errors.isEmpty());
        errors.forEach(System.out::println);
    }
}
