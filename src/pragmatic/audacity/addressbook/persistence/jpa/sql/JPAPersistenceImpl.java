package pragmatic.audacity.addressbook.persistence.jpa.sql;

import pragmatic.audacity.addressbook.persistence.IPersistenceService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.lang.reflect.ParameterizedType;

public abstract class JPAPersistenceImpl<K, E> implements IPersistenceService<K, E> {

    protected Class<E> entityClass;
    
    @PersistenceUnit(unitName="AddressBookPersistenceUnit")
    protected EntityManager entityManager;
    
    @SuppressWarnings("unchecked")
	public JPAPersistenceImpl() {
        ParameterizedType genericSuperClass = (ParameterizedType)getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperClass.getActualTypeArguments()[1];
    }

    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void delete(E entity) {
        entityManager.remove(entity);
    }

    public E find(K id) {
        return entityManager.find(entityClass, id);
    }
    
    public EntityTransaction getTransaction() {
    	return entityManager.getTransaction();
    }
    
    public EntityManager getEntityManager() {
    	return entityManager;
    }
    
    public void setEntityManager(EntityManager em) {
    	this.entityManager = em;
    }
}
