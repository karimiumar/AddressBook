package pragmatic.audacity.addressbook.validator;

import pragmatic.audacity.addressbook.domain.classes.City;
import pragmatic.audacity.addressbook.util.NullChecker;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.field.FieldValidationStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CityValidator extends FieldValidationStrategy<City> {

	public CityValidator(ErrorMessageService<Field> errorMsgService) {
		super(errorMsgService);
	}
	
	@Override
	public Collection<ValidationError> validate(City city, List<String> toIgnore) {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(NullChecker.isNullOrEmpty(city)) {
			errors.add(new ValidationError(City.class.getSimpleName() +" is " + city));
			return errors;
		}else {
			return super.validateObject(city, toIgnore);
		}
	}

}
