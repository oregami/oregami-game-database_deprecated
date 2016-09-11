package org.oregami.domain.model.publicationFranchise;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.oregami.entities.BaseEntityUUID;
import org.oregami.entities.Language;
import org.oregami.entities.Region;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
@NamedQueries({ @NamedQuery(name = "Publication.GetAll", query = "from Publication t") })
public class Publication extends BaseEntityUUID {

	private static final long serialVersionUID = -2098604730061653187L;

	private String name;

	@ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Region region;

	@ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Language language;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("issueNameYear ASC, issueNameNumber ASC")
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
