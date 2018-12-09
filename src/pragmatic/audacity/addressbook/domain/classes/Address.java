/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.domain.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
/**
 *
 * @author
 */
@Entity
@Table(name="ADDRESS_INFO", schema="address")
@Access(AccessType.PROPERTY)
public class Address extends AddressBase{
    
    private String houseNo;
    private String streetAddress;
    private City city;
    private AddressType addressType;

    
    @SuppressWarnings("unused")
	private Address() {}

    public Address(String houseNo, String streetAddress, City city, boolean preferred, Date createdOn, AddressType type) {
        setHouseNo(houseNo);
        setStreetAddress(streetAddress);
        setCity(city);
        setPreferred(preferred);
        setCreatedOn(createdOn);
        setAddressType(type);
    }
    
    public Address(Long id, String houseNo, String streetAddress, City city, boolean preferred, Date createdOn, AddressType type) {
    	this(houseNo,streetAddress, city, preferred, createdOn, type);
    	super.setId(id);
    }
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADDRESS_SEQ")
    public Long getId() {
        return id;
    }

    @Column(name="VERSION", nullable=false)
	public int getVersion() {
		return version;
	}

    public void setAddressType(AddressType type) {
        this.addressType = type;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "ADDRESS_TYPE", nullable = false)
    public AddressType getAddressType() {
        return addressType;
    }

    @Column(name ="CREATED_ON", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
    	this.createdOn = createdOn;
    }
        
    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + Objects.hashCode(this.houseNo);
        hash = 89 * hash + Objects.hashCode(this.streetAddress);
        hash = 89 * hash + Objects.hashCode(this.city);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        return Objects.equals(this.houseNo, other.houseNo)
                && Objects.equals(this.streetAddress, other.streetAddress)
                && Objects.equals(this.city, other.city)
                && this.isPreferred() == other.isPreferred();

    }

    public boolean setPreferred(boolean preferred) {
        return super.preferred = preferred;
    }
    
    public boolean isPreferred() {
        return preferred;
    }
    
    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    public void setCity(City city) {
    	this.city = city;
    }
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ADDRESS_CITY")
    public City getCity() {
    	return city;
    }

	@Override
	public String toString() {
		return super.toString() + String.format(" Address [HouseNo=%s StreetAddress=%s City=%s]",
				getHouseNo(), getStreetAddress(), getCity());
	}
    
}
