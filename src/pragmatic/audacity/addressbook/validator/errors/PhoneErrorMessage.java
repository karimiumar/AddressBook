package pragmatic.audacity.addressbook.validator.errors;

import pragmatic.audacity.addressbook.domain.classes.Phone;
import pragmatic.audacity.validator.pattern.PatternMatcherErrorService;

public class PhoneErrorMessage extends PatternMatcherErrorService {

    @Override
    public String getErrorMessage(Object o) {
        if(!(o instanceof Phone)) {
            throw new RuntimeException("Incoming object is not an instance of " + Phone.class.getSimpleName());
        }
        Phone phone = (Phone) o;
        return String.format("%s is not a valid phone number.", phone.getPhoneNo());
    }
}
