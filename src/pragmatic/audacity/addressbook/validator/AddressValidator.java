/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.validator;

import pragmatic.audacity.addressbook.domain.classes.Address;
import pragmatic.audacity.addressbook.util.NullChecker;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.field.FieldValidationStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author
 */
public class AddressValidator extends FieldValidationStrategy<Address> {
	
	public AddressValidator(ErrorMessageService<Field> errorMsg) {
		super(errorMsg);
	}
	
	
    @Override
    public Collection<ValidationError> validate(Address address, List<String> toIgnore) {
        List<ValidationError> errors = new ArrayList<>();
        if(NullChecker.isNullOrEmpty(address)) {
            errors.add(new ValidationError(Address.class.getSimpleName() + " is " + address));
            return errors;
        }else{
        	return super.validateObject(address, toIgnore);
        }
    }    
}
