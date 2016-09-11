package org.oregami.domain.model.gamingEnvironment;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;
import org.oregami.entities.BaseEntityUUID;
import org.oregami.domain.model.hardwarePlatform.HardwarePlatform;
import org.oregami.entities.PlatformTitle;
import org.oregami.entities.TopLevelEntity;

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

    public GamingEnvironment(Integer firstRaseYear) {
        this.setFirstReleaseYear(firstRaseYear);
    }

    public GamingEnvironment(Integer firstRaseYear, PlatformTitle t) {
        this(firstRaseYear);
        this.addTitle(t);
    }

    public GamingEnvironment(PlatformTitle t) {
        this.addTitle(t);
    }

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
	private HardwarePlatform hardwarePlatform;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @OrderBy("id")
	private Set<PlatformTitle> title = new HashSet<>();

    @Column()
    private Integer firstReleaseYear;

    public Integer getFirstReleaseYear() {
        return firstReleaseYear;
    }

    public void setFirstReleaseYear(Integer firstReleaseYear) {
        this.firstReleaseYear = firstReleaseYear;
    }

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
