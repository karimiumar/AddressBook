package pragmatic.audacity.addressbook.persistence.test.jpa;

import pragmatic.audacity.addressbook.domain.classes.Address;
import pragmatic.audacity.addressbook.domain.classes.AddressType;
import pragmatic.audacity.addressbook.domain.classes.City;
import pragmatic.audacity.addressbook.domain.classes.Country;
import pragmatic.audacity.addressbook.persistence.IAddressPersistenceService;
import pragmatic.audacity.addressbook.persistence.ICityPersistenceService;
import pragmatic.audacity.addressbook.persistence.ICountryPersistenceService;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPAAddressPersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPACityPersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.sql.JPACountryPersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jpa.util.JpaDbUtil;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class AddressPersistenceTest {
    private Date createdOn = new Date();
	private IAddressPersistenceService service;
    private ICityPersistenceService cityService;
	private ICountryPersistenceService countryService;
	private JpaDbUtil dbutil;
	private EntityTransaction tx;

	@BeforeClass
	public void setupJpa() {
		dbutil = JpaDbUtil.getUtil();
		service = new JPAAddressPersistenceServiceImpl();
		service.setEntityManager(dbutil.getEntityManager());
        countryService = new JPACountryPersistenceServiceImpl();
		countryService.setEntityManager(dbutil.getEntityManager());
        cityService = new JPACityPersistenceServiceImpl();
		cityService.setEntityManager(dbutil.getEntityManager());
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
            if(tx.isActive())
			    tx.rollback();
		}
	}
	
	@AfterClass
	protected void release() {
		dbutil.getFactory().close();
	}
	
	
	@Test
	public void save() {
        Country country = new Country("India", "IND", createdOn);
		City city = new City(country, "Nainital", "Uttrakhand", "263001", createdOn);
        Address homeAddress = new Address("435","Poplars Compound", city, true, createdOn, AddressType.PERMANENT);
        Address permanentAddress = new Address("435","Poplars Compound", city, true, createdOn, AddressType.PERMANENT);
        Address correspondenceAddress = new Address("435","Poplars Compound", city, true, createdOn, AddressType.CORRESPONDENCE);
        Address otherAddress = new Address("Rogue Chambers","Rogue Street", city, false, createdOn, AddressType.OTHER);
        countryService.save(country);
		cityService.save(city);
        service.save(homeAddress);
        service.save(permanentAddress);
        service.save(correspondenceAddress);
        service.save(otherAddress);
        Assert.assertEquals(homeAddress.getId(), Long.valueOf(1L));
        Assert.assertEquals(permanentAddress.getId(), Long.valueOf(2L));
        Assert.assertEquals(correspondenceAddress.getId(), Long.valueOf(3));
        Assert.assertEquals(otherAddress.getId(), Long.valueOf(4));
	}
	
	@Test(dependsOnMethods={"save"})
	public void listAll() {
		List<Address> addresses = service.getAddressesAddedSince(createdOn);
		Assert.assertFalse(addresses.isEmpty());
	}
}
