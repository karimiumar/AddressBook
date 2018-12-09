/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.domain.classes;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author
 */
public class AddressBase {

    protected Long id;
    protected int version = 0;
    private AtomicInteger atomicVersion = new AtomicInteger(0);
    boolean preferred;
    protected Date createdOn;
    
    public void setId(Long id) {
        this.id = id;
    }

    void setAtomicVersion(int version) {
        this.atomicVersion.set(version);
    }

	public Long getId() {
		return id;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
		setAtomicVersion(version);
	}
    
    public int incrementVersion() {
        return atomicVersion.incrementAndGet();
    } 
    
    public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
    
    public Date getCreatedOn() {
		return createdOn;
	}
    
    public boolean isPreferred() {
		return preferred;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressBase)) return false;

        AddressBase that = (AddressBase) o;

        return preferred == that.preferred
                && version == that.version
                && (createdOn != null ? createdOn.equals(that.createdOn) : that.createdOn == null
                && (id != null ? id.equals(that.id) : that.id == null));

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + version;
        result = 31 * result + (preferred ? 1 : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        return result;
    }

    @Override
	public String toString() {
		return String.format("AddressBase [Id=%s Version=%s CreatedOn=%s Preferred=%s]",
				getId(), getVersion(), getCreatedOn(), isPreferred());
	}
}
