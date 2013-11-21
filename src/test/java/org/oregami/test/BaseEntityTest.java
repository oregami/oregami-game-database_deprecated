package org.oregami.test;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import org.oregami.entities.Game;
import org.oregami.entities.GameTitle;
import org.oregami.entities.ReleaseGroup;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.jpa.JpaPersistModule;

public class BaseEntityTest {

	private static Injector injector;

	public BaseEntityTest() {
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
	public void testEquals() {
		ReleaseGroup releaseGroup = new ReleaseGroup();
		Game game = new Game();
		Game game2 = new Game();

		assertFalse("objects of different classes are not equal.", releaseGroup.equals(game));

		assertThat("identical objects are equal", game, is(game));

		EntityManager entityManager = injector.getInstance(EntityManager.class);
		entityManager.getTransaction().begin();
		entityManager.persist(game);
		entityManager.flush();
		entityManager.getTransaction().commit();

		assertThat("persisted objects are not equal to non-persisted objects", game, is(not(game2)));

		Game gameFromDb = entityManager.find(Game.class, game.getId());

		assertThat("two objects that refer to the same entity are equal", game, is(gameFromDb));

	}

}
