package org.oregami.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;

@Entity
@TopLevelEntity(discriminator = TopLevelEntity.Discriminator.SOFTWAREPLATFORM)
@NamedQueries({
	@NamedQuery(name="SoftwarePlatform.GetAll", query =
			"from SoftwarePlatform s")
})
public class SoftwarePlatform extends BaseEntityUUID {

	private String title;

	public SoftwarePlatform() {
	}

	public SoftwarePlatform(String title) {
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
