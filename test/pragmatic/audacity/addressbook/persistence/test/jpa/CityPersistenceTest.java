package pragmatic.audacity.addressbook.persistence.test.jpa;

import pragmatic.audacity.addressbook.domain.classes.City;
import pragmatic.audacity.addressbook.domain.classes.Country;
import pragmatic.audacity.addressbook.persistence.ICityPersistenceService;
import pragmatic.audacity.addressbook.persistence.ICountryPersistenceService;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPACityPersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPACountryPersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.util.JpaDbUtil;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityTransaction;
import javax.swing.*;
import java.util.Date;
import java.util.List;

public class CityPersistenceTest {
	private ICityPersistenceService service;
	private ICountryPersistenceService countryService;
	private JpaDbUtil dbutil;
	private EntityTransaction tx;
	
	@BeforeClass
	public void setupJpa() {
		dbutil = JpaDbUtil.getUtil();
		service = new JPACityPersistenceServiceImpl();
		service.setEntityManager(dbutil.getEntityManager());
		countryService = new JPACountryPersistenceServiceImpl();
		countryService.setEntityManager(dbutil.getEntityManager());
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
		City city = new City(country, "Nainital", "Uttrakhand", "263001", new Date());
		countryService.save(country);
		service.save(city);
		Assert.assertEquals(1, (long) city.getId());
		city = new City(country, "Delhi", "Delhi", "110017", new Date());
		countryService.save(country);
		service.save(city);
		Assert.assertEquals(2, (long) city.getId());
	}
	
	@Test(dependsOnMethods={"save"})
	public void listAll() {
		List<City> cities = service.getAllCities();
        for (City city:cities){
            JOptionPane.showMessageDialog(null, city, "City Info", JOptionPane.INFORMATION_MESSAGE);
        }
		Assert.assertFalse(cities.isEmpty());
	}
}
