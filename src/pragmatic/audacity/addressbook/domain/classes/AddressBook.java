package pragmatic.audacity.addressbook.domain.classes;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	
	private List<Person> contacts = new ArrayList<>();
	
	public void setContacts(List<Person> contacts) {
		this.contacts = contacts;
	}
	
	public List<Person> getContacts() {
		return contacts;
	}
}
