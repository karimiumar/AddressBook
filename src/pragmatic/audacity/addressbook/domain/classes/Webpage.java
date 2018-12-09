/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.domain.classes;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author
 */
@Entity
@Table(name="WEBPAGE_INFO", schema="address")
@Access(AccessType.PROPERTY)
public class Webpage extends AddressBase{
    
    private URL url;
    
    @SuppressWarnings("unused")
	private Webpage(){}
    
    public Webpage(String urlPath, boolean preferred, Date createdOn) {
        setUrl(urlPath);
        setPreferred(preferred);
        setCreatedOn(createdOn);
    }
    
    public Webpage(Long id, String urlPath, boolean preferred, Date createdOn) {
    	this(urlPath, preferred, createdOn);
    	setId(id);
    }

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WEBPAGE_SEQ")
	public Long getId() {
		return super.getId();
	}
	
	@Override
	@Column(name="VERSION", nullable=false)
	public int getVersion() {
		return super.getVersion();
	}
	
    @Override
	public void setVersion(int version) {
		this.version = version;
		setAtomicVersion(version);
	}
    
    @Temporal(value=TemporalType.TIMESTAMP)
	public Date getCreatedOn() {
		return super.getCreatedOn();
	}
    
    public void setCreatedOn(Date createdOn) {
    	this.createdOn = createdOn;
    }
	
    public void setUrl(String urlPath) {
        try{
            url = new URL(urlPath);
        }catch(MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean setPreferred(boolean preferred) {
        return super.preferred = preferred;
    }
    
    public boolean isPreferred() {
        return preferred;
    }
    
    public URL getUrl() {
        return url;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + Objects.hashCode(this.url);
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
        final Webpage other = (Webpage) obj;
        return Objects.equals(this.url, other.url)
                && this.preferred == other.preferred;
    }

	@Override
	public String toString() {
		return super.toString() + String.format(" Webpage [URl=%s]", getUrl());
	}
    
}
