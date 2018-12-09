package pragmatic.audacity.addressbook.domain.test;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class MobileTest {

	private final Date createdOn = new Date();
	private final Mobile mobile1 = new Mobile("+91-7890345", false, createdOn);
	private final Mobile mobile2 = new Mobile("+91-7890345", false, createdOn);
	private final Mobile mobile3 = new Mobile("+94-7890345", false, new Date());
	
	@Test
	public void areSameMobiles() {
		boolean result = mobile1.equals(mobile2);
		Assert.assertTrue(result);
	}
	
	@Test
	public void areDifferentMobiles() {
		boolean result = mobile1.equals(mobile3);
		Assert.assertFalse(result);
		
		result = mobile2.equals(mobile3);
		Assert.assertFalse(result);
	}
}
