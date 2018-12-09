package pragmatic.audacity.addressbook.persistence.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.util.Properties;

public class JpaDbUtil {

	@PersistenceUnit(unitName = "AddressBookPersistenceUnit")
	private EntityManagerFactory  emf;
	
	private static JpaDbUtil SINGLETON = new JpaDbUtil();
	
	private JpaDbUtil() {
		emf = Persistence.createEntityManagerFactory("AddressBookPersistenceUnit");
	}
	
	public static JpaDbUtil getUtil() {
		return SINGLETON;
	}
	
	public EntityManagerFactory getFactory() {
		return emf;
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	//This is only for testing
	public void setFactory(Properties props) {
		emf = Persistence.createEntityManagerFactory("AddressBookTestPersistenceUnit", props);
	}
}
