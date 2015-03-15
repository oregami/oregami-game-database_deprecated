package org.oregami.entities;

import org.oregami.entities.datalist.TitleType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class GameToGameTitleConnection extends BaseEntityUUID {

	private static final long serialVersionUID = -1883224144643128402L;
	
	public GameToGameTitleConnection() {
	}
	
	@ManyToOne
	private GameTitle gameTitle;

	@OneToOne
	private TitleType titleType;

	public TitleType getTitleType() {
		return titleType;
	}

	public void setTitleType(TitleType titleType) {
		this.titleType = titleType;
	}
	
	public GameTitle getGameTitle() {
		return gameTitle;
	}
	
	public void setGameTitle(GameTitle gameTitle) {
		this.gameTitle = gameTitle;
	}
	
	
}
