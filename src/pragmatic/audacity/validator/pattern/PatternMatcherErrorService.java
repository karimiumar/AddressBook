package pragmatic.audacity.validator.pattern;

import pragmatic.audacity.validator.ErrorMessageService;

public abstract class PatternMatcherErrorService implements ErrorMessageService {

	/*public String getErrorMessage(Object obj) {
		if(!(obj instanceof Mobile)){
			if(!(obj instanceof Phone)) {
				if(!(obj instanceof Webpage)) {
					if(!(obj instanceof Email)){
						throw new RuntimeException("Errors can only be generated for instances of Mobile or Phone or Webpage or Email." + obj);
					}
				}
			}
		}
		String [] messages = getErrorMsg(obj);
		String sb = "";
        sb += (messages[0]);
        sb += (" ");
        sb += (messages[1]);
        sb += (" ");
        sb += ("is not valid.");
        return sb;
	}*/

	/*private String[] getErrorMsg(Object obj) {
		String [] messages = new String[2];
		if(obj instanceof Mobile) {
			Mobile mobile = (Mobile)obj;
			messages[0] = "Mobile No.";
			messages[1] = mobile.getMobileNo();
		}else if(obj instanceof Phone) {
			Phone phone = (Phone)obj;
			messages[0] = "Phone No.";
			messages[1] = phone.getPhoneNo();
		}else if(obj instanceof Webpage) {
			Webpage page = (Webpage)obj;
			messages[0] = "Webpage";
			messages[1] = page.getUrl().toString();
		}else if(obj instanceof Email) {
			Email email = (Email)obj;
			messages[0] = "Email";
			messages[1] = email.getEmailId().toString();
		}
		return messages;
	}*/
}
