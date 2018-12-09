/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.validator;

import java.util.Collection;
import java.util.List;

/**
 *
 * The ValidationStrategy interface validates an object of T. If any errors are occurred during validation,
 * then a Collection of ValidationError objects are returned. The validate() method will be implemented by
 * its directly known subclasses.
 * 
 * @author
 * @param <T> 
 */
public interface ValidationStrategy<T> {
    /**
     * The validate method validates an T type and returns a list of ValidationErrors if any exception occurs.
     * @param t
     * @param toIgnore
     * @return
     */
	Collection<ValidationError> validate(T t, List<String> toIgnore);
}
