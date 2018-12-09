package pragmatic.audacity.addressbook.validator;

import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.util.NullChecker;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.field.FieldValidationStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonValidator extends FieldValidationStrategy<Person> {

	public PersonValidator(ErrorMessageService<Field> errorMsgService) {
		super(errorMsgService);
	}
	
	@Override
	public Collection<ValidationError> validate(Person person, List<String> toIgnore) {
		List<ValidationError> errors = new ArrayList<>();
		if(NullChecker.isNullOrEmpty(person)) {
			errors.add(new ValidationError(Person.class.getSimpleName() + " is" + person));
			return errors;
		}
		return super.validateObject(person, toIgnore);
	}
}
