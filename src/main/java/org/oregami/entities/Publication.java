package org.oregami.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Publication.GetAll", query = "from Publication t") })
public class Publication extends BaseEntityUUID {

	private static final long serialVersionUID = -2098604730061653187L;

	private String name;

	@ManyToOne
	private Region region;

	@ManyToOne
	private Language language;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private final Set<PublicationIssue> publicationIssueList = new HashSet<PublicationIssue>();

	public Publication() {
	}

	public Publication(String name) {
		this.setName(name);
	}

	public Set<PublicationIssue> getPublicationIssueList() {
		return publicationIssueList;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name;
	}
}
