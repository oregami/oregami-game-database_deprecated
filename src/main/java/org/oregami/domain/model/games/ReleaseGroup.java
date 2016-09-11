/*******************************************************************************
 * Copyright (C) 2012  Oregami.org, Germany http://www.oregami.org
 * 
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of version 3 or any later version of the
 * 	GNU Affero General Public License as published by the Free Software 
 * 	Foundation.
 * 	
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU Affero General Public License for more details.	
 * 	
 * 	You should have received a copy of the GNU Affero General Public License
 * 	along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.oregami.domain.model.games;

import org.hibernate.envers.Audited;
import org.oregami.entities.BaseEntityUUID;
import org.oregami.entities.datalist.*;
import org.oregami.domain.model.gamingEnvironment.GamingEnvironment;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Audited
public class ReleaseGroup extends BaseEntityUUID {

	private static final long serialVersionUID = 1L;

	private String name;

	@ManyToOne
	private ReleaseGroupReason releaseGroupReason;
	
	@ManyToOne
	private ReleaseType releaseType;

    @ManyToOne
    private UnReleasedState unreleasedState;

    @ManyToOne
    private ReleaseState releaseState;

	@ManyToMany
	private Set<DemoContentType> demoContentTypeList = new HashSet<DemoContentType>();

    @ManyToMany
    private Set<CensorshipType> censorshipTypeList = new HashSet<CensorshipType>();

    private boolean censored = false;
	
	private boolean released = true;

	@ManyToOne
	private GamingEnvironment gamingEnvironment;

	@ManyToOne(cascade = CascadeType.ALL)
	private Game game;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
//	@OrderBy("description ASC")
    @JoinColumn
	private Set<Release> releaseList = new HashSet<Release>();


	public ReleaseGroup() {
	}
	
	public ReleaseGroup(String name, GamingEnvironment gamingEnvironment, ReleaseType releaseType) {
		this.name = name;
		this.setReleaseType(releaseType);
		this.gamingEnvironment = gamingEnvironment;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public void addRelease(Release vo) {
		this.releaseList.add(vo);
//		vo.setReleaseGroup(this);
	}

	public String getName() {
		return name;
	}

	public Set<Release> getReleaseList() {
		return releaseList;
	}

	public boolean isCensored() {
		return censored;
	}

	public void setCensored(boolean censored) {
		this.censored = censored;
	}

	public boolean isReleased() {
		return released;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}

	public UnReleasedState getUnreleasedState() {
		return unreleasedState;
	}

	public void setUnreleasedState(UnReleasedState unreleasedState) {
		this.unreleasedState = unreleasedState;
	}

	public ReleaseType getReleaseType() {
		return releaseType;
	}

	public void setReleaseType(ReleaseType releaseType) {
		this.releaseType = releaseType;
	}

	public ReleaseGroupReason getReleaseGroupReason() {
		return releaseGroupReason;
	}

	public void setReleaseGroupReason(ReleaseGroupReason releaseGroupReason) {
		this.releaseGroupReason = releaseGroupReason;
	}

	public Set<DemoContentType> getDemoContentTypeList() {
		return demoContentTypeList;
	}
	
	public void addDemoContentType(DemoContentType demoContentType) {
		demoContentTypeList.add(demoContentType);
	}

	public void setGamingEnvironment(GamingEnvironment gamingEnvironment) {
		this.gamingEnvironment = gamingEnvironment;
	}

	public GamingEnvironment getGamingEnvironment() {
		return gamingEnvironment;
	}

    public void setReleaseList(Set<Release> releaseList) {
        this.releaseList = releaseList;
    }

    public ReleaseState getReleaseState() {
        return releaseState;
    }

    public void setReleaseState(ReleaseState releaseState) {
        this.releaseState = releaseState;
    }

    public Set<CensorshipType> getCensorshipTypeList() {
        return censorshipTypeList;
    }

    public void setCensorshipTypeList(Set<CensorshipType> censorshipTypeList) {
        this.censorshipTypeList = censorshipTypeList;
    }

    public void addCensorshipType(CensorshipType censorshipType) {
        this.censorshipTypeList.add(censorshipType);
    }
}
