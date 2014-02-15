package org.oregami.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({@NamedQuery(name="Website.GetAll", query = "from Website w")})
public class Website extends BaseEntityUUID {

	private static final long serialVersionUID = -8912197072050937329L;
	
	@Column(name = "DATA", unique = false, nullable = false, length = 10000000)
	private byte[] image;

	@Column(name = "THUMB", unique = false, nullable = false, length = 1000000)
	private byte[] thumbnail;	
	
	@Column()
	private boolean approved = false;
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
}
