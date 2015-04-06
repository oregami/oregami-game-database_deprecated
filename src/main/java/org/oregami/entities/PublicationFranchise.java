package org.oregami.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@TopLevelEntity(discriminator = TopLevelEntity.Discriminator.PUBLICATIONFRANCHISE)
@Audited
@NamedQueries({
	@NamedQuery(name="PublicationFranchise.GetAll", query =
			"from PublicationFranchise t")
})
@JsonIgnoreProperties(ignoreUnknown = true)
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
