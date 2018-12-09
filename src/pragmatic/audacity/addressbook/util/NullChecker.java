/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.util;

import java.util.List;

/**
 *
 * @author
 */
public class NullChecker {

    public static <T> boolean isNullOrEmpty(T t) {
        if(null == t) return true;
        if(t.getClass().equals(String.class)) {
            String casted = (String) t;
            return casted.isEmpty();
        }
        if(t instanceof List) {
            return ((List) t).isEmpty();
        }
        return false;
    }

}
