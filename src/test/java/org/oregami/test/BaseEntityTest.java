package org.oregami.test;

import com.google.inject.Injector;
import com.google.inject.persist.Transactional;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oregami.data.DatabaseFiller;
import org.oregami.data.GameDao;
import org.oregami.entities.Game;
import org.oregami.entities.ReleaseGroup;
import org.oregami.util.StartHelper;

import javax.persistence.EntityManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class BaseEntityTest {

	private static Injector injector;

    private static EntityManager entityManager = null;

	public BaseEntityTest() {
	}

    @BeforeClass
    public static void initClass() {
        StartHelper.init(StartHelper.CONFIG_FILENAME_TEST);
        injector = StartHelper.getInjector();
        entityManager = injector.getInstance(EntityManager.class);
    }


	@Test
	public void testEquals() {

		entityManager.getTransaction().begin();

		ReleaseGroup releaseGroup = new ReleaseGroup();
		Game game = new Game();
		Game game2 = new Game();

		assertFalse("objects of different classes are not equal.", releaseGroup.equals(game));

		assertThat("identical objects are equal", game, is(game));


		entityManager.persist(game);
		entityManager.flush();


		assertThat("persisted objects are not equal to non-persisted objects", game, is(not(game2)));

		Game gameFromDb = entityManager.find(Game.class, game.getId());

		assertThat("two objects that refer to the same entity are equal", game, is(gameFromDb));

        entityManager.getTransaction().rollback();
	}



	@Test
	@Transactional
	public void testDeleteGameData() {

		GameDao gameDao = injector.getInstance(GameDao.class);

		Game game2 = new Game();
        gameDao.save(game2);

		int sizeBeforeDelete = gameDao.findAll().size();
		Assert.assertTrue(sizeBeforeDelete==1);

		StartHelper.getInstance(DatabaseFiller.class).deleteGameData();

		int sizeAfterDelete = gameDao.findAll().size();
		Assert.assertTrue(sizeAfterDelete==0);

	}

}
