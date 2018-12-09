package pragmatic.audacity.addressbook.validator.test;

import pragmatic.audacity.addressbook.domain.classes.Webpage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pragmatic.audacity.addressbook.validator.*;
import pragmatic.audacity.addressbook.validator.errors.WebpageErrorMessage;
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
public class WebpageValidatorTest {
    private final String regex = "^(https?)://[(\\w+0-9)+&@#/%?=~_|!:,.;]*[(\\w+0-9)+&@#/%=~_|]";
    private ErrorMessageService errorMessageService = new WebpageErrorMessage();
    private final ValidationErrorGenerator generator = new ValidationErrorGenerator((regex));
    private final ValidationStrategy<Webpage> strategy = new WebpageValidator(generator, errorMessageService,regex);
    
    @DataProvider
    private Object[][] validPages() {
        return new Object[][] {
            new Webpage[] {new Webpage(new Random().nextLong(), "http://google/lynda.com", true, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "https://google/lynda.com", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://google/froogle/lynda.com", true, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://google.com/froogle/lynda.com", true, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://google/lynda.net", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://google/froogle/lynda.net", true, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://google/froogle/lynda.in", true, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://@##@/!!!=?.;++/lynda.net", true, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://1234.!@5456;=?/!!!=?.;++/6775.net", false, new Date())},            
        };
    }
    
    @DataProvider
    private Object[][] inValidPages() {
        return new Object[][] {
            new Webpage[] {new Webpage(new Random().nextLong(), "ftp://google/lynda.in", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "ftp://google/froogle/lynda.net", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://google/froogle/lynda.", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://1234.!@5456;=?/!!!=?.;++/****.net", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://1234.!@5456;=?/!!!=?.;++/<>^^.net", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "https://1234.!@5456;=?/!!!=?.;++/:)^^.net", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://1234.!@5456;=?/!!!=?.;++/:>.net", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://1234.!@5456;=?/!!!=?.;++/:|-.net", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://1234.!@5456;=?/!!!=?.;++/:-|.net", false, new Date())},
            new Webpage[] {new Webpage(new Random().nextLong(), "http://1234.!@5456;=?/;++/:-*.net", false, new Date())},
        };
    }
    
    @Test(dataProvider = "validPages")
    public void areValidPages(Webpage webpage) {
        Collection<ValidationError> errors = strategy.validate(webpage, new ArrayList<>());
        Assert.assertTrue(errors.isEmpty());
    }
    
    @Test(dataProvider = "inValidPages")
    public void areInvalidPages(Webpage webpage) {
        Collection<ValidationError> errors = strategy.validate(webpage, new ArrayList<>());
        Assert.assertFalse(errors.isEmpty());
        errors.forEach(System.out::println);
    }
}
