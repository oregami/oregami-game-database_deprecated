package org.oregami.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@TopLevelEntity(discriminator = TopLevelEntity.Discriminator.WEBSITE)
@NamedQueries({@NamedQuery(name="Website.GetAll", query = "from Website w")})
public class Website extends BaseEntityUUID {

	private static final long serialVersionUID = -8912197072050937329L;

	public Website() {
		this.createTime = new LocalDateTime();
	}

	@Column
	private String url;

	@Column
	private String createSize;

	public String getCreateSize() {
		return createSize;
	}

	public void setCreateSize(String createSize) {
		this.createSize = createSize;
	}


	@Column
	private final LocalDateTime createTime;

	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	@Column(name = "DATA", unique = false, nullable = false, length = 90000000)
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    public LocalDateTime getChangeTimeGui() {
        return getChangeTime();
    }

}
