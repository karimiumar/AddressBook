/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.domain.classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author
 */
@Entity
@Table(name="PERSON_INFO", schema="address")
@Access(AccessType.PROPERTY)
public class Person extends AddressBase {

	private String firstName;
	private String lastName;
	private String midName;
	private Date birthDate;
	private List<Mobile> mobiles = new ArrayList<>();
	private List<Webpage> webpages = new ArrayList<>();
	private List<Address> addresses = new ArrayList<>();
	private List<Phone> phones = new ArrayList<>();
	private List<Email> emails = new ArrayList<>();

	@SuppressWarnings("unused")
	public Person(){}
	
	public Person(String firstName, String lastName, String midName,
			Date birthDate, List<Mobile> mobiles, List<Webpage> webpages,
			List<Address> addresses, List<Phone> phones, List<Email> emails, Date createdOn) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setMidName(midName);
		setBirthDate(birthDate);
		setWebpages(webpages);
		setMobiles(mobiles);
		setPhones(phones);
		setAddresses(addresses);
		setCreatedOn(createdOn);
		setEmails(emails);
	}

	public Person(Long id, String firstName, String lastName, String midName,
			Date birthDate, List<Mobile> mobiles, List<Webpage> webpages,
			List<Address> addresses, List<Phone> phones, List<Email> emails, Date createdOn) {
		this(firstName, lastName, midName, birthDate, mobiles, webpages, addresses, phones, emails, createdOn);
		setId(id);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 47 * result + Objects.hashCode(this.addresses);
		result = 47 * result + Objects.hashCode(this.birthDate);
		result = 47 * result + Objects.hashCode(this.firstName);
		result = 47 * result + Objects.hashCode(this.lastName);
		result = 47 * result + Objects.hashCode(this.midName);
		result = 47 * result + Objects.hashCode(this.mobiles);
		result = 47 * result + Objects.hashCode(this.phones);
		result = 47 * result + Objects.hashCode(this.webpages);
		result = 47 * result + Objects.hashCode(this.emails);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		return Objects.equals(this.addresses, other.addresses)
				&& Objects.equals(this.birthDate, other.birthDate)
				&& Objects.equals(this.lastName, other.lastName)
				&& Objects.equals(this.firstName, other.firstName)
				&& Objects.equals(this.midName, other.midName)
				&& Objects.equals(this.phones, other.phones)
				&& Objects.equals(this.webpages, other.webpages)
				&& Objects.equals(this.mobiles, other.mobiles)
				&& Objects.equals(this.emails, other.emails);
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Temporal(value=TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON", nullable = false)
	public Date getCreatedOn() {
		return super.getCreatedOn();
	}
	
	@Id	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSON_SEQ")
	public Long getId() {
		return super.getId();
	}
	
	@Column(name="VERSION", nullable=false)
	public int getVersion() {
		return super.getVersion();
	}
	
	@Override
	public void setVersion(int version) {
		this.version = version;
		setAtomicVersion(version);
	}
	
	@Column(name="FIRST_NAME", nullable=false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NAME", nullable=false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="MID_NAME", nullable=true)
	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	@Temporal(value=TemporalType.DATE)
	@Column(name="BIRTH_DATE", nullable=false )
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	public List<Mobile> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<Mobile> mobiles) {
		this.mobiles = mobiles;
	}

    @OneToMany(cascade = CascadeType.PERSIST)
	public List<Webpage> getWebpages() {
		return webpages;
	}

	public void setWebpages(List<Webpage> webpages) {
		this.webpages = webpages;
	}

    @OneToMany(cascade = CascadeType.PERSIST)
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

    @OneToMany(cascade = CascadeType.PERSIST)
	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

    @OneToMany(cascade = CascadeType.PERSIST)
	public List<Email> getEmails() {
		return emails;
	}

	@Override
	public String toString() {
		return String.format( "Person [Id=%s Version=%s FirstName=%s LastName=%s MidName=%s BirthDate=%s Mobiles=%s Webpages=%s Addresses=%s Phones=%s Emails=%s CreatedOn=%s]",
						getId(), getVersion(), getFirstName(), getLastName(), getMidName(),
						getBirthDate(), getMobiles(), getWebpages(),
						getAddresses(), getPhones(), getEmails(), getCreatedOn());
	}

}
