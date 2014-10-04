package org.oregami.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;
import org.joda.time.LocalDateTime;

@Entity
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


    private LocalDateTime changeTime = null;

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    public LocalDateTime getChangeTimeGui() {
        return changeTime;
    }

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
