/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence.jpa.sql;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.persistence.IMobilePersistenceService;

import javax.persistence.Query;
import java.util.List;

public class JPAMobilePersistenceServiceImpl extends JPAPersistenceImpl<Long, Mobile>
implements IMobilePersistenceService {

	@Override
    public List<Mobile> getAllMobileNosOf(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Mobile> getPreferredMobileNoOf(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public List<Mobile> getAllMobiles() {
		Query query = entityManager.createQuery("from Mobile m"); 
		return query.getResultList();
	}
    
}
