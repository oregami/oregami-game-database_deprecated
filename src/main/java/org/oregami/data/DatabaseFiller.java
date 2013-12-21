package org.oregami.data;

import org.oregami.dropwizard.OregamiService;
import org.oregami.entities.Game;
import org.oregami.entities.GameTitle;
import org.oregami.entities.KeyObjects.SystemKey;
import org.oregami.entities.ReleaseGroup;
import org.oregami.entities.datalist.DemoContentType;
import org.oregami.entities.datalist.GameEntryType;
import org.oregami.entities.datalist.ReleaseType;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * Class to fill the database with some sample entities.
 * 
 * @author twendelmuth
 * 
 */
public class DatabaseFiller {

	private static DatabaseFiller instance;
	
	@Inject 
	private GameDao gameRepository;
	
	BaseListFinder baseListFinder = BaseListFinder.instance();
	
	BaseListFiller baseListFiller = BaseListFiller.instance();
	
	public static DatabaseFiller getInstance() {
		if (instance==null) {
			JpaPersistModule jpaPersistModule = OregamiService.createJpaModule();
			Injector injector = Guice.createInjector(jpaPersistModule);
			instance = injector.getInstance(DatabaseFiller.class);
			PersistService persistService = injector.getInstance(PersistService.class);
			persistService.start();
		}
		return instance;
	}

	private void addMonkeyIsland() {
		Game gameMonkeyIsland = new Game();
		
//		gameMonkeyIsland.setGameEntryType(gameEntryTypeDao.findByName(GameEntryType.GAME));
		gameMonkeyIsland.setGameEntryType(baseListFinder.getGameEntryType(GameEntryType.GAME));
		
		gameMonkeyIsland.addGameTitle(new GameTitle("Monkey Island"));
		gameMonkeyIsland.addGameTitle(new GameTitle("Monkey Island 1"));
		gameMonkeyIsland.addGameTitle(new GameTitle("The Secret of Monkey Island"));

		ReleaseGroup rgDos = new ReleaseGroup("DOS-Demo", SystemKey.MSDOS, baseListFinder.getReleaseType(ReleaseType.NATIVE_DEVELOPMENT));
		gameMonkeyIsland.addReleaseGroup(rgDos);
		
		rgDos.addDemoContentType(BaseListFinder.instance().getDemoContentType(DemoContentType.CONTENT_LIMIT));
		rgDos.addDemoContentType(BaseListFinder.instance().getDemoContentType(DemoContentType.TECH_DEMO));
		
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
	
//	@Transactional
	public void initData() {
		baseListFiller.initBaseLists();
		addGames();
//		addVideoGamesDatabase1991();
//		addVideoGamesDatabase1992();
//		addVideoGamesDatabase1993();
	}


}
