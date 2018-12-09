/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.domain.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.AccessType.PROPERTY;

/**
 *
 * @author
 */
@Entity
@Table(name="MOBILE_INFO", schema="address")
@Access(PROPERTY)
public class Mobile extends AddressBase{
    private String mobileNo;

    @SuppressWarnings("unused")
	public Mobile() { }
    
    public Mobile(String mobileNo, boolean preferred, Date createdOn) {
        setMobileNo(mobileNo);
        setPreferred(preferred);
        setCreatedOn(createdOn);
    }
    
    public Mobile(Long id, String mobileNo, boolean preferred, Date createdOn) {
    	this(mobileNo, preferred, createdOn);
    	setId(id);
    }
    
	@Id @Column(name="ID", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MOBILE_SEQ")
	public Long getId() {
		return id;
	}
    
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON")
	public Date getCreatedOn() {
		return super.getCreatedOn();
	}
    
    public void setCreatedOn(Date createdOn){
    	this.createdOn = createdOn;
    }
     
    @Column(name="VERSION", nullable=false)
	public int getVersion() {
		return version;
	}

    @Column(name = "MOBILE_NO")
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public boolean setPreferred(boolean preferred) {
        return super.preferred = preferred;
    }

    @Column(name="PREFERRED")
    public boolean isPreferred() {
        return preferred;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 47 * hash + Objects.hashCode(this.mobileNo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(!super.equals(obj)){
        	return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mobile other = (Mobile) obj;
        return Objects.equals(this.mobileNo, other.mobileNo);
    }

	@Override
	public String toString() {
		return super.toString() + String.format(" Mobile [MobileNo=%s]",getMobileNo());
	}

}
