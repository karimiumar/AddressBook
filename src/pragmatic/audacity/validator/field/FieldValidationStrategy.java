package pragmatic.audacity.validator.field;

import pragmatic.audacity.addressbook.util.NullChecker;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.ValidationStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class FieldValidationStrategy<T> implements ValidationStrategy<T> {
	private final ErrorMessageService<Field> errorMsgService;
	
	public FieldValidationStrategy(ErrorMessageService<Field> errorMsg) {
		this.errorMsgService = errorMsg;
	}

	protected Collection<ValidationError> validateObject(T t, List<?> toIgnore) {
		List<ValidationError> errors = new ArrayList<>();
		if(null == toIgnore) toIgnore = Collections.EMPTY_LIST;
		if(!NullChecker.isNullOrEmpty(t)) {
			Field [] fields = t.getClass().getDeclaredFields();
			for(Field field: fields) {
				field.setAccessible(true);
				try {
					Object value  = field.get(t);
					if(!(toIgnore.contains(field.getName())) && NullChecker.isNullOrEmpty(value)) {
						ValidationError error = new ValidationError(errorMsgService.getErrorMessage(field));
						errors.add(error);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return errors;
	}

	public ErrorMessageService<Field> getErrorMsgService() {
		return errorMsgService;
	}
}
