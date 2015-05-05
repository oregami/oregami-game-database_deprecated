package org.oregami.test;

import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.junit.*;
import org.oregami.data.*;
import org.oregami.entities.*;
import org.oregami.entities.datalist.GameEntryType;
import org.oregami.entities.datalist.ReleaseType;
import org.oregami.entities.datalist.Script;
import org.oregami.entities.datalist.TitleType;
import org.oregami.entities.user.User;
import org.oregami.util.StartHelper;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PersistenceTest {

	private static Injector injector;

	EntityManager entityManager = null;

    private Logger logger = Logger.getLogger(this.getClass());

	public PersistenceTest() {
	}

    @BeforeClass
    public static void initClass() {
        StartHelper.init(StartHelper.CONFIG_FILENAME_TEST);
        injector = StartHelper.getInjector();
    }

	@Before
	public void startTx() {
		if (entityManager==null) {
			entityManager = injector.getInstance(EntityManager.class);
		}
		entityManager.getTransaction().begin();
	}

	@After
	public void rollbackTx() {
		entityManager.getTransaction().rollback();
	}

	private <T> T getInstance(Class<T> c) {
		return injector.getInstance(c);
	}

	@Test
	public void testSaveGame() {
		Game game = new Game();
		game.connectGameTitle(new GameTitle("The Secret of Monkey Island"), new TitleType(TitleType.ORIGINAL_TITLE));

		GameDao gameDao = getInstance(GameDao.class);
		String gameId = gameDao.save(game);
		Assert.assertNotNull(gameId);

		Game loadedGame = gameDao.findOne(gameId);
		Assert.assertNotNull(loadedGame);
		Assert.assertEquals(game.getGameEntryType(), loadedGame.getGameEntryType());
	}

	@Test
	public void testSaveGameEntryType() {
		GameEntryType gameEntryType = new GameEntryType(GameEntryType.GAME);
		GameEntryTypeDao gameEntryTypeDao = getInstance(GameEntryTypeDao.class);
		gameEntryTypeDao.save(gameEntryType);

		GameEntryType loadedGameEntryType = gameEntryTypeDao.findOne(gameEntryType.getId());
		Assert.assertNotNull(loadedGameEntryType);
		Assert.assertEquals(loadedGameEntryType.getValue(), gameEntryType.getValue());
	}



	@Test
	public void testSaveMultipleGameEntryTypes() {
		GameEntryTypeDao gameEntryTypeDao = getInstance(GameEntryTypeDao.class);

		GameEntryType gameEntryType = new GameEntryType(GameEntryType.GAME);
		gameEntryTypeDao.save(gameEntryType);

		GameEntryType gameEntryType2 = new GameEntryType(GameEntryType.COMPILATION);
		gameEntryTypeDao.save(gameEntryType2);

		GameEntryType gameEntryType3 = new GameEntryType(GameEntryType.ADD_ON_SIGNIFICANT);
		gameEntryTypeDao.save(gameEntryType3);

		List<GameEntryType> all = gameEntryTypeDao.findAll();
		Assert.assertNotNull(all);
		Assert.assertEquals(3, all.size());
	}


	/**
	 * if a Game is deleted, does the associated GameEntryType stay in the database?
	 * This works because the Game.GameEntryType field does NOT have CascadeType.ALL.
	 */
	@Test
	public void testSaveAndDeleteGameWithMultipleGameEntryTypes() {
		GameEntryTypeDao gameEntryTypeDao = getInstance(GameEntryTypeDao.class);
		GameDao gameDao = getInstance(GameDao.class);
		GameTitleDao gameTitleDao = getInstance(GameTitleDao.class);

		GameTitle gameTitle = new GameTitle("The Secret of Monkey Island");
		gameTitleDao.save(gameTitle);

		TitleTypeDao titleTypeDao = getInstance(TitleTypeDao.class);
		TitleType titleType = new TitleType(TitleType.ORIGINAL_TITLE);
		titleTypeDao.save(titleType);

		GameEntryType gameEntryType = new GameEntryType(GameEntryType.GAME);
		gameEntryTypeDao.save(gameEntryType);

		GameEntryType gameEntryType2 = new GameEntryType(GameEntryType.COMPILATION);
		gameEntryTypeDao.save(gameEntryType2);

		GameEntryType gameEntryType3 = new GameEntryType(GameEntryType.ADD_ON_SIGNIFICANT);
		gameEntryTypeDao.save(gameEntryType3);

		Game game = new Game();
		game.connectGameTitle(gameTitle, titleType);
		game.setGameEntryType(gameEntryType);
		String gameId = gameDao.save(game);
		Assert.assertNotNull(gameId);

		List<GameEntryType> allGameEntryTypes = gameEntryTypeDao.findAll();
		Assert.assertNotNull(allGameEntryTypes);
		Assert.assertEquals(allGameEntryTypes.size(), 3);

		List<Game> allGames = gameDao.findAll();
		Assert.assertNotNull(allGames);
		Assert.assertEquals(allGames.size(), 1);

		gameDao.delete(game);

		allGames = gameDao.findAll();
		Assert.assertNotNull(allGames);
		Assert.assertEquals(allGames.size(), 0);

		allGameEntryTypes = gameEntryTypeDao.findAll();
		Assert.assertNotNull(allGameEntryTypes);
		Assert.assertEquals(allGameEntryTypes.size(), 3);

	}

	@Test
	public void testSaveGameWithGameEntryType() {

		GameEntryType gameEntryType = new GameEntryType(GameEntryType.GAME);
		GameEntryTypeDao gameEntryTypeDao = getInstance(GameEntryTypeDao.class);
		gameEntryTypeDao.save(gameEntryType);

		GameEntryType loadedGameEntryType = gameEntryTypeDao.findOne(gameEntryType.getId());
		Assert.assertNotNull(loadedGameEntryType);
		Assert.assertEquals(loadedGameEntryType.getValue(), gameEntryType.getValue());

		GameDao gameDao = getInstance(GameDao.class);

		Game game = new Game();
		game.setGameEntryType(gameEntryType);
		String gameId = gameDao.save(game);
		Assert.assertNotNull(gameId);

		Game game2 = new Game();
		game2.setGameEntryType(gameEntryType);
		String gameId2 = gameDao.save(game2);
		Assert.assertNotNull(gameId2);

		Game loadedGame = gameDao.findOne(gameId);
		Assert.assertNotNull(loadedGame);
		Assert.assertEquals(game.getGameEntryType(), loadedGame.getGameEntryType());

		Game loadedGame2 = gameDao.findOne(gameId2);
		Assert.assertNotNull(loadedGame2);
		Assert.assertEquals(game2.getGameEntryType(), loadedGame2.getGameEntryType());

		List<Game> allGames = gameDao.findAll();
		Assert.assertEquals(2, allGames.size());


	}


	@Test
	public void testSaveReleaseGroup() {

		ReleaseType releaseType = new ReleaseType(ReleaseType.NATIVE_DEVELOPMENT);
		entityManager.persist(releaseType);

		ReleaseGroup releaseGroup = new ReleaseGroup();
		releaseGroup.setReleaseType(releaseType);
		entityManager.persist(releaseGroup);
		String id = releaseGroup.getId();
		Assert.assertNotNull(id);

		ReleaseGroup rgLoaded = entityManager.find(ReleaseGroup.class, id);
		Assert.assertNotNull(rgLoaded);
		Assert.assertEquals(rgLoaded.getReleaseType(), releaseGroup.getReleaseType());

	}

	@Test
	public void testSaveGameTitle() {

		StartHelper.getInstance(DatabaseFiller.class).addLanguages();

		LanguageDao languageDao = getInstance(LanguageDao.class);
		Language languageEn = languageDao.findByExactName(Language.ENGLISH);

		GameTitleDao titleDao = getInstance(GameTitleDao.class);
		GameTitle title = new GameTitle("The Secret of Monkey Island");
		title.setLanguage(languageEn);
		String id1 = titleDao.save(title);

		Assert.assertEquals(titleDao.findAll().size(), 1);

		GameTitle title2 = new GameTitle("Le Secret de L'Ile aux Singes");
		titleDao.save(title2);

		Assert.assertEquals(titleDao.findAll().size(), 2);

		GameTitle loadedGameTitle = titleDao.findOne(id1);
		Assert.assertEquals(loadedGameTitle.getLanguage(), languageEn);


	}


	@Test
	public void testSaveGameWithGameTitle() {
		GameTitleDao titleDao = getInstance(GameTitleDao.class);
		GameTitle title = new GameTitle("The Secret of Monkey Island");
		titleDao.save(title);
		GameTitle title2 = new GameTitle("Monkey Island");
		titleDao.save(title2);
		Assert.assertEquals(titleDao.findAll().size(), 2);

		TitleTypeDao titleTypeDao = getInstance(TitleTypeDao.class);
		TitleType titleType = new TitleType(TitleType.ORIGINAL_TITLE);
		TitleType titleType2 = new TitleType(TitleType.ABBREVIATION);
		titleTypeDao.save(titleType);
		titleTypeDao.save(titleType2);

		Assert.assertEquals(titleTypeDao.findAll().size(), 2);

		GameDao gameDao = getInstance(GameDao.class);
		Game game = new Game();
		game.connectGameTitle(title, titleType);
		game.connectGameTitle(title2, titleType2);
		String gameId = gameDao.save(game);

		Game gameLoaded = gameDao.findOne(gameId);
		Set<GameToGameTitleConnection> connectionList = gameLoaded.getGameToGameTitleConnectionList();
		Assert.assertEquals(connectionList.size(), 2);

		GameToGameTitleConnection gameTitleConnectionLoaded = connectionList.iterator().next();
		Assert.assertNotNull(gameTitleConnectionLoaded);

		Assert.assertEquals(titleTypeDao.findAll().size(), 2);

		//delete Game an test if GameTitle objects stay in the database
		gameDao.delete(game);
		List<Game> allGames = gameDao.findAll();
		Assert.assertNotNull(allGames);
		Assert.assertEquals(allGames.size(), 0);
		Assert.assertEquals(titleTypeDao.findAll().size(), 2);

	}

	@Test
	public void testSaveGameWithGameTitle2() {
		GameTitleDao titleDao = getInstance(GameTitleDao.class);
		GameTitle title = new GameTitle("The Secret of Monkey Island");
		titleDao.save(title);
		GameTitle title2 = new GameTitle("Monkey Island");
		titleDao.save(title2);
		Assert.assertEquals(titleDao.findAll().size(), 2);

		TitleTypeDao titleTypeDao = getInstance(TitleTypeDao.class);
		TitleType titleType = new TitleType(TitleType.ORIGINAL_TITLE);
		TitleType titleType2 = new TitleType(TitleType.ABBREVIATION);
		titleTypeDao.save(titleType);
		titleTypeDao.save(titleType2);

		Assert.assertEquals(titleTypeDao.findAll().size(), 2);

		GameDao gameDao = getInstance(GameDao.class);
		Game game = new Game();
		game.connectGameTitle(title, titleType);
		game.connectGameTitle(title2, titleType2);
		String gameId = gameDao.save(game);

		Game gameLoaded = gameDao.findOne(gameId);
		Set<GameToGameTitleConnection> connectionList = gameLoaded.getGameToGameTitleConnectionList();
		Assert.assertEquals(connectionList.size(), 2);

		GameToGameTitleConnection gameTitleConnectionLoaded = connectionList.iterator().next();
		Assert.assertNotNull(gameTitleConnectionLoaded);
		//Assert.assertEquals(gameTitleConnectionLoaded.getTitleType(), titleType2);

		Assert.assertEquals(titleTypeDao.findAll().size(), 2);

	}


	@Test
	public void testLanguage() {
		LanguageDao languageDao = injector.getInstance(LanguageDao.class);

		Language german = new Language(Language.GERMAN);
		String id = languageDao.save(german);
		Assert.assertNotNull("ID expected", id);

		List<Language> all = languageDao.findAll();
		Assert.assertTrue("1 language expected", all.size()==1);

		Language english = new Language(Language.ENGLISH);
		String id2 = languageDao.save(english);

		all = languageDao.findAll();
		Assert.assertTrue("2 languages expected", all.size()==2);

		Language loadedGerman = languageDao.findByExactName(Language.GERMAN);
		Assert.assertNotNull(loadedGerman);
		Assert.assertEquals(loadedGerman.getId(), id);
		Assert.assertEquals(loadedGerman, german);

		Language loadedEnglish = languageDao.findByExactName(Language.ENGLISH);
		Assert.assertNotNull(loadedEnglish);
		Assert.assertEquals(loadedEnglish.getId(), id2);
		Assert.assertEquals(loadedEnglish, english);

	}


	@Test
	public void testRegion() {
		RegionDao regionDao = injector.getInstance(RegionDao.class);

		Region germany = new Region(Region.GERMANY);
		String id1 = regionDao.save(germany);
		Assert.assertNotNull("ID expected", id1);

		List<Region> all = regionDao.findAll();
		Assert.assertTrue("1 Region expected", all.size()==1);

		Region europe = new Region(Region.EUROPE);
		String id2 = regionDao.save(europe);

		all = regionDao.findAll();
		Assert.assertTrue("2 Regions expected", all.size()==2);

		Region loadedGermany = regionDao.findByExactName(Region.GERMANY);
		Assert.assertNotNull(loadedGermany);
		Assert.assertEquals(loadedGermany.getId(), id1);
		Assert.assertEquals(loadedGermany, germany);

		Region loadedEurope = regionDao.findByExactName(Region.EUROPE);
		Assert.assertNotNull(loadedEurope);
		Assert.assertEquals(loadedEurope.getId(), id2);
		Assert.assertEquals(loadedEurope, europe);
	}

	@Test
	public void testPublication() {
		PublicationFranchiseDao publicationFranchiseDao = injector.getInstance(PublicationFranchiseDao.class);
		PublicationFranchise pf = new PublicationFranchise("Power Play");

		Publication publicationPowerPlay = new Publication("Power Play");
		pf.getPublicationList().add(publicationPowerPlay);

		PublicationIssue issue = new PublicationIssue();
		issue.setIssueNameYear(1990);
		issue.setIssueNameNumber(1);
		publicationPowerPlay.getPublicationIssueList().add(issue);
		PublicationIssue issue2 = new PublicationIssue();
		issue2.setIssueNameYear(1990);
		issue2.setIssueNameNumber(2);
		issue2.setReleaseDate(new LocalDate(1990,3,16));


		publicationPowerPlay.getPublicationIssueList().add(issue2);

		Publication publicationChipPowerPlay = new Publication("CHIP Power Play");
		pf.getPublicationList().add(publicationChipPowerPlay);

		publicationFranchiseDao.save(pf);

		List<PublicationFranchise> findAll = publicationFranchiseDao.findAll();
		Assert.assertNotNull(findAll);
		Assert.assertTrue(findAll.size()==1);

		PublicationFranchise franchiseLoaded = findAll.get(0);
		Assert.assertEquals(franchiseLoaded.getId(), pf.getId());
		Assert.assertEquals(franchiseLoaded.getName(), pf.getName());

		boolean releaseDateFound = false;
		LocalDate localDate = new LocalDate(1990,3,16);

		Iterator<Publication> publicationIterator = franchiseLoaded.getPublicationList().iterator();
		while (publicationIterator.hasNext()) {
			Publication publication = publicationIterator.next();

			Iterator<PublicationIssue> issueIterator = publication.getPublicationIssueList().iterator();

			while (issueIterator.hasNext()) {
				PublicationIssue publicationIssue = issueIterator.next();
				if (localDate.equals(publicationIssue.getReleaseDate())) {
					releaseDateFound=true;
				}
			}
		}

		Assert.assertTrue("release date not found", releaseDateFound);

		if (logger.isDebugEnabled()) {
            logger.debug(pf);
        }
	}

	@Test
	public void testUser() {
		UserDao userDao = injector.getInstance(UserDao.class);
		User user = new User();
		user.setUsername("gene");
		user.setEmail("gene@kultpower.de");

		String id1 = userDao.save(user);

		Assert.assertNotNull(id1);

		User userLoaded = userDao.findOne(id1);
		Assert.assertNotNull(userLoaded);
		Assert.assertEquals(userLoaded.getUsername(), user.getUsername());

	}


	@Test
	public void testWebsite() {
		WebsiteDao websiteDao = injector.getInstance(WebsiteDao.class);

		Website website = new Website();
		website.setImage(new byte[0]);
		website.setThumbnail(new byte[0]);

		String id = websiteDao.save(website);

		List<Website> findAll = websiteDao.findAll();

		Assert.assertTrue(findAll.size()==1);

	}



	@Test
	public void testGamingEnvironment() {
		GamingEnvironmentDao geDao = injector.getInstance(GamingEnvironmentDao.class);

		GamingEnvironment g = new GamingEnvironment("SONY_PLAYSTATION");

		String id1 = geDao.save(g);

		List<GamingEnvironment> findAll = geDao.findAll();
		Assert.assertNotNull(findAll);
		Assert.assertEquals(findAll.size(), 1);

		Assert.assertNotNull(id1);

		GamingEnvironment gLoaded = geDao.findOne(id1);
		Assert.assertNotNull(gLoaded);
		Assert.assertEquals(gLoaded.getTitle(), g.getTitle());

	}


	@Test
	public void testScript() {

		ScriptDao dao = injector.getInstance(ScriptDao.class);

        Language langEnglish = StartHelper.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH);

		Script s = new Script(Script.LATIN);
		String id = dao.save(s);
		Assert.assertNotNull("ID expected", id);

		List<Script> all = dao.findAll();
		Assert.assertEquals("1 script expected", 1, all.size());

		Script s2 = new Script(Script.ARABIC);
        String id2 = dao.save(s2);

		all = dao.findAll();
		Assert.assertEquals("2 scripts expected", 2, all.size());

		Script loadedObject = dao.findByExactName(Script.LATIN);
		Assert.assertNotNull(dao);
		Assert.assertEquals(loadedObject.getId(), id);
		Assert.assertEquals(loadedObject, s);

		Script loadedObject2 = dao.findByExactName(Script.ARABIC);
		Assert.assertNotNull(loadedObject2);
		Assert.assertEquals(loadedObject2.getId(), id2);
		Assert.assertEquals(loadedObject2, s2);

	}



}
