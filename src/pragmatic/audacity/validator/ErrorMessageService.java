/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.validator;

/**
 *
 * @author
 * @param <T>
 */
public interface ErrorMessageService<T> {
    String getErrorMessage(T t);
}
