package pragmatic.audacity.addressbook.persistence.test.jdbc;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.persistence.jdbc.IJdbcPersistenceService;
import pragmatic.audacity.addressbook.persistence.jdbc.MobilePersistenceServiceImpl;
import pragmatic.audacity.addressbook.persistence.jdbc.Tables;
import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class MobilePersistenceTest {
    private IJdbcPersistenceService<Mobile> service;
    private JdbcConnectionManager connectionManager;
    private String MOBILE_INFO;

    @BeforeClass
    public void setup() {
        MOBILE_INFO = Tables.MOBILE_INFO;
        connectionManager = JdbcConnectionManager.getJdbcUtil();
        service = new MobilePersistenceServiceImpl(connectionManager, "address");
    }

    @AfterClass
    public void clear() {
        connectionManager.close();
        connectionManager = null;
    }

    @Test
    public void clearData() {
        String delete = "DELETE FROM " + MOBILE_INFO;
        service.delete(delete);
        List<Mobile> mobiles = service.getAll();
        Assert.assertTrue(mobiles.isEmpty());
    }

    @Test(dependsOnMethods = {"clearData"})
    public void save() {
        Mobile mobile = new Mobile("+91-6778432", false, new Date());
        String save = "INSERT INTO " + MOBILE_INFO + "(MOBILE_NO, VERSION, CREATED_ON, PREFERRED, PERSON_ID) VALUES(?,?,?,?,1100)";
        service.save(save, mobile.getMobileNo(), mobile.getVersion(), mobile.getCreatedOn(), mobile.isPreferred());
        mobile = service.find(mobile);
        Assert.assertTrue(mobile.getId() > 0);
        Mobile prefmobile = new Mobile("+91-6778432", true, new Date());
        save = "INSERT INTO " + MOBILE_INFO + "(MOBILE_NO, VERSION, CREATED_ON, PREFERRED, PERSON_ID) VALUES(?,?,?,?,1100)";
        service.save(save, prefmobile.getMobileNo(), prefmobile.getVersion(), prefmobile.getCreatedOn(), prefmobile.isPreferred());

    }

    @Test(dependsOnMethods = {"save"})
    public void belongsToPersonHavingId1100() {
        Person personWithId1100 = new Person();
        personWithId1100.setId(1100L);
        MobilePersistenceServiceImpl mobilePersistenceService = (MobilePersistenceServiceImpl) service;
        Map<Person, List<Mobile>> personMobilesMap= mobilePersistenceService.getAllMobileNosOf(personWithId1100);
        List<Mobile> mobiles = personMobilesMap.get(personWithId1100);
        Assert.assertEquals(2, mobiles.size());
        Assert.assertFalse(personMobilesMap.isEmpty());
    }

    @Test(dependsOnMethods = {"save"})
    public void getPreferredMobileOd1100() {
        Person personWithId1100 = new Person();
        personWithId1100.setId(1100L);
        MobilePersistenceServiceImpl mobilePersistenceService = (MobilePersistenceServiceImpl) service;
        List<Mobile> mobiles = mobilePersistenceService.getPreferredMobileNoOf(personWithId1100);
        Assert.assertFalse(mobiles.isEmpty());
    }

    @Test(dependsOnMethods = "save")
    public void listAll() {
        List<Mobile> mobiles = service.getAll();
        Assert.assertFalse(mobiles.isEmpty());
    }
}
