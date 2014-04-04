package org.oregami.entities.user;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.oregami.entities.BaseEntityUUID;
import org.oregami.entities.KeyObjects.UserStatusKey;

@Entity
public class UserStatus extends BaseEntityUUID {
	
	private static final long serialVersionUID = -5022685871866053210L;

	private Timestamp creationDate;
	
	private String verifyHash;
	
	private Timestamp verifyDate;

    @Enumerated(EnumType.STRING)
	private UserStatusKey userStatus;
	
	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getVerifyHash() {
		return verifyHash;
	}

	public void setVerifyHash(String verifyHash) {
		this.verifyHash = verifyHash;
	}

	public Timestamp getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Timestamp verifyDate) {
		this.verifyDate = verifyDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		String ret = "";
		ret += "[Registration " + getId() + ": " + creationDate + ", " + verifyHash + ", " + verifyDate + "]";
		
		return ret;
	}

	public UserStatusKey getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatusKey userStatus) {
		this.userStatus = userStatus;
	}
	
}
