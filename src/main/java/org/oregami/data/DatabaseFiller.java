package org.oregami.data;

import org.joda.time.LocalDate;
import org.oregami.entities.*;
import org.oregami.entities.datalist.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Class to fill the database with some sample entities.
 *
 * @author twendelmuth
 *
 */
@Component
public class DatabaseFiller {

	@Autowired
	private LanguageDao languageDao;

	@Autowired
	private PublicationFranchiseDao publicationFranchiseDao;

    @Autowired
    private GamingEnvironmentDao gamingEnvironmentDao;

    @Autowired
    private RegionDao regionDao;

    @Autowired
    BaseListFinder baseListFinder;

    @Autowired
    TitleFactory titleFactory;

    @Autowired
    GameDao gameDao;

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
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1991, 2));
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1991,3));
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1992,1));
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1992,2));
		publicationVideoGames.getPublicationIssueList().add(new PublicationIssue(1992, 3));

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
		publicationPowerPlay.getPublicationIssueList().add(new PublicationIssue(1990, 5));
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
				titleFactory.createGameTitle(
						regionDao.findByExactName(Region.NORTH_AMERICA), //Region
						baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"The Secret of Monkey Island"
				)
		);

		gameMonkeyIsland.addGameTitle(
				titleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ABBREVIATION),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Monkey Island 1"
				)
		);

		gameMonkeyIsland.addGameTitle(
				titleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.FRENCH),
						"Le Secret de L'Ile aux Singes"
				)
		);

		gameMonkeyIsland.addGameTitle(
				titleFactory.createGameTitle(
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
				titleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Resident Evil"
				)
		);
		gameResidentEvil.addGameTitle(
				titleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.RE_RELEASE_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Resident Evil: Director's Cut"
				)
		);

		ReleaseGroup rgPsOne = new ReleaseGroup("PS 1 Release", getGamingEnvironmentPlaystation1(), baseListFinder.getReleaseType(ReleaseType.NATIVE_DEVELOPMENT));
        rgPsOne.setReleaseGroupReason(baseListFinder.getReleaseGroupReason(ReleaseGroupReason.ORIGINAL));

        Release releaseGermany = new Release();
        releaseGermany.setDescription("rel_ger");
        ReleaseRegion germany = new ReleaseRegion();
        germany.setRegion(regionDao.findByExactName(Region.GERMANY));
        germany.setReleaseDate(new LocalDate(1999,12,1));
        releaseGermany.getReleaseRegionList().add(germany);
        germany.setRelease(releaseGermany);
        rgPsOne.addRelease(releaseGermany);

		gameResidentEvil.addReleaseGroup(rgPsOne);

		gameDao.save(gameResidentEvil);
	}

	private void addXWingGame() {
		Game gameXWing = new Game();

		gameXWing.addGameTitle(
				titleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ABBREVIATION),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Star Wars - X-Wing"
				)
		);
		gameXWing.addGameTitle(
				titleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ABBREVIATION),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"X-Wing"
				)
		);

		gameXWing.addGameTitle(
				titleFactory.createGameTitle(
						null, //Region
						baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
						baseListFinder.getScript(Script.LATIN),
						languageDao.findByExactName(Language.ENGLISH),
						"Star Wars - X-Wing: Space Combat Simulator"
				)
		);

		gameDao.save(gameXWing);
	}

	public void addGames() {
		if (gameDao.findAll().size()>0) return;
		addMonkeyIsland();
		addResidentEvilGame();
		addXWingGame();
	}


	public void addGamingEnvironments() {
		//====== SONY PLAYSTATION =================
		GamingEnvironment gamingEnvironmentPlaystation = new GamingEnvironment();

        //gamingEnvironmentPlaystation.setHardwarePlatform(HardwarePlatformTypeDao.);

		PlatformTitle pt1 = titleFactory.createPlatformTitle(
                regionDao.findByExactName(Region.UNITED_STATES),
				baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
				baseListFinder.getScript(Script.LATIN),
				languageDao.findByExactName(Language.ENGLISH),
				"Sony Playstation"
		);
		gamingEnvironmentPlaystation.addTitle(pt1);
		PlatformTitle pt2 = titleFactory.createPlatformTitle(
				regionDao.findByExactName(Region.JAPAN),
				baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
				baseListFinder.getScript(Script.JAPANESE),
				languageDao.findByExactName(Language.JAPANESE),
				"プレイステーション"
		);
		gamingEnvironmentPlaystation.addTitle(pt2);
		gamingEnvironmentDao.save(gamingEnvironmentPlaystation);

		//====== NES =================
		GamingEnvironment nes = new GamingEnvironment();
		nes.addTitle(titleFactory.createPlatformTitle(
				regionDao.findByExactName(Region.UNITED_STATES),
				baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
				baseListFinder.getScript(Script.LATIN),
				languageDao.findByExactName(Language.ENGLISH),
				"Famicom"
		));
		nes.addTitle(titleFactory.createPlatformTitle(
				regionDao.findByExactName(Region.EUROPE),
				baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
				baseListFinder.getScript(Script.LATIN),
				languageDao.findByExactName(Language.ENGLISH),
				"Nintendo Entertainment System"
		));
		nes.addTitle(titleFactory.createPlatformTitle(
				regionDao.findByExactName(Region.JAPAN),
				baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
				baseListFinder.getScript(Script.JAPANESE),
				languageDao.findByExactName(Language.JAPANESE),
				"ファミリーコンピュータ"
		));
        gamingEnvironmentDao.save(nes);


		//====== Amiga =================
		GamingEnvironment amiga = new GamingEnvironment();
		amiga.addTitle(titleFactory.createPlatformTitle(
				regionDao.findByExactName(Region.EUROPE),
				baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
				baseListFinder.getScript(Script.LATIN),
				languageDao.findByExactName(Language.ENGLISH),
				"Commodore Amiga"
		));
		HardwarePlatform hpAmiga = new HardwarePlatform();
		hpAmiga.addTitle(titleFactory.createLatinPlatformTitle(
				languageDao.findByExactName(Language.ENGLISH),
				"Amiga M68K Machine & Compatibles"
		));
		hpAmiga.addTitle(titleFactory.createLatinPlatformTitle(
				languageDao.findByExactName(Language.GERMAN),
				"Amiga M68K und Kompatible"
		));

		amiga.setHardwarePlatform(hpAmiga);
        gamingEnvironmentDao.save(amiga);

		//====== C64 =================
		GamingEnvironment c64 = new GamingEnvironment();
		c64.addTitle(titleFactory.createPlatformTitle(
				regionDao.findByExactName(Region.EUROPE),
				baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
				baseListFinder.getScript(Script.LATIN),
				languageDao.findByExactName(Language.ENGLISH),
				"Commodore 64"
		));
        gamingEnvironmentDao.save(c64);

		//====== MS-DOS =================
		GamingEnvironment msdos = new GamingEnvironment();
		msdos.addTitle(titleFactory.createPlatformTitle(
				regionDao.findByExactName(Region.EUROPE),
				baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE),
				baseListFinder.getScript(Script.LATIN),
				null,
				"MS-DOS"
		));
        gamingEnvironmentDao.save(msdos);


	}

    public void addRegions() {
		//countries:
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
//
//
//    @Transactional
//    public void initDemoUser() {
//        UserDao userDao = getInjector().getInstance(UserDao.class);
//		if (userDao.findAll().size()==0) {
//			User user = new User();
//			user.setEmail("user1@oregami.org");
//			user.setUsername("user1");
//			user.setPasswordAndEncryptIt("password1");
//			userDao.save(user);
//
//			User user2 = new User();
//			user2.setEmail("user2@oregami.org");
//			user2.setUsername("user2");
//			user2.setPasswordAndEncryptIt("password2");
//			userDao.save(user2);
//
//			User user3 = new User();
//			user3.setEmail("gene1@oregami.org");
//			user3.setUsername("gene");
//			user3.setPasswordAndEncryptIt("gene");
//			userDao.save(user3);
//		}
//    }

	private GamingEnvironment getGamingEnvironmentPlaystation1() {
        return gamingEnvironmentDao.findOneByExactTitle("Sony Playstation");
	}

	private GamingEnvironment getGamingEnvironmentPlaystation2() {
		return gamingEnvironmentDao.findOneByExactTitle("Playstation 2");
	}
}
