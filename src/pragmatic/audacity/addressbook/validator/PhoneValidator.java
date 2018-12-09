/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.validator;

import pragmatic.audacity.addressbook.domain.classes.Phone;
import pragmatic.audacity.addressbook.util.NullChecker;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.ValidationErrorGenerator;
import pragmatic.audacity.validator.pattern.PatternValidationStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author
 */
public class PhoneValidator extends PatternValidationStrategy<Phone> {

    public PhoneValidator(ValidationErrorGenerator generator, ErrorMessageService<Phone> errorMsgService, final String regex) {
        super(generator, errorMsgService, regex);
    }    
    
    @Override
    public Collection<ValidationError> validate(Phone phone, List<String> toIgnore) {
        List<ValidationError> errors = new ArrayList<>();
        if(NullChecker.isNullOrEmpty(phone)) {
        	errors.add(new ValidationError(Phone.class.getSimpleName() + " is " + phone));
            return errors;
        }
        return super.validate(phone, phone.getPhoneNo());
    }
}
