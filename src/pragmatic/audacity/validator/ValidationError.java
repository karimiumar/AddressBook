/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.validator;

/**
 *
 * @author
 */
public final class ValidationError {
    private String error;

    ValidationError() {
        
    }
    
    public ValidationError(String error) {
        setError(error);
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
