package org.oregami.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.oregami.entities.datalist.HardwarePlatformType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
@TopLevelEntity(discriminator = TopLevelEntity.Discriminator.HARDWAREPLATFORM)
@NamedQueries({
	@NamedQuery(name="HardwarePlatform.GetAll", query =
			"from HardwarePlatform h")
})
public class HardwarePlatform extends BaseEntityUUID {

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable
	private Set<TransliteratedString> title = new HashSet<>();

	public HardwarePlatform() {
	}

    @ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public HardwarePlatformType platformType;

    public Set<TransliteratedString> getTitle() {
        return title;
    }

    public void setTitle(Set<TransliteratedString> title) {
        this.title = title;
    }

    public void addTitle(TransliteratedString t) {
        this.title.add(t);
    }

    public HardwarePlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(HardwarePlatformType platformType) {
        this.platformType = platformType;
    }
}
