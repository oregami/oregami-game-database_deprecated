package org.oregami.data;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.entities.Game;
import org.oregami.entities.GameTitle;
import org.oregami.entities.GameToGameTitleConnection;
import org.oregami.entities.KeyObjects.SystemKey;
import org.oregami.entities.Language;
import org.oregami.entities.Publication;
import org.oregami.entities.PublicationFranchise;
import org.oregami.entities.PublicationIssue;
import org.oregami.entities.Region;
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
			Arrays.asList("GameTitle", "GameToGameTitleConnection", "Game",  "Release", "ReleaseGroup", "Language", "Region", "PublicationFranchise", "Publication", "PublicationIssue");
	
	private static List<String> baseListDataTables = 
			Arrays.asList("Script", "BusinessModel", "CensorshipType", "DemoContentType", "GameEntryType", "ReleaseGroupReason", "ReleaseType", "RemakeEnhancementType", "TitleType", "UnreleaseState");
	
	@Inject 
	private GameDao gameDao;
	
	@Inject 
	private GameTitleDao gameTitleDao;

	@Inject 
	private LanguageDao languageDao;
	
	@Inject
	private PublicationFranchiseDao publicationFranchiseDao;
	
	BaseListFinder baseListFinder = BaseListFinder.instance();
	
	BaseListFiller baseListFiller = BaseListFiller.instance();
	
	public static DatabaseFiller getInstance() {
		if (instance==null) {
			JpaPersistModule jpaPersistModule = OregamiApplication.createJpaModule();
			injector = Guice.createInjector(jpaPersistModule);
			instance = injector.getInstance(DatabaseFiller.class);
			PersistService persistService = injector.getInstance(PersistService.class);
			persistService.start();
		}
		return instance;
	}
	
	public void addPublications() {
		addPublicationPowerPlay();
		addPublicationVideoGames();
	}


	private void addPublicationVideoGames() {
		PublicationFranchise pf = new PublicationFranchise("Video Games");
		
		Publication publicationVideoGames = new Publication("Video Games");
		publicationVideoGames.setLanguage(languageDao.findByExactName(Language.GERMAN));
		pf.getPublicationList().add(publicationVideoGames);
		
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1991,1));
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1991,2));
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1991,3));
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1992,1));
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1992,2));
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1992,3));
		
		publicationFranchiseDao.save(pf);
		
	}

	private void addPublicationPowerPlay() {
		PublicationFranchise pf = new PublicationFranchise("Power Play");
		
		Publication publicationPowerPlay = new Publication("Power Play");
		publicationPowerPlay.setLanguage(languageDao.findByExactName(Language.GERMAN));
		pf.getPublicationList().add(publicationPowerPlay);
		
		publicationPowerPlay.getPublicationIssueList().add(new PublicationIssue(1990,1));
		publicationPowerPlay.getPublicationIssueList().add(new PublicationIssue(1990,2));
		publicationPowerPlay.getPublicationIssueList().add(new PublicationIssue(1990,3));
		PublicationIssue issue_1990_4 = new PublicationIssue(1990,4);
		issue_1990_4.setReleaseDate(new LocalDate(1990,3,16));
		publicationPowerPlay.getPublicationIssueList().add(issue_1990_4);
		publicationPowerPlay.getPublicationIssueList().add(new PublicationIssue(1990,5));
		publicationPowerPlay.getPublicationIssueList().add(new PublicationIssue(1989,1));
		
		Publication publicationChipPowerPlay = new Publication("CHIP Power Play");
		pf.getPublicationList().add(publicationChipPowerPlay);
		publicationChipPowerPlay.setLanguage(languageDao.findByExactName(Language.GERMAN));
		
		publicationChipPowerPlay.getPublicationIssueList().add(new PublicationIssue(2013,1));
		publicationChipPowerPlay.getPublicationIssueList().add(new PublicationIssue(2013,2));
		publicationChipPowerPlay.getPublicationIssueList().add(new PublicationIssue(2013,3));
		publicationChipPowerPlay.getPublicationIssueList().add(new PublicationIssue(2013,4));
		
		publicationFranchiseDao.save(pf);
		
	}

	private void addMonkeyIsland() {
		Game gameMonkeyIsland = new Game();
		
		gameMonkeyIsland.setGameEntryType(baseListFinder.getGameEntryType(GameEntryType.GAME));
		
		gameTitleDao.save(new GameTitle("The Secret of Monkey Island", languageDao.findByExactName(Language.ENGLISH)));
		gameTitleDao.save(new GameTitle("Le Secret de L'Ile aux Singes", languageDao.findByExactName(Language.FRENCH)));
		gameTitleDao.save(new GameTitle("El Secreto de Monkey Island", languageDao.findByExactName(Language.SPANISH)));
		gameTitleDao.save(new GameTitle("Monkey Island", languageDao.findByExactName(Language.ENGLISH)));
		gameTitleDao.save(new GameTitle("Monkey Island 1", languageDao.findByExactName(Language.ENGLISH)));
		GameTitle gameTitle = new GameTitle("猴島小英雄", languageDao.findByExactName(Language.CHINESE));
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
		
	}

	private void addResidentEvilGame() {

		Game gameResidentEvil = new Game();
		gameResidentEvil.setGameEntryType(baseListFinder.getGameEntryType(GameEntryType.GAME));

		gameTitleDao.save(new GameTitle("Resident Evil", languageDao.findByExactName(Language.ENGLISH)));
		gameTitleDao.save(new GameTitle("Resident Evil: Director's Cut", languageDao.findByExactName(Language.ENGLISH)));

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

		gameTitleDao.save(new GameTitle("Star Wars - X-Wing", languageDao.findByExactName(Language.ENGLISH)));
		gameTitleDao.save(new GameTitle("X-Wing", languageDao.findByExactName(Language.ENGLISH)));
		gameTitleDao.save(new GameTitle("Star Wars - X-Wing: Space Combat Simulator", languageDao.findByExactName(Language.ENGLISH)));
		
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
	
	public void initBaseLists() {
		baseListFiller.initBaseLists();
	}
	
	public void initGameData() {
		addLanguages();
		addRegions();
		addGames();
		addPublications();
	}
	
//	@Transactional
	public void initData() {
		initBaseLists();
		initGameData();
	}
	
	private void addRegions() {
		//countries:
		RegionDao regionDao = injector.getInstance(RegionDao.class);
		regionDao.save(new Region(Region.GERMANY, true, false, null));
		regionDao.save(new Region(Region.UNITED_STATES, true, false, null));
		regionDao.save(new Region(Region.UNITED_KINGDOM, true, false, null));
		regionDao.save(new Region(Region.FRANCE, true, false, null));
		regionDao.save(new Region(Region.JAPAN, true, false, null));
		regionDao.save(new Region(Region.CHINA, true, false, null));
		
		//regions:
		regionDao.save(new Region(Region.EUROPE, false, false, null));
		regionDao.save(new Region(Region.NORTH_AMERICA, false, false, null));
		
	}
	public void addLanguages() {
		LanguageDao languageDao = injector.getInstance(LanguageDao.class);
		languageDao.save(new Language(Language.ARABIC));
		languageDao.save(new Language(Language.BENGALI));
		languageDao.save(new Language(Language.CANTONESE));
		languageDao.save(new Language(Language.CHINESE));
		languageDao.save(new Language(Language.DUTCH));
		languageDao.save(new Language(Language.ENGLISH));
		languageDao.save(new Language(Language.FRENCH));
		languageDao.save(new Language(Language.GERMAN));
		languageDao.save(new Language(Language.GREEK));
		languageDao.save(new Language(Language.HINDI));
		languageDao.save(new Language(Language.ITALIAN));
		languageDao.save(new Language(Language.JAPANESE));
		languageDao.save(new Language(Language.KOREAN));
		languageDao.save(new Language(Language.MANDARIN));
		languageDao.save(new Language(Language.PERSIAN));
		languageDao.save(new Language(Language.POLISH));
		languageDao.save(new Language(Language.PORTUGUESE));
		languageDao.save(new Language(Language.PUNJABI));
		languageDao.save(new Language(Language.RUSSIAN));
		languageDao.save(new Language(Language.SPANISH));
		languageDao.save(new Language(Language.TURKISH));
		
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
