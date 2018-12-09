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
public interface IPersonPersistenceService extends IPersistenceService<Long, Person>{
    
    /**
     * Fetch a list of Person whose First Name matches the regex. Otherwise returns an empty List
     * @param regex
     * @return Returns a <code>java.util.List</code>
     */
    List<Person> findbyFirstName(String regex);
    
    /**
     * Fetch a list of Person whose Last Name matches the regex. Otherwise returns an empty List
     * @param regex
     * @return Returns a <code>java.util.List</code>
     */
    List<Person> findbyLastName(String regex);
    
    /**
     * Fetch a list of Person whose Mid Name matches the regex. Otherwise returns an empty List
     * @param regex
     * @return Returns a <code>java.util.List</code>
     */
    List<Person> findbyMidName(String regex);
    
    /**
     * Fetch a list of Person whose birth date matched. Otherwise returns an empty List
     * @param birthDate
     * @return Returns a <code>java.util.List</code>
     */
    List<Person> findbyBirthDate(Date birthDate);
    
    /**
     * Fetch a list of Person whose Address matches. Otherwise returns an empty List
     * @param address
     * @return Returns a <code>java.util.List</code>
     */
    List<Person> findbyAddress(Address address);
    
    /**
     * Fetch a list of Person who were added since a given date. Otherwise returns an empty List
     * @param date
     * @return Returns a <code>java.util.List</code>
     */
    List<Person> getAllPersonsAddedSince(Date date);
}
