package org.oregami.data;

import java.util.Date;

import org.oregami.entities.Game;
import org.oregami.entities.GameTitle;
import org.oregami.entities.KeyObjects.ReleaseGroupType;
import org.oregami.entities.KeyObjects.SystemKey;
import org.oregami.entities.ReleaseGroup;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * Class to fill the database with some sample entities.
 * 
 * @author twendelmuth
 * 
 */
public class DatabaseFiller {

	@Inject 
	private GameDao gameRepository;

	private void addMonkeyIsland() {
		Game gameMonkeyIsland = new Game();
		
		gameMonkeyIsland.addGameTitle(new GameTitle("Monkey Island"));
		gameMonkeyIsland.addGameTitle(new GameTitle("Monkey Island 1"));
		gameMonkeyIsland.addGameTitle(new GameTitle("The Secret of Monkey Island"));

		gameMonkeyIsland.setTagLineDescription("Monkey Island tld");
		gameMonkeyIsland.setDescription("Tolles Spiel mit viel Humor! (" + new Date() + ")");

		ReleaseGroup releaseGroupDos = new ReleaseGroup("DOS", SystemKey.MSDOS, ReleaseGroupType.Original);
		ReleaseGroup releaseGroupDosDemo = new ReleaseGroup("DOS", SystemKey.MSDOS, ReleaseGroupType.Demo);
		ReleaseGroup releaseGroupDosEnhanced = new ReleaseGroup("DOS (Verbesserte CD-Version)", SystemKey.MSDOS, ReleaseGroupType.Enhanced);

		gameMonkeyIsland.addReleaseGroup(releaseGroupDos);
		gameMonkeyIsland.addReleaseGroup(releaseGroupDosDemo);
		gameMonkeyIsland.addReleaseGroup(releaseGroupDosEnhanced);

		// ########### Amiga
		ReleaseGroup releaseGroupAmiga = new ReleaseGroup("Amiga 500/600 (OCS/ECS)", SystemKey.Amiga, ReleaseGroupType.Original);
		ReleaseGroup releaseGroupAmigaDemo = new ReleaseGroup("Amiga 500/600 (OCS/ECS)", SystemKey.Amiga, ReleaseGroupType.Demo);

		gameMonkeyIsland.addReleaseGroup(releaseGroupAmiga);
		gameMonkeyIsland.addReleaseGroup(releaseGroupAmigaDemo);

		// ########### Atari ST
		ReleaseGroup releaseGroupAtariST = new ReleaseGroup("Atari ST", SystemKey.AtariST, ReleaseGroupType.Original);
		gameMonkeyIsland.addReleaseGroup(releaseGroupAtariST);

		// ########### Apple
		ReleaseGroup releaseGroupApple = new ReleaseGroup("Apple Macintosh", SystemKey.AppleMacintosh, ReleaseGroupType.Original);
		ReleaseGroup vogAppleSpecial = new ReleaseGroup("Apple Macintosh", SystemKey.AppleMacintosh, ReleaseGroupType.Enhanced);

		gameMonkeyIsland.addReleaseGroup(releaseGroupApple);
		gameMonkeyIsland.addReleaseGroup(vogAppleSpecial);

		gameRepository.save(gameMonkeyIsland);
		
		gameMonkeyIsland.setDescription("new description");
		gameRepository.update(gameMonkeyIsland);
	}

	private void addResidentEvilGame() {

		Game gameResidentEvil = new Game();
		// gameResidentEvil.setId(2l);

		gameResidentEvil.setTagLineDescription("Resident Evil tld");
		gameResidentEvil.setDescription("Horror-Shooter (" + new Date() + ")");
		gameResidentEvil.addGameTitle(new GameTitle("Resident Evil"));

		ReleaseGroup releaseGroupPlaystation = new ReleaseGroup("Playstation", SystemKey.Playstation, ReleaseGroupType.Original);
		gameResidentEvil.addReleaseGroup(releaseGroupPlaystation);
		ReleaseGroup releaseGroupWindows = new ReleaseGroup("Windows", SystemKey.Windows, ReleaseGroupType.Original);
		gameResidentEvil.addReleaseGroup(releaseGroupWindows);

		gameRepository.save(gameResidentEvil);
	}

	private void addXWingGame() {
		Game gameXWing = new Game();

		gameXWing.setTagLineDescription("X-Wing tld");
		gameXWing.addGameTitle(new GameTitle("Star Wars - X-Wing"));
		gameXWing.addGameTitle(new GameTitle("Star Wars - X-Wing: Space Combat Simulator"));

		ReleaseGroup rgDos = new ReleaseGroup("DOS", SystemKey.MSDOS, ReleaseGroupType.Original);
		gameXWing.addReleaseGroup(rgDos);

		ReleaseGroup rgDosEnhanced = new ReleaseGroup("DOS", SystemKey.MSDOS, ReleaseGroupType.Enhanced);
		gameXWing.addReleaseGroup(rgDosEnhanced);

		ReleaseGroup rgWinEnhanced = new ReleaseGroup("Windows", SystemKey.Windows, ReleaseGroupType.Enhanced);
		gameXWing.addReleaseGroup(rgWinEnhanced);

		ReleaseGroup rgMacEnhanced = new ReleaseGroup("Apple Macintosh", SystemKey.AppleMacintosh, ReleaseGroupType.Enhanced);
		gameXWing.addReleaseGroup(rgMacEnhanced);

		gameRepository.save(gameXWing);
	}

	private void addGames() {
		
		if (gameRepository.findOne(1L)!=null) return;
		
		addMonkeyIsland();
		addResidentEvilGame();
		addXWingGame();
		
	}
	
	@Transactional
	public void initData() {
		addGames();
//		addVideoGamesDatabase1991();
//		addVideoGamesDatabase1992();
//		addVideoGamesDatabase1993();
	}
	


}
