package pragmatic.audacity.addressbook.persistence.test.jpa;

import pragmatic.audacity.addressbook.domain.classes.Country;
import pragmatic.audacity.addressbook.persistence.ICountryPersistenceService;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPACountryPersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.util.JpaDbUtil;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class CountryPersistenceTest {
	private ICountryPersistenceService service;
	private JpaDbUtil dbutil;
	private EntityTransaction tx;
	
	@BeforeClass
	public void setupJpa() {
		dbutil = JpaDbUtil.getUtil();
		service = new JPACountryPersistenceServiceImpl();
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
		Country country = new Country("India", "IND", new Date());
		service.save(country);
		Assert.assertEquals(1, (long) country.getId());
		country = new Country("Panama", "PAN", new Date());
		service.save(country);
		Assert.assertEquals(2, (long) country.getId());
	}
	
	@Test(dependsOnMethods={"save"})
	public void listAll() {
		List<Country> countries = service.getAllCountries();
		Assert.assertFalse(countries.isEmpty());
	}
	
}
