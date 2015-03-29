package org.oregami.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oregami.data.GameTitleDao;
import org.oregami.entities.GameTitle;
import org.oregami.entities.Publication;
import org.oregami.entities.GameTitle;
import org.oregami.entities.PublicationIssue;
import org.oregami.test.PersistenceTest;

import javax.persistence.EntityManager;
import java.util.List;

public class TestGameTitleService {


    private static Injector injector;

    private static EntityManager entityManager;

    public TestGameTitleService() {
    }

    @BeforeClass
    public static void init() {
        JpaPersistModule jpaPersistModule = new JpaPersistModule("data");
        injector = Guice.createInjector(jpaPersistModule);
        injector.getInstance(PersistenceTest.class);
        PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();
        entityManager = injector.getInstance(EntityManager.class);
    }

    @Test
    public void testBasicPersistence() {

        entityManager.getTransaction().begin();

        GameTitle t = new GameTitle();
        t.setNativeSpelling("Secret of Monkey Island");

        entityManager.persist(t);
        entityManager.flush();

        GameTitleDao dao = injector.getInstance(GameTitleDao.class);
        List<GameTitle> list = dao.findAll();

        Assert.assertEquals(1, list.size());

        entityManager.getTransaction().rollback();
    }


    @Test
    public void testServiceUpdate() {

        entityManager.getTransaction().begin();

        GameTitle t = new GameTitle();
        t.setNativeSpelling("Secret of Monkey Island");

        entityManager.persist(t);
        entityManager.flush();

        GameTitleDao dao = injector.getInstance(GameTitleDao.class);
        List<GameTitle> list = dao.findAll();

        Assert.assertEquals(1, list.size());


        GameTitleService service = injector.getInstance(GameTitleService.class);
        GameTitle t2 = dao.findOne(t.getId());
        t.setNativeSpelling("Secret of Monkey Island updated");
        ServiceResult<GameTitle> serviceResult = service.updateEntity(t2, null);
        Assert.assertTrue(serviceResult.getErrors().isEmpty());

        GameTitle tLoaded = dao.findOne(t.getId());
        Assert.assertEquals(t2.getNativeSpelling(), tLoaded.getNativeSpelling());


        entityManager.getTransaction().rollback();
    }

}
