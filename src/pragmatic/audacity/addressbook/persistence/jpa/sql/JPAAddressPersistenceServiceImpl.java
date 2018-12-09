/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.persistence.jpa.sql;

import pragmatic.audacity.addressbook.domain.classes.Address;
import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.persistence.IAddressPersistenceService;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 *
 * @author
 */
public class JPAAddressPersistenceServiceImpl extends JPAPersistenceImpl<Long, Address>
        implements IAddressPersistenceService {

    @Override
    public List<Address> getAddressesAddedSince(Date date) {
        Query query = entityManager.createQuery("from Address a where a.createdOn=:createdOn ");
        query.setParameter("createdOn", date);
        return query.getResultList();
    }

    @Override
    public List<Address> getAllAddressOf(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> getPreferredAddressOf(Person person) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> getAllAddressesForPin(String pin) {
        Query query = entityManager.createQuery("from Address a where a.zip=:zip ");
        query.setParameter("zip", pin);
        return query.getResultList();
    }

    @Override
    public List<Address> getCurrentAddressof(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> getOfficeAddressof(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
