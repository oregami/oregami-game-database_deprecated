package org.oregami.data;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.oregami.dropwizard.OregamiService;
import org.oregami.entities.Game;
import org.oregami.entities.GameTitle;
import org.oregami.entities.GameToGameTitleConnection;
import org.oregami.entities.KeyObjects.SystemKey;
import org.oregami.entities.ReleaseGroup;
import org.oregami.entities.datalist.GameEntryType;
import org.oregami.entities.datalist.ReleaseType;
import org.oregami.entities.datalist.TitleType;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * Class to fill the database with some sample entities.
 * 
 * @author twendelmuth
 * 
 */
public class DatabaseFiller {

	private static DatabaseFiller instance;
	
	private static Injector injector = null;
	
	Logger logger = Logger.getLogger(DatabaseFiller.class);
	
	private static List<String> dataTables = 
			Arrays.asList("GameTitle", "GameToGameTitleConnection", "Game",  "Release", "ReleaseGroup");
	
	private static List<String> baseListDataTables = 
			Arrays.asList("BusinessModel", "CensorshipType", "DemoContentType", "GameEntryType", "ReleaseGroupReason", "ReleaseType", "RemakeEnhancementType", "TitleType", "UnreleaseState");
	
	@Inject 
	private GameDao gameDao;
	
	@Inject 
	private GameTitleDao gameTitleDao;
	
	
	
	BaseListFinder baseListFinder = BaseListFinder.instance();
	
	BaseListFiller baseListFiller = BaseListFiller.instance();
	
	public static DatabaseFiller getInstance() {
		if (instance==null) {
			JpaPersistModule jpaPersistModule = OregamiService.createJpaModule();
			injector = Guice.createInjector(jpaPersistModule);
			instance = injector.getInstance(DatabaseFiller.class);
			PersistService persistService = injector.getInstance(PersistService.class);
			persistService.start();
		}
		return instance;
	}

	private void addMonkeyIsland() {
		Game gameMonkeyIsland = new Game();
		
		gameMonkeyIsland.setGameEntryType(baseListFinder.getGameEntryType(GameEntryType.GAME));
		
		gameTitleDao.save(new GameTitle("The Secret of Monkey Island"));
		gameTitleDao.save(new GameTitle("Le Secret de L'Ile aux Singes"));
		gameTitleDao.save(new GameTitle("El Secreto de Monkey Island"));
		gameTitleDao.save(new GameTitle("Monkey Island"));
		gameTitleDao.save(new GameTitle("Monkey Island 1"));
		GameTitle gameTitle = new GameTitle("猴島小英雄");
		gameTitle.setStandardTransliteration("The Secret of Monkey Island");
		gameTitleDao.save(gameTitle);
		
		GameToGameTitleConnection gameToGameTitleConnection = new GameToGameTitleConnection();
		gameToGameTitleConnection.setTitleType(baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE));
		gameToGameTitleConnection.setGameTitle(gameTitleDao.findByExactName("The Secret of Monkey Island").get(0));
		gameMonkeyIsland.getGameToGameTitleConnectionList().add(gameToGameTitleConnection);
		
		GameToGameTitleConnection gameToGameTitleConnection1 = new GameToGameTitleConnection();
		gameToGameTitleConnection1.setTitleType(baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE));
		gameToGameTitleConnection1.setGameTitle(gameTitleDao.findByExactName("猴島小英雄").get(0));
		gameMonkeyIsland.getGameToGameTitleConnectionList().add(gameToGameTitleConnection1);
		
		GameToGameTitleConnection gameToGameTitleConnection2 = new GameToGameTitleConnection();
		gameToGameTitleConnection2.setTitleType(baseListFinder.getTitleType(TitleType.ABBREVIATION));
		gameToGameTitleConnection2.setGameTitle(gameTitleDao.findByExactName("Monkey Island").get(0));
		gameMonkeyIsland.getGameToGameTitleConnectionList().add(gameToGameTitleConnection2);
		
		GameToGameTitleConnection gameToGameTitleConnection3 = new GameToGameTitleConnection();
		gameToGameTitleConnection3.setTitleType(baseListFinder.getTitleType(TitleType.ABBREVIATION));
		gameToGameTitleConnection3.setGameTitle(gameTitleDao.findByExactName("Monkey Island 1").get(0));
		gameMonkeyIsland.getGameToGameTitleConnectionList().add(gameToGameTitleConnection3);		
		
		
		//#### Atari ST #######
		ReleaseGroup rgAtari = new ReleaseGroup("Atari", SystemKey.AtariST, baseListFinder.getReleaseType(ReleaseType.PORT));
		gameMonkeyIsland.addReleaseGroup(rgAtari);
		
		//#### Amiga #######
		ReleaseGroup rgAmiga = new ReleaseGroup("Amiga", SystemKey.Amiga, baseListFinder.getReleaseType(ReleaseType.PORT));
		gameMonkeyIsland.addReleaseGroup(rgAmiga);
		
		ReleaseGroup rgAmigaDemo = new ReleaseGroup("Amiga Demo", SystemKey.Amiga, baseListFinder.getReleaseType(ReleaseType.PORT));
		gameMonkeyIsland.addReleaseGroup(rgAmigaDemo);
		
		//#### MS-DOS ######
		ReleaseGroup rgDos = new ReleaseGroup("DOS Initial Floppy", SystemKey.MSDOS, baseListFinder.getReleaseType(ReleaseType.NATIVE_DEVELOPMENT));
		gameMonkeyIsland.addReleaseGroup(rgDos);
		
		ReleaseGroup rgDosVga = new ReleaseGroup("DOS Floppy VGA", SystemKey.MSDOS, baseListFinder.getReleaseType(ReleaseType.NATIVE_DEVELOPMENT));
		gameMonkeyIsland.addReleaseGroup(rgDosVga);

		ReleaseGroup rgDosEnhancedCDRom = new ReleaseGroup("DOS Enhanced CDROM", SystemKey.MSDOS, baseListFinder.getReleaseType(ReleaseType.NATIVE_DEVELOPMENT));
		gameMonkeyIsland.addReleaseGroup(rgDosEnhancedCDRom);
		
		ReleaseGroup rgDosDemo = new ReleaseGroup("DOS Demo", SystemKey.MSDOS, baseListFinder.getReleaseType(ReleaseType.NATIVE_DEVELOPMENT));
		gameMonkeyIsland.addReleaseGroup(rgDosDemo);
		
		//#### FMTowns #######
		ReleaseGroup rgFMTowns = new ReleaseGroup("FMTowns", SystemKey.FMTowns, baseListFinder.getReleaseType(ReleaseType.PORT));
		gameMonkeyIsland.addReleaseGroup(rgFMTowns);		
		
		//#### Mac #######
		ReleaseGroup rgMac = new ReleaseGroup("Mac", SystemKey.AppleMacintosh, baseListFinder.getReleaseType(ReleaseType.PORT));
		gameMonkeyIsland.addReleaseGroup(rgMac);		

		ReleaseGroup rgMacSE = new ReleaseGroup("Mac SE", SystemKey.AppleMacintosh, baseListFinder.getReleaseType(ReleaseType.PORT));
		gameMonkeyIsland.addReleaseGroup(rgMacSE);	
		
		
		gameDao.save(gameMonkeyIsland);
		
		gameDao.update(gameMonkeyIsland);
	}

	private void addResidentEvilGame() {

		Game gameResidentEvil = new Game();
		gameResidentEvil.setGameEntryType(baseListFinder.getGameEntryType(GameEntryType.GAME));

		gameTitleDao.save(new GameTitle("Resident Evil"));
		gameTitleDao.save(new GameTitle("Resident Evil: Director's Cut"));

		GameToGameTitleConnection gameToGameTitleConnection = new GameToGameTitleConnection();
		gameToGameTitleConnection.setTitleType(baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE));
		gameToGameTitleConnection.setGameTitle(gameTitleDao.findByExactName("Resident Evil").get(0));
		gameResidentEvil.getGameToGameTitleConnectionList().add(gameToGameTitleConnection);
		
		GameToGameTitleConnection gameToGameTitleConnection1 = new GameToGameTitleConnection();
		gameToGameTitleConnection1.setTitleType(baseListFinder.getTitleType(TitleType.RE_RELEASE_TITLE));
		gameToGameTitleConnection1.setGameTitle(gameTitleDao.findByExactName("Resident Evil: Director's Cut").get(0));
		gameResidentEvil.getGameToGameTitleConnectionList().add(gameToGameTitleConnection1);		
		
//		ReleaseGroup releaseGroupPlaystation = new ReleaseGroup("Playstation", SystemKey.Playstation, ReleaseGroupReason.ORIGINAL);
//		gameResidentEvil.addReleaseGroup(releaseGroupPlaystation);
//		ReleaseGroup releaseGroupWindows = new ReleaseGroup("Windows", SystemKey.Windows, ReleaseGroupReason.ORIGINAL);
//		gameResidentEvil.addReleaseGroup(releaseGroupWindows);

		gameDao.save(gameResidentEvil);
	}

	private void addXWingGame() {
		Game gameXWing = new Game();

		gameTitleDao.save(new GameTitle("Star Wars - X-Wing"));
		gameTitleDao.save(new GameTitle("X-Wing"));
		gameTitleDao.save(new GameTitle("Star Wars - X-Wing: Space Combat Simulator"));
		
		GameToGameTitleConnection gameToGameTitleConnection = new GameToGameTitleConnection();
		gameToGameTitleConnection.setTitleType(baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE));
		gameToGameTitleConnection.setGameTitle(gameTitleDao.findByExactName("Star Wars - X-Wing: Space Combat Simulator").get(0));
		gameXWing.getGameToGameTitleConnectionList().add(gameToGameTitleConnection);

		GameToGameTitleConnection gameToGameTitleConnection1 = new GameToGameTitleConnection();
		gameToGameTitleConnection1.setTitleType(baseListFinder.getTitleType(TitleType.ABBREVIATION));
		gameToGameTitleConnection1.setGameTitle(gameTitleDao.findByExactName("X-Wing").get(0));
		gameXWing.getGameToGameTitleConnectionList().add(gameToGameTitleConnection1);
		
		GameToGameTitleConnection gameToGameTitleConnection2 = new GameToGameTitleConnection();
		gameToGameTitleConnection2.setTitleType(baseListFinder.getTitleType(TitleType.ABBREVIATION));
		gameToGameTitleConnection2.setGameTitle(gameTitleDao.findByExactName("Star Wars - X-Wing").get(0));
		gameXWing.getGameToGameTitleConnectionList().add(gameToGameTitleConnection2);
		
		
		
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

		gameDao.save(gameXWing);
	}

	private void addGames() {
		
		if (gameDao.findAll().size()>0) return;
		
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
	
	@Transactional
	public void deleteGameData() {
		EntityManager em = injector.getInstance(EntityManager.class);
		for (String tableName : dataTables) {
			int update = em.createQuery("DELETE FROM " + tableName).executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("deleted all " + update + " rows from " + tableName);
			}
		}
	}
	
	@Transactional
	public void deleteBaseListData() {
		EntityManager em = injector.getInstance(EntityManager.class);
		for (String tableName : baseListDataTables) {
			int update = em.createQuery("DELETE FROM " + tableName).executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("deleted all " + update + " rows from " + tableName);
			}
		}
	}


}
