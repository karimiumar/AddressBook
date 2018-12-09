package pragmatic.audacity.addressbook.validator.errors;

import pragmatic.audacity.addressbook.domain.classes.Email;
import pragmatic.audacity.validator.pattern.PatternMatcherErrorService;

public class EmailErrorMessage extends PatternMatcherErrorService {

    @Override
    public String getErrorMessage(Object o) {
        if(!(o instanceof Email)) {
            throw new RuntimeException("Incoming object is not an instance of " + Email.class.getSimpleName());
        }
        Email email = (Email) o;
        return String.format("%s is not a valid email address.", email.getEmailId());
    }
}
