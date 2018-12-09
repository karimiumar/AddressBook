/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence.jpa.sql;

import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.domain.classes.Phone;
import pragmatic.audacity.addressbook.persistence.IPhonePersistenceService;

import java.util.List;

/**
 *
 * @author
 */
public class JPAPhonePersistenceServiceImpl extends JPAPersistenceImpl<Long, Phone>
    implements IPhonePersistenceService {

    @Override
    public List<Phone> getAllPhonesOf(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Phone> getPreferredPhoneOf(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public List<Phone> getAllPhones() {
		return entityManager.createQuery(" from Phone p").getResultList();
	}
    
}
