/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence.jpa.sql;

import pragmatic.audacity.addressbook.domain.classes.Email;
import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.persistence.IEmailPersistenceService;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author
 */
public class JPAEmailPersistenceServiceImpl extends JPAPersistenceImpl<Long, Email>
    implements IEmailPersistenceService {

    @Override
    public List<Email> getAllEmailsOf(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Email> getPreferredEmailOf(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public List<Email> getAllEmails() {
		Query query = entityManager.createQuery("from Email e");
		return query.getResultList();
	}
    
}
