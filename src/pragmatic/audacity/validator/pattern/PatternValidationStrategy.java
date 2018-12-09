package pragmatic.audacity.validator.pattern;

import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.ValidationErrorGenerator;
import pragmatic.audacity.validator.ValidationStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PatternValidationStrategy<T> implements ValidationStrategy<T> {

	private final String regex;
    private ValidationErrorGenerator generator;
    private final ErrorMessageService<T> errorMsgService;
    
    public PatternValidationStrategy(ValidationErrorGenerator generator, ErrorMessageService<T> errorMsgService, final String regex) {
    	this.errorMsgService = errorMsgService;
    	this.generator = generator;
        this.regex = regex;
    }
    
    public Collection<ValidationError> validate(T t, String regex) {
    	List<ValidationError> errors = new ArrayList<>();
    	generator.setErrorMsg(errorMsgService.getErrorMessage((T)t));
        ValidationError error = generator.matchPattern(regex);
        if(null != error) {
            errors.add(error);
        }
        return errors;
    }
    
    public String getRegex() {
		return regex;
	}
    
    public ErrorMessageService<T> getErrorMsgService() {
		return errorMsgService;
	}
    
    public ValidationErrorGenerator getGenerator() {
		return generator;
	}
}
