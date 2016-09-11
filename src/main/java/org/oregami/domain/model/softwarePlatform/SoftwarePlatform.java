package org.oregami.domain.model.softwarePlatform;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.oregami.entities.BaseEntityUUID;
import org.oregami.entities.PlatformTitle;
import org.oregami.entities.datalist.SoftwarePlatformType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
public class SoftwarePlatform extends BaseEntityUUID {

	public SoftwarePlatform() {
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	private Set<PlatformTitle> title = new HashSet<>();

	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	public SoftwarePlatformType platformType;

	public Set<PlatformTitle> getTitle() {
		return title;
	}

	public void setTitle(Set<PlatformTitle> title) {
		this.title = title;
	}

	public void addTitle(PlatformTitle t) {
		this.title.add(t);
	}

	public SoftwarePlatformType getPlatformType() {
		return platformType;
	}

	public void setPlatformType(SoftwarePlatformType platformType) {
		this.platformType = platformType;
	}
}
