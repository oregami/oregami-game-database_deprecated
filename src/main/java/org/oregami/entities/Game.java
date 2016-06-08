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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.oregami.entities.datalist.GameEntryType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@TopLevelEntity(discriminator = TopLevelEntity.Discriminator.GAME)
@Audited
@NamedQueries({
	@NamedQuery(name="Game.GetAll", query =
			"from Game g")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game extends BaseEntityUUID {

	private static final long serialVersionUID = -2362683596950421365L;

	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private GameEntryType gameEntryType;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	@JoinColumn
	private Set<ReleaseGroup> releaseGroupList = new HashSet<ReleaseGroup>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	@JoinColumn
    @OrderBy("id")
	private Set<GameTitle> gameTitleList = new HashSet<>();

	public void addReleaseGroup(ReleaseGroup vog) {
		this.releaseGroupList.add(vog);
		vog.setGame(this);
	}

	public Collection<ReleaseGroup> getReleaseGroupList() {
		return releaseGroupList;
	}

	public GameEntryType getGameEntryType() {
		return gameEntryType;
	}

	public void setGameEntryType(GameEntryType gameEntryType) {
		this.gameEntryType = gameEntryType;
	}

	public Set<GameTitle> getGameTitleList() {
		return gameTitleList;
	}

	public void setGameTitleList(Set<GameTitle> gameTitleList) {
		this.gameTitleList = gameTitleList;
	}

	public void addGameTitle(GameTitle g) {
		this.gameTitleList.add(g);
	}
}
