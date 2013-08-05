package org.oregami.entities;

import javax.persistence.Entity;

@Entity
public class GameTitle extends BaseEntity {

	private static final long serialVersionUID = -4693647736354542489L;
	
	private String title;

	public GameTitle() {
	}
	
	public GameTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
