/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence.jpa.sql;

import pragmatic.audacity.addressbook.domain.classes.Address;
import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.persistence.IPersonPersistenceService;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 *
 * @author
 */
public class JPAPersonPersistenceServiceImpl extends JPAPersistenceImpl<Long, Person>
    implements IPersonPersistenceService {

    @Override
    public List<Person> findbyFirstName(String regex) {
        Query query = entityManager.createQuery(" from Person p where p.firstName like %:firstName");
        query.setParameter("firstName", regex);
        return query.getResultList();
    }

    @Override
    public List<Person> findbyLastName(String regex) {
        Query query = entityManager.createQuery(" from Person p where p.lastName like %:lastName");
        query.setParameter("lastName", regex);
        return query.getResultList();
    }

    @Override
    public List<Person> findbyMidName(String regex) {
        Query query = entityManager.createQuery(" from Person p where p.midName like %:midName");
        query.setParameter("midName", regex);
        return query.getResultList();
    }

    @Override
    public List<Person> findbyBirthDate(Date birthDate) {
        Query query = entityManager.createQuery(" from Person p where p.birthDate =:birthDate");
        query.setParameter("birthDate", birthDate);
        return query.getResultList();
    }

    @Override
    public List<Person> findbyAddress(Address address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getAllPersonsAddedSince(Date date) {
        Query query = entityManager.createQuery("from Person p where p.createdOn=:createdOn ");
        query.setParameter("createdOn", date);
        return query.getResultList();
    }
    
}
