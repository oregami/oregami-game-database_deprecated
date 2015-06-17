package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.Transactional;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.oregami.entities.*;
import org.oregami.entities.datalist.*;
import org.oregami.entities.user.User;
import org.oregami.util.StartHelper;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

/**
 * Class to fill the database with some sample entities.
 *
 * @author twendelmuth
 *
 */
public class DatabaseFiller {

	Logger logger = Logger.getLogger(DatabaseFiller.class);

	private static List<String> dataTables =
			Arrays.asList( "Release", "ReleaseGroup", "GameTitle", "Game",
                    "GameTitle",
                    "PublicationIssue",  "Publication",  "PublicationFranchise",
                    "Language", "Region"
                    );

	private static List<String> baseListDataTables =
			Arrays.asList("Script", "BusinessModel", "CensorshipType", "DemoContentType", "GameEntryType", "ReleaseGroupReason", "ReleaseType", "RemakeEnhancementType", "TitleType", "UnreleaseState");

	@Inject
	private GameDao gameDao;

	@Inject
	private LanguageDao languageDao;

	@Inject
	private PublicationFranchiseDao publicationFranchiseDao;

	BaseListFinder baseListFinder = StartHelper.getInstance(BaseListFinder.class);

	BaseListFiller baseListFiller = StartHelper.getInstance(BaseListFiller.class);

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

		gameMonkeyIsland.addGameTitle(
				TitleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"The Secret of Monkey Island"
				)
		);

		gameMonkeyIsland.addGameTitle(
				TitleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ABBREVIATION),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Monkey Island 1"
				)
		);

		gameMonkeyIsland.addGameTitle(
				TitleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.FRENCH),
						"Le Secret de L'Ile aux Singes"
				)
		);

		gameMonkeyIsland.addGameTitle(
				TitleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
						baseListFinder.getScript(Script.CHINESE),
						languageDao.findByExactName(Language.CHINESE),
						"猴島小英雄"
				)
		);


		/*
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
		*/

		gameDao.save(gameMonkeyIsland);


	}

	private void addResidentEvilGame() {

		Game gameResidentEvil = new Game();
		gameResidentEvil.setGameEntryType(baseListFinder.getGameEntryType(GameEntryType.GAME));

		gameResidentEvil.addGameTitle(
				TitleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Resident Evil"
				)
		);
		gameResidentEvil.addGameTitle(
				TitleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.RE_RELEASE_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Resident Evil: Director's Cut"
				)
		);

		ReleaseGroup rgPsOne = new ReleaseGroup("PS 1 Release", getGamingEnvironmentPlaystation1(), baseListFinder.getReleaseType(ReleaseType.NATIVE_DEVELOPMENT));
		gameResidentEvil.addReleaseGroup(rgPsOne);

		gameDao.save(gameResidentEvil);
	}

	private void addXWingGame() {
		Game gameXWing = new Game();

		gameXWing.addGameTitle(
				TitleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ABBREVIATION),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Star Wars - X-Wing"
				)
		);
		gameXWing.addGameTitle(
				TitleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ABBREVIATION),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"X-Wing"
				)
		);

		gameXWing.addGameTitle(
				TitleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Star Wars - X-Wing: Space Combat Simulator"
				)
		);

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
		addGamingEnvironments();
        //addPlatforms();
		addGames();
		addPublications();
	}

	private void addGamingEnvironments() {
		GamingEnvironmentDao dao = getInjector().getInstance(GamingEnvironmentDao.class);

		//====== SONY PLAYSTATION =================
		GamingEnvironment gamingEnvironmentPlaystation = new GamingEnvironment();
		PlatformTitle pt1 = TitleFactory.createPlatformTitle(
				StartHelper.getInstance(RegionDao.class).findByExactName(Region.UNITED_STATES),
				StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
				StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN),
				StartHelper.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH),
				"Sony Playstation"
		);
		gamingEnvironmentPlaystation.addTitle(pt1);
		PlatformTitle pt2 = TitleFactory.createPlatformTitle(
				StartHelper.getInstance(RegionDao.class).findByExactName(Region.JAPAN),
				StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
				StartHelper.getInstance(BaseListFinder.class).getScript(Script.JAPANESE),
				StartHelper.getInstance(LanguageDao.class).findByExactName(Language.JAPANESE),
				"プレイステーション"
		);
		gamingEnvironmentPlaystation.addTitle(pt2);
		dao.save(gamingEnvironmentPlaystation);

		//====== NES =================
		GamingEnvironment nes = new GamingEnvironment();
		nes.addTitle(TitleFactory.createPlatformTitle(
				StartHelper.getInstance(RegionDao.class).findByExactName(Region.UNITED_STATES),
				StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
				StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN),
				StartHelper.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH),
				"Famicom"
		));
		nes.addTitle(TitleFactory.createPlatformTitle(
				StartHelper.getInstance(RegionDao.class).findByExactName(Region.EUROPE),
				StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
				StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN),
				StartHelper.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH),
				"Nintendo Entertainment System"
		));
		nes.addTitle(TitleFactory.createPlatformTitle(
				StartHelper.getInstance(RegionDao.class).findByExactName(Region.JAPAN),
				StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
				StartHelper.getInstance(BaseListFinder.class).getScript(Script.JAPANESE),
				StartHelper.getInstance(LanguageDao.class).findByExactName(Language.JAPANESE),
				"ファミリーコンピュータ"
		));
		dao.save(nes);


		//====== Amiga =================
		GamingEnvironment amiga = new GamingEnvironment();
		amiga.addTitle(TitleFactory.createPlatformTitle(
				StartHelper.getInstance(RegionDao.class).findByExactName(Region.EUROPE),
				StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
				StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN),
				StartHelper.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH),
				"Commodore Amiga"
		));
		HardwarePlatform hpAmiga = new HardwarePlatform();
		hpAmiga.addTitle(TitleFactory.createLatinPlatformTitle(
				StartHelper.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH),
				"Amiga M68K Machine & Compatibles"
		));
		hpAmiga.addTitle(TitleFactory.createLatinPlatformTitle(
				StartHelper.getInstance(LanguageDao.class).findByExactName(Language.GERMAN),
				"Amiga M68K und Kompatible"
		));

		amiga.setHardwarePlatform(hpAmiga);
		dao.save(amiga);

		//====== C64 =================
		GamingEnvironment c64 = new GamingEnvironment();
		c64.addTitle(TitleFactory.createPlatformTitle(
				StartHelper.getInstance(RegionDao.class).findByExactName(Region.EUROPE),
				StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
				StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN),
				StartHelper.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH),
				"Commodore 64"
		));
		dao.save(c64);

		//====== MS-DOS =================
		GamingEnvironment msdos = new GamingEnvironment();
		msdos.addTitle(TitleFactory.createPlatformTitle(
				StartHelper.getInstance(RegionDao.class).findByExactName(Region.EUROPE),
				StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
				StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN),
				null,
				"MS-DOS"
		));
		dao.save(msdos);


	}

    //	@Transactional
	public void initData() {
		initBaseLists();
		initGameData();
	}

	public void addRegions() {
		//countries:
		RegionDao regionDao = getInjector().getInstance(RegionDao.class);
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
		LanguageDao languageDao = getInjector().getInstance(LanguageDao.class);
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
		EntityManager em = getInjector().getInstance(EntityManager.class);
		for (String tableName : dataTables) {
            System.out.println("deleting all rows from " + tableName);
			int update = em.createQuery("DELETE FROM " + tableName).executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("deleted all " + update + " rows from " + tableName);
			}
		}
	}

	@Transactional
	public void deleteBaseListData() {
		EntityManager em = getInjector().getInstance(EntityManager.class);
		for (String tableName : baseListDataTables) {
			int update = em.createQuery("DELETE FROM " + tableName).executeUpdate();
            System.out.println("deleting all rows from " + tableName);
			if (logger.isDebugEnabled()) {
				logger.debug("deleted all " + update + " rows from " + tableName);
			}
		}
	}


    @Transactional
    public void initDemoUser() {
        UserDao userDao = getInjector().getInstance(UserDao.class);
        User user = new User();
        user.setEmail("user1@oregami.org");
        user.setUsername("user1");
        user.setPasswordAndEncryptIt("password1");
        userDao.save(user);

        User user2 = new User();
        user2.setEmail("user2@oregami.org");
        user2.setUsername("user2");
        user2.setPasswordAndEncryptIt("password2");
        userDao.save(user2);

    }


    @Transactional
    public void dropAllData() {
        EntityManager em = getInjector().getInstance(EntityManager.class);
        em.createNativeQuery("TRUNCATE SCHEMA public AND COMMIT NO CHECK").executeUpdate();
        StartHelper.getInstance(BaseListFiller.class).reset();
    }

    public Injector getInjector() {
        return StartHelper.getInjector();
    }


	private GamingEnvironment getGamingEnvironmentPlaystation1() {
		return getInjector().getInstance(GamingEnvironmentDao.class).findOneByExactTitle("Sony Playstation");
	}

	private GamingEnvironment getGamingEnvironmentPlaystation2() {
		return getInjector().getInstance(GamingEnvironmentDao.class).findOneByExactTitle("Playstation 2");
	}
}
