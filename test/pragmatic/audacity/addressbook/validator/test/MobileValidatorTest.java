/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.validator.test;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pragmatic.audacity.addressbook.validator.*;
import pragmatic.audacity.addressbook.validator.errors.MobileErrorMessage;
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
public class MobileValidatorTest {
    private ErrorMessageService errorMessageService = new MobileErrorMessage();
    private String regex = "\\+\\d{2}-\\d{10}";
    private final ValidationErrorGenerator generator = new ValidationErrorGenerator(regex);
    private final ValidationStrategy<Mobile> strategy = new MobileValidator(generator, errorMessageService, regex);
    
    @DataProvider
    private Object [][] validMobiles() {
        return new Object[][] {
          new Mobile[] { new Mobile(new Random().nextLong(), "+91-5942239092", true, new Date())},
          new Mobile [] { new Mobile(new Random().nextLong(), "+91-1242347832", false, new Date())}
        };
    }
    
    @DataProvider
    private Object [][] inValidMobiles() {
        return new Object[][] {
          new Mobile [] { new Mobile(new Random().nextLong(), "+1-5942-239092", false, new Date())},
          new Mobile [] { new Mobile(new Random().nextLong(), "+911-242-347832", false, new Date())},
          new Mobile [] { new Mobile(new Random().nextLong(), "+911-242-34783", false, new Date())},
          new Mobile [] { new Mobile(new Random().nextLong(), "+911-242347832", false, new Date())},
          new Mobile [] { new Mobile(new Random().nextLong(), "+91-242-6347832", true, new Date())},
        };
    }
    
    @Test(dataProvider = "validMobiles")
    public void areValidPhones(Mobile mobile) {
        Collection<ValidationError> errors = strategy.validate(mobile, new ArrayList<>());
        Assert.assertTrue(errors.isEmpty());
    }
    
    @Test(dataProvider = "inValidMobiles")
    public void areInvalidPhones(Mobile mobile) {
        Collection<ValidationError> errors = strategy.validate(mobile, new ArrayList<>());
        Assert.assertFalse(errors.isEmpty());
        errors.forEach(System.out::println);
    }
}
