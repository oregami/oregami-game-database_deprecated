package org.oregami.test;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oregami.data.GameDao;
import org.oregami.data.GameEntryTypeDao;
import org.oregami.entities.Game;
import org.oregami.entities.GameTitle;
import org.oregami.entities.ReleaseGroup;
import org.oregami.entities.datalist.GameEntryType;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.jpa.JpaPersistModule;

public class PersistenceTest {

	private static Injector injector;
	
	public PersistenceTest() {
	}
	
	@BeforeClass
	public static void init() {
		JpaPersistModule jpaPersistModule = new JpaPersistModule("data");
		injector = Guice.createInjector(jpaPersistModule);
		injector.getInstance(PersistenceTest.class);
		PersistService persistService = injector.getInstance(PersistService.class);
		persistService.start();
	}
	
	@Test
	@Transactional
	public void testSaveGame() {
		Game game = new Game();
		game.addGameTitle(new GameTitle("Monkey Island"));
		
		GameDao gameDao = injector.getInstance(GameDao.class);
		Long gameId = gameDao.save(game);
		Assert.assertNotNull(gameId);
		
		Game loadedGame = gameDao.findOne(gameId);
		Assert.assertNotNull(loadedGame);
		Assert.assertEquals(game.getGameEntryType(), loadedGame.getGameEntryType());
	}
	
	@Test
	@Transactional
	public void testSaveGameEntryType() {
		GameEntryType gameEntryType = new GameEntryType();
		gameEntryType.setValue(GameEntryType.GAME);
		GameEntryTypeDao gameEntryTypeDao = injector.getInstance(GameEntryTypeDao.class);
		gameEntryTypeDao.save(gameEntryType);
		
		GameEntryType loadedGameEntryType = gameEntryTypeDao.findOne(gameEntryType.getId());
		Assert.assertNotNull(loadedGameEntryType);
		Assert.assertEquals(loadedGameEntryType.getValue(), gameEntryType.getValue());
	}	
	
	@Test
	@Transactional
	public void testSaveGameWithGameEntryType() {
		GameEntryType gameEntryType = new GameEntryType();
		gameEntryType.setValue(GameEntryType.GAME);
		GameEntryTypeDao gameEntryTypeDao = injector.getInstance(GameEntryTypeDao.class);
		gameEntryTypeDao.save(gameEntryType);
		
		Game game = new Game();
		game.addGameTitle(new GameTitle("Monkey Island"));
		game.setGameEntryType(gameEntryType);
		
		GameDao gameDao = injector.getInstance(GameDao.class);
		Long gameId = gameDao.save(game);
		Assert.assertNotNull(gameId);
		
		Game loadedGame = gameDao.findOne(gameId);
		Assert.assertNotNull(loadedGame);
		Assert.assertEquals(game.getGameEntryType(), loadedGame.getGameEntryType());

		
	}
	
	@Test
	@Transactional
	public void testSaveReleaseGroup() {
		ReleaseGroup releaseGroup = new ReleaseGroup();
//		releaseGroup.setReleaseType(ReleaseType.NATIVE_DEVELOPMENT);
		EntityManager entityManager = injector.getInstance(EntityManager.class);
		entityManager.getTransaction().begin();
		entityManager.persist(releaseGroup);
		entityManager.flush();
		entityManager.getTransaction().commit();
		Long id = releaseGroup.getId();
		Assert.assertNotNull(id);
		
		entityManager.getTransaction().begin();
		ReleaseGroup rgLoaded = entityManager.find(ReleaseGroup.class, id);
		entityManager.getTransaction().commit();
		Assert.assertNotNull(rgLoaded);
		Assert.assertEquals(rgLoaded.getReleaseType(), releaseGroup.getReleaseType());
		
	}
	
	
	
}
