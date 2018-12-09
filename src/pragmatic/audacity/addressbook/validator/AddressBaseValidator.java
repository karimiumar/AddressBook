package pragmatic.audacity.addressbook.validator;

import pragmatic.audacity.addressbook.domain.classes.AddressBase;
import pragmatic.audacity.addressbook.util.NullChecker;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.field.FieldValidationStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddressBaseValidator extends FieldValidationStrategy<AddressBase> {
	
	public AddressBaseValidator(ErrorMessageService<Field> errorMsgService) {
		super(errorMsgService);
	}
	
	@Override
	public Collection<ValidationError> validate(AddressBase base, List<String> toIgnore) {
		List<ValidationError> errors = new ArrayList<>();
		if(NullChecker.isNullOrEmpty(base)) {
			errors.add(new ValidationError(AddressBase.class.getSimpleName() + " is " + base));
			return errors;
		}
		toIgnore.add("id");
		return super.validateObject(base, toIgnore);
	}

}
