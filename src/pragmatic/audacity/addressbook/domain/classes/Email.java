/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pragmatic.audacity.addressbook.domain.classes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author
 */
@Entity
@Table(name="EMAIL_INFO", schema="address")
@Access(AccessType.PROPERTY)
public class Email extends AddressBase implements Serializable {

    private static final long serialVersionUID = -735252261900471910L;
    private String emailId;

    @SuppressWarnings("unused")
	private Email() {}

    public Email(String emailId, boolean preferred, Date createdOn) {
        setEmailId(emailId);
        setPreferred(preferred);
        setCreatedOn(createdOn);
    }

    public Email(Long id, String emailId, boolean preferred, Date createdOn) {
        this(emailId, preferred, createdOn);
        setId(id);
    }

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMAIL_SEQ")
    public Long getId() {
        return id;
    }

    @Column(name="VERSION", nullable=false)
	public int getVersion() {
		return version;
	}
    
    @Column(name="CREATED_ON", nullable=false) 
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
		return createdOn;
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
        hash = 41 * hash + Objects.hashCode(this.emailId);
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
        final Email other = (Email) obj;
        return Objects.equals(this.emailId, other.emailId);
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name="EMAIL_ID", unique=true)
    public String getEmailId() {
        return emailId;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Email [EmailId=%s]", getEmailId());
    }

}
