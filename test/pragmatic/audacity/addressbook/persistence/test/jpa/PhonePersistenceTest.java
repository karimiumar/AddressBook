package pragmatic.audacity.addressbook.persistence.test.jpa;

import pragmatic.audacity.addressbook.domain.classes.Phone;
import pragmatic.audacity.addressbook.persistence.IPhonePersistenceService;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPAPhonePersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.util.JpaDbUtil;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class PhonePersistenceTest {
	private IPhonePersistenceService service;
	private JpaDbUtil dbutil;
	private EntityTransaction tx;
	
	@BeforeClass
	public void setupJpa() {
		dbutil = JpaDbUtil.getUtil();
		service = new JPAPhonePersistenceServiceImpl();
		service.setEntityManager(dbutil.getEntityManager());
	}
	
	@BeforeMethod
	protected void startService() {
		tx = service.getTransaction();
		tx.begin();
	}
	
	@AfterMethod
	protected void endService() {
		try{
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}
	}
	
	@AfterClass
	protected void release() {
		dbutil.getFactory().close();
	}
	
	
	@Test
	public void save() {
		Phone phone = new Phone("+91-6778432", false , new Date());
		service.save(phone);
		Assert.assertEquals(1, (long) phone.getId());
		phone = new Phone("+91-6778436772", true, new Date());
		service.save(phone);
		Assert.assertEquals(2, (long) phone.getId());
	}
	
	@Test(dependsOnMethods={"save"})
	public void listAll() {
		List<Phone> phones = service.getAllPhones();
		Assert.assertFalse(phones.isEmpty());
	}
}
