package pragmatic.audacity.addressbook.persistence.test.jpa;

import pragmatic.audacity.addressbook.domain.classes.Email;
import pragmatic.audacity.addressbook.persistence.IEmailPersistenceService;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPAEmailPersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.util.JpaDbUtil;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class EmailPersistenceTest {
	private IEmailPersistenceService service;
	private JpaDbUtil dbutil;
	private EntityTransaction tx;
	
	@BeforeClass
	public void setupJPA(){
		dbutil = JpaDbUtil.getUtil();
		service = new JPAEmailPersistenceServiceImpl();
		service.setEntityManager(dbutil.getEntityManager());
	}
	
	@BeforeMethod
	protected void startService(){
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
		Email email = new Email("helen@yahoo.com", true, new Date());
		service.save(email);
		Assert.assertEquals(1, (long) email.getId());
		email = new Email("hell@gmail.com", true, new Date());
		service.save(email);
		Assert.assertEquals(2, (long) email.getId());
	}
	
	@Test(dependsOnMethods={"save"})
	public void listAll() {
		List<Email> emails = service.getAllEmails();
		Assert.assertFalse(emails.isEmpty());
	}
}
