/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence;

import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.domain.classes.Phone;

import java.util.List;

/**
 *
 * @author
 */
public interface IPhonePersistenceService extends IPersistenceService<Long, Phone>{
    
    List<Phone> getAllPhonesOf(Person person);
    List<Phone> getPreferredPhoneOf(Person person);
	List<Phone> getAllPhones();
}
