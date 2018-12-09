package pragmatic.audacity.addressbook.service;

import pragmatic.audacity.addressbook.domain.classes.*;

import java.util.List;
/**
 * An interface to communicate with the AddressBook application. 
 * Any GUI or web application will interact with the methods of this interface.
 *  
 * @author
 *
 */
public interface AddressBookService {
	
	/**
	 * Adds a person to the database if the person does not exist.
	 * Otherwise edit the details of a persisted <code>Person</code>
	 * entity.
	 * @param person
	 * @return The saved Person
	 */
	Person save(Person person);
	
	/**
	 * Returns a persisted <code>Person</code> entity from the store if one exists;
	 * otherwise returns a null.
	 *  
	 * @param id
	 * @return The Person
	 */
	Person getById(Long id);
	
	/**
	 * Returns a <code>java.util.List</code> of the <code>Person</code> instances
	 * from the data store. If none exists, then return an empty <code>java.util.List</code>
	 *   
	 * @return a <code>java.util.List</code>
	 */
	List<Person> getAll();
	
	/**
	 * Returns a <code>java.util.List</code> of the given <code>Address</code> instances of the
	 * given <code>Person</code> from the data store. If none exists, then return an empty
	 * <code>java.util.List</code>.
	 * 
	 * @param person
	 * @return a <code>java.util.List</code>
	 */
	List<Address> getAddressesOfPerson(Person person);
	
	/**
	 * Returns a <code>java.util.List</code> of the given <code>Mobile</code> instances of the
	 * given <code>Person</code> from the data store. If none exists, then return an empty
	 * <code>java.util.List</code>.
	 * 
	 * @param person
	 * @return a <code>java.util.List</code>
	 */
	List<Mobile> getMobilesOfPerson(Person person);
	
	/**
	 * Returns a <code>java.util.List</code> of the given <code>Phone</code> instances of the
	 * given <code>Person</code> from the data store. If none exists, then return an empty
	 * <code>java.util.List</code>.
	 * 
	 * @param person
	 * @return a <code>java.util.List</code>
	 */
	List<Phone> getPhonesOfPerson(Person person);
	
	/**
	 * Returns a <code>java.util.List</code> of the given <code>Webpage</code> instances of the
	 * given <code>Person</code> from the data store. If none exists, then return an empty
	 * <code>java.util.List</code>.
	 * 
	 * @param person
	 * @return a <code>java.util.List</code>
	 */
	List<Webpage> getWebPagesOfPerson(Person person);
}
