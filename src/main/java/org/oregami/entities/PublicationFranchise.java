package org.oregami.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

@Entity
@Audited
@NamedQueries({
	@NamedQuery(name="PublicationFranchise.GetAll", query = 
			"from PublicationFranchise t")
})
public class PublicationFranchise extends BaseEntityUUID {

	private static final long serialVersionUID = -4693647736354542489L;
	
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	private final Set<Publication> publicationList = new HashSet<Publication>();
	
	public PublicationFranchise() {
	}
	
	public PublicationFranchise(String name) {
		this.setName(name);
	}	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Set<Publication> getPublicationList() {
		return publicationList;
	}

}
