/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pragmatic.audacity.addressbook.persistence;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
import pragmatic.audacity.addressbook.domain.classes.Person;

import java.util.List;

/**
 *
 * @author
 */
public interface IMobilePersistenceService extends IPersistenceService<Long, Mobile> {

    List<Mobile> getAllMobileNosOf(Person person);

    List<Mobile> getPreferredMobileNoOf(Person person);

	List<Mobile> getAllMobiles();
}
