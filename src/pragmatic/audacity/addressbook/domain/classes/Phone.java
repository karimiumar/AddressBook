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
@Table(name="PHONE_INFO", schema="address")
@Access(AccessType.PROPERTY)
public class Phone extends AddressBase{
  
    private String phoneNo;
    
    @SuppressWarnings("unused")
	private Phone(){}
    
    public Phone(String phoneNo, boolean preferred, Date createdOn) {
        setPhoneNo(phoneNo);
        setPreferred(preferred);
        setCreatedOn(createdOn);
    }
    
    public Phone(Long id, String phoneNo, boolean preferred, Date createdOn) {
    	this(phoneNo, preferred, createdOn);
    	setId(id);
    }
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PHONE_SEQ")
    public Long getId() {
        return id;
    }

    @Column(name="VERSION", nullable=false)
	public int getVersion() {
		return version;
	}
    
    public void setCreatedOn(Date createdOn) {
    	this.createdOn = createdOn;
    }
    
    @Temporal(value=TemporalType.TIMESTAMP)
	public Date getCreatedOn() {
		return super.getCreatedOn();
	}

    @Override
	public void setVersion(int version) {
		this.version = version;
		setAtomicVersion(version);
	}
	
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "PHONE_NO")
    public String getPhoneNo() {
        return phoneNo;
    }
    
    public boolean setPreferred(boolean preferred) {
        return super.preferred = preferred;
    }
    
    public boolean isPreferred() {
        return preferred;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 47 * hash + Objects.hashCode(this.phoneNo);
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
        final Phone other = (Phone) obj;
        return Objects.equals(this.getPhoneNo(), other.getPhoneNo())
                && this.isPreferred() == other.isPreferred();
    }

	@Override
	public String toString() {
		return super.toString() + String.format(" Phone [PhoneNo=%s]",getPhoneNo());
	}
}
