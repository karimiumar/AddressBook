package pragmatic.audacity.addressbook.persistence.test.jpa;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
import pragmatic.audacity.addressbook.persistence.IMobilePersistenceService;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPAMobilePersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.util.JpaDbUtil;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class MobilePersistenceTest {
	private IMobilePersistenceService service;
	private JpaDbUtil dbutil;
	private EntityTransaction tx;
	
	@BeforeClass
	public void setupJpa() {
		dbutil = JpaDbUtil.getUtil();
		service = new JPAMobilePersistenceServiceImpl();
		service.setEntityManager(dbutil.getEntityManager());
	}
	
	@BeforeMethod
	protected void startService() throws Exception {
		tx = service.getTransaction();
		tx.begin();
	}
	
	@AfterMethod
	protected void endService() throws Exception {
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
		Mobile mobile = new Mobile("+91-6778432", false , new Date());
		service.save(mobile);
		Assert.assertEquals(1, (long) mobile.getId());
		mobile = new Mobile("+91-6778436772", true, new Date());
		service.save(mobile);
		Assert.assertEquals(2, (long) mobile.getId());
	}
	
	@Test(dependsOnMethods={"save"})
	public void listAll() {
		List<Mobile> mobiles = service.getAllMobiles();
		Assert.assertFalse(mobiles.isEmpty());
	}
}
