/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.validator;

import pragmatic.audacity.addressbook.util.NullChecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author
 */
public class ValidationErrorGenerator {

    private Pattern pattern;
    private String errorMsg;

    public ValidationErrorGenerator(String regex) {
    	if(NullChecker.isNullOrEmpty(regex)) {
    		throw new RuntimeException("Incoming Regular Expression is null. Cannot compile.");
    	}else {
    		compile(regex);
    	}
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    private void compile(String regex) {
        pattern = Pattern.compile(regex);
    }

    public ValidationError matchPattern(String patternToMatch) {
        ValidationError error = null;
        Matcher matcher = pattern.matcher(patternToMatch);
        if(!matcher.matches()) {
            error = new ValidationError();
            error.setError(errorMsg);
        }
        return error;
    }
}
