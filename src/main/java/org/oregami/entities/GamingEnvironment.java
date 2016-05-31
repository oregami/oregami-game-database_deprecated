package org.oregami.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
@TopLevelEntity(discriminator = TopLevelEntity.Discriminator.GAMINGENVIRONMENT)
@NamedQueries({
	@NamedQuery(name="GamingEnvironment.GetAll", query =
			"from GamingEnvironment t")
})
public class GamingEnvironment extends BaseEntityUUID {

	private static final long serialVersionUID = -4567292842284849726L;

	public GamingEnvironment() {
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
	private HardwarePlatform hardwarePlatform;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @OrderBy("id")
	private Set<PlatformTitle> title = new HashSet<>();

	public Set<PlatformTitle> getTitle() {
		return title;
	}

	public void setTitle(Set<PlatformTitle> title) {
		this.title = title;
	}

	public void addTitle(PlatformTitle t) {
		this.title.add(t);
	}

	public HardwarePlatform getHardwarePlatform() {
		return hardwarePlatform;
	}

	public void setHardwarePlatform(HardwarePlatform hardwarePlatform) {
		this.hardwarePlatform = hardwarePlatform;
	}
}
