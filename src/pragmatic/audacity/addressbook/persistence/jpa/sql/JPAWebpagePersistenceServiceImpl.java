/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence.jpa.sql;

import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.domain.classes.Webpage;
import pragmatic.audacity.addressbook.persistence.IWebpagePersistenceService;

import java.util.List;

/**
 *
 * @author
 */
public class JPAWebpagePersistenceServiceImpl extends JPAPersistenceImpl<Long, Webpage>
    implements IWebpagePersistenceService {

    @Override
    public List<Webpage> getAllWebpagesOf(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public List<Webpage> getAllWebpages(){
		return entityManager.createQuery("from Webpage w").getResultList();
	}
    
}
