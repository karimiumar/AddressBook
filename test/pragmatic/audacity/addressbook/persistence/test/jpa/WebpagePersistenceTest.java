package pragmatic.audacity.addressbook.persistence.test.jpa;

import pragmatic.audacity.addressbook.domain.classes.Webpage;
import pragmatic.audacity.addressbook.persistence.IWebpagePersistenceService;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPAWebpagePersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.util.JpaDbUtil;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class WebpagePersistenceTest  {
	private IWebpagePersistenceService service;
	private JpaDbUtil dbutil;
	private EntityTransaction tx;
	
	@BeforeClass
	public void setupJpa() {
		dbutil = JpaDbUtil.getUtil();
		service = new JPAWebpagePersistenceServiceImpl();
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
		Webpage webpage = new Webpage("http://fb.com/audacity", false , new Date());
		service.save(webpage);
		Assert.assertEquals(1, (long) webpage.getId());
		webpage = new Webpage("http://twitter.com/audacity", true, new Date());
		service.save(webpage);
		Assert.assertEquals(2, (long) webpage.getId());
	}
	
	@Test(dependsOnMethods={"save"})
	public void listAll() {
		List<Webpage> webpages = service.getAllWebpages();
		Assert.assertFalse(webpages.isEmpty());
	}
}
