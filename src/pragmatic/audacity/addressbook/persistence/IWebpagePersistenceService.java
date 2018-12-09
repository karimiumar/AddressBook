/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence;

import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.domain.classes.Webpage;

import java.util.List;

/**
 *
 * @author
 */
public interface IWebpagePersistenceService extends IPersistenceService<Long, Webpage>{
        
    List<Webpage> getAllWebpagesOf(Person person);

	List<Webpage> getAllWebpages();
}
