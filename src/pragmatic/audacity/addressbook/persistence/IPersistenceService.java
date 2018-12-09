package pragmatic.audacity.addressbook.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public interface IPersistenceService<K, E> {

    E save(E entity);

    E find(K id);

    void delete(E entity);

    EntityTransaction getTransaction();

    EntityManager getEntityManager();

    void setEntityManager(EntityManager em);

}
