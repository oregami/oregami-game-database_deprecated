package org.oregami.domain.model.hardwarePlatform;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.oregami.entities.BaseEntityUUID;
import org.oregami.entities.PlatformTitle;
import org.oregami.entities.datalist.HardwarePlatformType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
@NamedQueries({
	@NamedQuery(name="HardwarePlatform.GetAll", query =
			"from HardwarePlatform h")
})
public class HardwarePlatform extends BaseEntityUUID {

    public HardwarePlatform() {
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	private Set<PlatformTitle> title = new HashSet<>();

    @ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    public HardwarePlatformType platformType;

    public Set<PlatformTitle> getTitle() {
        return title;
    }

    public void setTitle(Set<PlatformTitle> title) {
        this.title = title;
    }

    public void addTitle(PlatformTitle t) {
        this.title.add(t);
    }

    public HardwarePlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(HardwarePlatformType platformType) {
        this.platformType = platformType;
    }
}
