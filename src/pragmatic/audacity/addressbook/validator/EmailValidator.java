package pragmatic.audacity.addressbook.validator;

import pragmatic.audacity.addressbook.domain.classes.Email;
import pragmatic.audacity.addressbook.util.NullChecker;
import pragmatic.audacity.validator.ErrorMessageService;
import pragmatic.audacity.validator.ValidationError;
import pragmatic.audacity.validator.ValidationErrorGenerator;
import pragmatic.audacity.validator.pattern.PatternValidationStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmailValidator extends PatternValidationStrategy<Email> {

	/*
	 * 	  ^	#start of the line
		  [_A-Za-z0-9-\\+]+		# must start with string in the bracket [ ], must contains one or more (+)
		  (						# start of group #1
		    \\.[_A-Za-z0-9-]+	# follow by a dot "." and string in the bracket [ ], must contains one or more (+)
		  )*					# end of group #1, this group is optional (*)
		  @						# must contains a "@" symbol
		  [A-Za-z0-9-]+ 			# follow by string in the bracket [ ], must contains one or more (+)
		  (			      		# start of group #2 - first level TLD checking
		  \\.[A-Za-z0-9]+  		# follow by a dot "." and string in the bracket [ ], must contains one or more (+)
		  )*					# end of group #2, this group is optional (*)
		  (						# start of group #3 - second level TLD checking
		  \\.[A-Za-z]{2,}  		# follow by a dot "." and string in the bracket [ ], with minimum length of 2
		  )						# end of group #3
		  $						# end of the line
	 */
	
	public static String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    /*public EmailValidator(ValidationErrorGenerator generator, ErrorMessageService<AddressBase> errorMsgService, String regex) {
    	this(generator, errorMsgService);
    	EmailValidator.regex = regex;
    }*/
    
    public EmailValidator(ValidationErrorGenerator generator, ErrorMessageService<Email> errorMsgService, final String regex) {
    	super(generator, errorMsgService,regex);
    }

	@Override
	public Collection<ValidationError> validate(Email email, List<String> toIgnore) {
		List<ValidationError> errors = new ArrayList<>();
		if(NullChecker.isNullOrEmpty(email)) {
			errors.add(new ValidationError(Email.class.getSimpleName() + " is " + email));
			return errors;
		}
		return super.validate(email, email.getEmailId());
	}

}
