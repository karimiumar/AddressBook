package pragmatic.audacity.addressbook.domain.classes;

import pragmatic.audacity.addressbook.util.NullChecker;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CITY_INFO", schema="address")
@Access(AccessType.PROPERTY)
public class City extends AddressBase{

	private Country country;
	private String city;
	private String state;
	private String zip;
	
	@SuppressWarnings("unused")
	private City(){}
	
	public City(Country country, String city, String state, String zip, Date createdOn) {
		setCity(city);
		setState(state);
		setZip(zip);
		setCreatedOn(createdOn);
        setCountryAndAddCity(country);
	}

	public City(Long id, Country country, String city, String state, String zip, Date createdOn) {
		this(country, city, state, zip, createdOn);
		setId(id);
	}
	
	private void setCountryAndAddCity(Country country) {
		if(!NullChecker.isNullOrEmpty(country)) {
			setCountry(country);
			country.addCity(this);
		}
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(value=TemporalType.TIMESTAMP)
	public Date getCreatedOn() {
		return super.getCreatedOn();
	}
	
	@Id	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CITY_SEQ")
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

	@ManyToOne(targetEntity=Country.class, cascade={CascadeType.ALL}) @JoinColumn(name="COUNTRY_ID")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

    @Override
    public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof City)) return false;
		if (!super.equals(o)) return false;

		City city1 = (City) o;

		return city != null ? city.equals(city1.city) : city1.city == null
				&& (state != null ? state.equals(city1.state) : city1.state == null
				&& (zip != null ? zip.equals(city1.zip) : city1.zip == null
				&& (country != null ? country.equals(city1.country) : city1.country == null)));
	}

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public String toString() {
		return String.format("City[Id=%d Version=%d City=%s State=%s Zip=%s Country=%s CreatedOn=%s]", getId(), getVersion(), getCity(), getState(), 
				getZip(), getCountry(), getCreatedOn());
	}
	
}
