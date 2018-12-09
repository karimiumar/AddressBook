package pragmatic.audacity.validator.field;

import pragmatic.audacity.validator.ErrorMessageService;

import java.lang.reflect.Field;

public class FieldValidationErrorService implements ErrorMessageService<Field> {

	@Override
	public String getErrorMessage(Field field) {
		String fieldName = field.getName();
    	char first = fieldName.charAt(0);
    	fieldName = fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), String.valueOf(Character.toUpperCase(first)));
        String sb = fieldName;
        sb += " is null or empty.";
        return sb;
	}
}
