/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.validator;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
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
public class MobileValidator extends PatternValidationStrategy<Mobile> {

    public MobileValidator(ValidationErrorGenerator generator, ErrorMessageService<Mobile> errorMsgService, final String regex) {
        super(generator,errorMsgService, regex);
    }    
    
    @Override
    public Collection<ValidationError> validate(Mobile mobile, List<String> toIgnore) {
        List<ValidationError> errors = new ArrayList<>();
        if(NullChecker.isNullOrEmpty(mobile)) {
            errors.add(new ValidationError(Mobile.class.getSimpleName() + " is " + mobile));
            return errors;
        }
        return super.validate(mobile, mobile.getMobileNo());
    }
}
