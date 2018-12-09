package pragmatic.audacity.addressbook.persistence.test.jpa;

import pragmatic.audacity.addressbook.domain.classes.*;
import pragmatic.audacity.addressbook.persistence.*;
import pragmatic.audacity.addressbook.persistence.jpa.sql.*;
import pragmatic.audacity.addressbook.persistence.jpa.util.JpaDbUtil;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PersonPersistenceTest {
    private Date createdOn = new Date();
	private IAddressPersistenceService addressService;
    private ICityPersistenceService cityService;
	private ICountryPersistenceService countryService;
    private IMobilePersistenceService mobileService;
    private IPhonePersistenceService phoneService;
    private IEmailPersistenceService emailService;
    private IWebpagePersistenceService webpagePersistenceService;
	private IPersonPersistenceService service;
	private JpaDbUtil dbutil;
	private EntityTransaction tx;
	
	@BeforeClass
	public void setupJpa() {
		dbutil = JpaDbUtil.getUtil();
        EntityManager entityManager = dbutil.getEntityManager();
		service = new JPAPersonPersistenceServiceImpl();
		service.setEntityManager(entityManager);
        addressService = new JPAAddressPersistenceServiceImpl();
        addressService.setEntityManager(entityManager);
        cityService = new JPACityPersistenceServiceImpl();
        cityService.setEntityManager(entityManager);
        countryService = new JPACountryPersistenceServiceImpl();
        countryService.setEntityManager(entityManager);
        mobileService = new JPAMobilePersistenceServiceImpl();
        mobileService.setEntityManager(entityManager);
        phoneService = new JPAPhonePersistenceServiceImpl();
        phoneService.setEntityManager(entityManager);
        emailService = new JPAEmailPersistenceServiceImpl();
        emailService.setEntityManager(entityManager);
        webpagePersistenceService = new JPAWebpagePersistenceServiceImpl();
        webpagePersistenceService.setEntityManager(entityManager);
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
	    final Mobile mobile = new Mobile("+91-6778432", false , createdOn);
        final Email email = new Email("helena@yahoo.com", true, createdOn);
        final Country country = new Country("Rogue Country", "ROG", createdOn);
		final City city = new City(country, "Rogue City", "Rogue State", "ROGUE02", createdOn);
        final Address rogueAddress = new Address("Rogue Chambers","Rogue Street", city, false, createdOn, AddressType.OFFICE);
        final Webpage webpage = new Webpage("http://twitter.com/owe", true, createdOn);
        final Phone phone = new Phone("+91-6778432", false , createdOn);
        mobileService.save(mobile);
        phoneService.save(phone);
        webpagePersistenceService.save(webpage);
        emailService.save(email);
        countryService.save(country);
        cityService.save(city);
        addressService.save(rogueAddress);
        List<Webpage>webpages = Arrays.asList(webpage);
        List<Mobile> mobiles = Arrays.asList(mobile);
        List<Phone> phones = Arrays.asList(phone);
        List<Email> emails = Arrays.asList(email);
        List<Address> addresses = Arrays.asList(rogueAddress);
        Person person = new Person("Rachel", "Jarrolt", "", new Date(), mobiles, webpages, addresses, phones, emails, createdOn);
        service.save(person);
        Assert.assertEquals(person.getId(), Long.valueOf(1));
	}
	
	@Test(dependsOnMethods={"save"})
	public void listAll() {
		List<Person> persons = service.getAllPersonsAddedSince(createdOn);
		Assert.assertFalse(persons.isEmpty());
	}
}
