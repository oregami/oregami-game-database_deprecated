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
package org.oregami.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.oregami.entities.KeyObjects.SystemKey;
import org.oregami.entities.datalist.ReleaseGroupReason;
import org.oregami.entities.datalist.ReleaseType;
import org.oregami.entities.datalist.UnreleaseState;


@Entity
public class ReleaseGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	
	@ManyToOne
	private ReleaseGroupReason releaseGroupReason;
	
	@ManyToOne
	private ReleaseType releaseType;
	
	private boolean censored = false;
	
	private boolean released = true;
	
	private SystemKey system;
	
	@ManyToOne
	private UnreleaseState unreleaseState;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Game game;

	@OneToMany(mappedBy = "releaseGroup", cascade = CascadeType.ALL, orphanRemoval=true)
//	@OrderBy("description ASC")
	private final Set<Release> releaseList = new HashSet<Release>();


	public ReleaseGroup() {
	}
	
	public ReleaseGroup(String name, SystemKey system, ReleaseType releaseType) {
		this.name = name;
		this.setReleaseType(releaseType);
		this.setSystem(system);
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public void addRelease(Release vo) {
		this.releaseList.add(vo);
		vo.setReleaseGroup(this);
	}

	public String getName() {
		return name;
	}

	public Set<Release> getReleaseList() {
		return releaseList;
	}

	public SystemKey getSystem() {
		return system;
	}

	public void setSystem(SystemKey system) {
		this.system = system;
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

	public UnreleaseState getUnreleaseState() {
		return unreleaseState;
	}

	public void setUnreleaseState(UnreleaseState unreleaseState) {
		this.unreleaseState = unreleaseState;
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

}
