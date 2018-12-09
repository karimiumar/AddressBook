package pragmatic.audacity.addressbook.validator;

import pragmatic.audacity.addressbook.domain.classes.Country;
import pragmatic.audacity.addressbook.util.NullChecker;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.field.FieldValidationStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CountryValidator extends FieldValidationStrategy<Country> {
	
	public CountryValidator(ErrorMessageService<Field> errorMsg) {
		super(errorMsg);
	}
	
	@Override
	public Collection<ValidationError> validate(Country country, List<String> toIgnore) {
		List<ValidationError> errors = new ArrayList<>();
		if(NullChecker.isNullOrEmpty(country)) {
			errors.add(new ValidationError(Country.class.getSimpleName() + " is " + country));
            return errors;
		}
		else{
			return super.validateObject(country, toIgnore);
		}
	}

}
