package org.oregami.data;

import org.oregami.entities.Game;
import org.oregami.entities.GameTitle;
import org.oregami.entities.datalist.GameEntryType;

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
	
	@Inject
	private GameEntryTypeDao gameEntryTypeDao;

	private void fillGameEntryType() {
		GameEntryType gameEntryType = new GameEntryType();
		gameEntryType.setValue(GameEntryType.GAME);
		gameEntryTypeDao.save(gameEntryType);		
	}
	
	private void addMonkeyIsland() {
		Game gameMonkeyIsland = new Game();
		
//		gameMonkeyIsland.setGameEntryType(gameEntryTypeDao.findByName(GameEntryType.GAME));
		
		gameMonkeyIsland.addGameTitle(new GameTitle("Monkey Island"));
		gameMonkeyIsland.addGameTitle(new GameTitle("Monkey Island 1"));
		gameMonkeyIsland.addGameTitle(new GameTitle("The Secret of Monkey Island"));

//		ReleaseGroup releaseGroupDos = new ReleaseGroup("DOS", SystemKey.MSDOS, ReleaseGroupReason.ORIGINAL);
//		ReleaseGroup releaseGroupDosDemo = new ReleaseGroup("DOS", SystemKey.MSDOS, ReleaseGroupReason.DEMO_PROMO);
//		ReleaseGroup releaseGroupDosEnhanced = new ReleaseGroup("DOS (Verbesserte CD-Version)", SystemKey.MSDOS, ReleaseGroupReason.ENHANCED);
//
//		gameMonkeyIsland.addReleaseGroup(releaseGroupDos);
//		gameMonkeyIsland.addReleaseGroup(releaseGroupDosDemo);
//		gameMonkeyIsland.addReleaseGroup(releaseGroupDosEnhanced);
//
//		// ########### Amiga
//		ReleaseGroup releaseGroupAmiga = new ReleaseGroup("Amiga 500/600 (OCS/ECS)", SystemKey.Amiga, ReleaseGroupReason.ORIGINAL);
//		ReleaseGroup releaseGroupAmigaDemo = new ReleaseGroup("Amiga 500/600 (OCS/ECS)", SystemKey.Amiga, ReleaseGroupReason.DEMO_PROMO);
//
//		gameMonkeyIsland.addReleaseGroup(releaseGroupAmiga);
//		gameMonkeyIsland.addReleaseGroup(releaseGroupAmigaDemo);
//
//		// ########### Atari ST
//		ReleaseGroup releaseGroupAtariST = new ReleaseGroup("Atari ST", SystemKey.AtariST, ReleaseGroupReason.ORIGINAL);
//		gameMonkeyIsland.addReleaseGroup(releaseGroupAtariST);
//
//		// ########### Apple
//		ReleaseGroup releaseGroupApple = new ReleaseGroup("Apple Macintosh", SystemKey.AppleMacintosh, ReleaseGroupReason.ORIGINAL);
//		ReleaseGroup vogAppleSpecial = new ReleaseGroup("Apple Macintosh", SystemKey.AppleMacintosh, ReleaseGroupReason.ENHANCED);
//
//		gameMonkeyIsland.addReleaseGroup(releaseGroupApple);
//		gameMonkeyIsland.addReleaseGroup(vogAppleSpecial);

		gameRepository.save(gameMonkeyIsland);
		
		gameRepository.update(gameMonkeyIsland);
	}

	private void addResidentEvilGame() {

		Game gameResidentEvil = new Game();
		// gameResidentEvil.setId(2l);

		gameResidentEvil.addGameTitle(new GameTitle("Resident Evil"));

//		ReleaseGroup releaseGroupPlaystation = new ReleaseGroup("Playstation", SystemKey.Playstation, ReleaseGroupReason.ORIGINAL);
//		gameResidentEvil.addReleaseGroup(releaseGroupPlaystation);
//		ReleaseGroup releaseGroupWindows = new ReleaseGroup("Windows", SystemKey.Windows, ReleaseGroupReason.ORIGINAL);
//		gameResidentEvil.addReleaseGroup(releaseGroupWindows);

		gameRepository.save(gameResidentEvil);
	}

	private void addXWingGame() {
		Game gameXWing = new Game();

		gameXWing.addGameTitle(new GameTitle("Star Wars - X-Wing"));
		gameXWing.addGameTitle(new GameTitle("Star Wars - X-Wing: Space Combat Simulator"));

//		ReleaseGroup rgDos = new ReleaseGroup("DOS", SystemKey.MSDOS, ReleaseGroupReason.ORIGINAL);
//		gameXWing.addReleaseGroup(rgDos);
//
//		ReleaseGroup rgDosEnhanced = new ReleaseGroup("DOS", SystemKey.MSDOS, ReleaseGroupReason.ENHANCED);
//		gameXWing.addReleaseGroup(rgDosEnhanced);
//
//		ReleaseGroup rgWinEnhanced = new ReleaseGroup("Windows", SystemKey.Windows, ReleaseGroupReason.ENHANCED);
//		gameXWing.addReleaseGroup(rgWinEnhanced);
//
//		ReleaseGroup rgMacEnhanced = new ReleaseGroup("Apple Macintosh", SystemKey.AppleMacintosh, ReleaseGroupReason.ENHANCED);
//		gameXWing.addReleaseGroup(rgMacEnhanced);

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
		fillGameEntryType();
		addGames();
//		addVideoGamesDatabase1991();
//		addVideoGamesDatabase1992();
//		addVideoGamesDatabase1993();
	}
	


}
