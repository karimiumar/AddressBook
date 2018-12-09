package pragmatic.audacity.addressbook.validator.errors;

import pragmatic.audacity.addressbook.domain.classes.Webpage;
import pragmatic.audacity.validator.pattern.PatternMatcherErrorService;

public class WebpageErrorMessage extends PatternMatcherErrorService {

    @Override
    public String getErrorMessage(Object o) {
        if(!(o instanceof Webpage)) {
            throw new RuntimeException("Incoming object is not an instance of " + Webpage.class.getSimpleName());
        }
        Webpage webpage = (Webpage) o;
        return String.format("%s is not a valid webpage url", webpage.getUrl());
    }
}
