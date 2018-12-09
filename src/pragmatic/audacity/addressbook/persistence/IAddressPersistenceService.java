/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence;

import pragmatic.audacity.addressbook.domain.classes.Address;
import pragmatic.audacity.addressbook.domain.classes.Person;

import java.util.Date;
import java.util.List;

/**
 *
 * @author
 */
public interface IAddressPersistenceService extends IPersistenceService<Long, Address>{
    
    List<Address> getAddressesAddedSince(Date date);
    List<Address> getAllAddressOf(Person person);
    List<Address> getPreferredAddressOf(Person person);
    List<Address> getAllAddressesForPin(String pin);
    List<Address> getCurrentAddressof(Person person);
    List<Address> getOfficeAddressof(Person person);
}
