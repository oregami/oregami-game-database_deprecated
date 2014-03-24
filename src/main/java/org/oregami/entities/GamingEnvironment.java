package org.oregami.entities;

import javax.persistence.Entity;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="GamingEnvironment.GetAll", query = 
			"from GamingEnvironment t")
})
public class GamingEnvironment extends BaseEntity {

	private static final long serialVersionUID = -4567292842284849726L;

	private String title;
	
	public GamingEnvironment() {
	}
	
	public GamingEnvironment(String title) {
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
