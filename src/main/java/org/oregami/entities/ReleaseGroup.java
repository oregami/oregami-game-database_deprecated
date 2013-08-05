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

import org.oregami.entities.KeyObjects.ReleaseGroupType;


@Entity
public class ReleaseGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Game game;

	@OneToMany(mappedBy = "releaseGroup", cascade = CascadeType.ALL, orphanRemoval=true)
//	@OrderBy("description ASC")
	private Set<Release> releaseList = new HashSet<Release>();

	private ReleaseGroupType releaseGroupType;

	public ReleaseGroup() {
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void addRelease(Release vo) {
		this.releaseList.add(vo);
		vo.setReleaseGroup(this);
	}

	public ReleaseGroupType getReleaseGroupType() {
		return releaseGroupType;
	}

	public void setReleaseGroupType(ReleaseGroupType releaseGroupType) {
		this.releaseGroupType = releaseGroupType;
	}	
	
	public String getName() {
		return name;
	}

	public Set<Release> getReleaseList() {
		return releaseList;
	}

}
