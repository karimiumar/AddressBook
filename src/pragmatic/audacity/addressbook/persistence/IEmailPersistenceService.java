/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence;

import pragmatic.audacity.addressbook.domain.classes.Email;
import pragmatic.audacity.addressbook.domain.classes.Person;

import java.util.List;

/**
 *
 * @author
 */
public interface IEmailPersistenceService extends IPersistenceService<Long, Email>{
    
    List<Email> getAllEmailsOf(Person person);
    List<Email> getPreferredEmailOf(Person person);
	List<Email> getAllEmails();
}
