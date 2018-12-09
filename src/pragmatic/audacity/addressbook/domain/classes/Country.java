package pragmatic.audacity.addressbook.domain.classes;

import pragmatic.audacity.addressbook.util.NullChecker;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="COUNTRY_INFO", schema="address")
@Access(AccessType.PROPERTY)
public class Country extends AddressBase {

	private String country;
	private String code;
	private Set<City> cities = new HashSet<>();
	
	@SuppressWarnings("unused")
	private Country(){}
	
	public Country(String country, String code, Date createdOn) {
		setCountry(country);
		setCode(code);
		setCreatedOn(createdOn);
	}
	
	public Country(Long id, String country, String code, Date createdOn) {
		this(country, code, createdOn);
		setId(id);
	}
	
	public Country(String country, String code, Date createdOn, Set<City> cities) {
		this(country, code, createdOn);
		setCities(cities);
	}
	
	
	public void addCity(City city) {
		if(!NullChecker.isNullOrEmpty(city)) {
			cities.add(city);
		}
	}
	
	public boolean removeCity(City city) {
		return cities.remove(city);
	}
	
	@Temporal(value=TemporalType.TIMESTAMP)
	public Date getCreatedOn() {
		return super.getCreatedOn();
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Id	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COUNTRY_SEQ")
	public Long getId() {
		return super.getId();
	}
	
	@Column(name="VERSION", nullable=false)
	public int getVersion() {
		return super.getVersion();
	}
	
	@Column(unique=true)
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(unique=true, length=3, nullable=false)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@JoinTable(name="COUNTRY_CITY")
	@OneToMany(fetch=FetchType.LAZY)
	public Set<City> getCities() {
		return cities;
	}
	
	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

    @Override
    public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Country)) return false;
		if (!super.equals(o)) return false;

		Country country1 = (Country) o;

		return code != null ? code.equals(country1.code) : country1.code == null
				&& (country != null ? country.equals(country1.country) : country1.country == null);

	}

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    public String toString() {
		return String.format(" Country[Id=%s Version=%s Country=%s Code=%s CreatedOn=%s]", 
				getId(), getVersion(), getCountry(), getCode(), getCreatedOn());
	}
	
}
