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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Game.GetAll", query = 
			"from Game g")
})
public class Game extends BaseEntity {

	private static final long serialVersionUID = -2362683596950421365L;

	public String tagLineDescription;
	
	public String description;
	
	public String longDescription;
	
	public boolean compilation;
	
	public boolean addOn;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	@JoinColumn(name="GameId", referencedColumnName="id")
	private Set<GameTitle> gameTitleList = new HashSet<GameTitle>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
//	@OrderBy("system ASC")
	@JoinColumn
	private Set<ReleaseGroup> releaseGroupList = new HashSet<ReleaseGroup>();

	public void addReleaseGroup(ReleaseGroup vog) {
		this.releaseGroupList.add(vog);
		vog.setGame(this);
	}

	public void addGameTitle(GameTitle t) {
		this.gameTitleList.add(t);
	}

	public Collection<ReleaseGroup> getReleaseGroupList() {
		return releaseGroupList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getTagLineDescription() {
		return tagLineDescription;
	}

	public void setTagLineDescription(String tagLineDescription) {
		this.tagLineDescription = tagLineDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public boolean isCompilation() {
		return compilation;
	}

	public void setCompilation(boolean compilation) {
		this.compilation = compilation;
	}

	public boolean isAddOn() {
		return addOn;
	}

	public void setAddOn(boolean addOn) {
		this.addOn = addOn;
	}

	public Set<GameTitle> getGameTitleList() {
		return gameTitleList;
	}

	public String getMainTitle() {
		String ret = "[missing title for game with id " + getId() + "!]";
		Collection<GameTitle> list = getGameTitleList();
		if (list!=null && !list.isEmpty()) {
			ret = 
//					"mt: " + 
				((GameTitle)list.toArray()[0]).getTitle();
		}
		return ret;
	}
}
