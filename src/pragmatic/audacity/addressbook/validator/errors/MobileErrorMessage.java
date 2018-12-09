package pragmatic.audacity.addressbook.validator.errors;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
import pragmatic.audacity.validator.pattern.PatternMatcherErrorService;

public class MobileErrorMessage extends PatternMatcherErrorService {

    @Override
    public String getErrorMessage(Object o) {
        if(!(o instanceof Mobile)) {
            throw new RuntimeException("Incoming object is not an instance of " + Mobile.class.getSimpleName());
        }
        Mobile mobile = (Mobile) o;
        return String.format("%s is not a valid mobile number.", mobile.getMobileNo());
    }
}
