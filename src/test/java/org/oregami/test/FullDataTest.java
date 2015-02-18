package org.oregami.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oregami.data.BaseListFiller;
import org.oregami.data.BaseListFinder;
import org.oregami.data.DatabaseFiller;
import org.oregami.data.GameEntryTypeDao;
import org.oregami.entities.datalist.DemoContentType;
import org.oregami.entities.datalist.GameEntryType;
import org.oregami.entities.datalist.ReleaseType;

public class FullDataTest {

    private static Injector injector;

    private Logger logger = Logger.getLogger(this.getClass());

    public FullDataTest() {
    }

    @BeforeClass
    public static void init() {
        JpaPersistModule jpaPersistModule = new JpaPersistModule("data");
        injector = Guice.createInjector(jpaPersistModule);
        injector.getInstance(PersistenceTest.class);
        PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();
        BaseListFiller.instance().initBaseLists();
    }

    @AfterClass
    public static void finish() {
        DatabaseFiller.getInstance().dropAllData();
    }


    @Test
    @Transactional
    public void testGeneric() {
        GameEntryType compilationLoaded = BaseListFinder.instance().getGameEntryType(GameEntryType.EPISODE);
        if (logger.isDebugEnabled()) {
            logger.debug("gameEntryDao: " + compilationLoaded);
        }
        Assert.assertNotNull(compilationLoaded);

        DemoContentType demoContentType = BaseListFinder.instance().getDemoContentType(DemoContentType.ABSOLUTE_PLAY_COUNT_LIMIT);
        if (logger.isDebugEnabled()) {
            logger.debug(demoContentType);
        }
        Assert.assertNotNull(demoContentType);

        ReleaseType releaseType = BaseListFinder.instance().getReleaseType(ReleaseType.EMULATOR_RELEASE);
        if (logger.isDebugEnabled()) {
            logger.debug(releaseType);
        }
        Assert.assertNotNull(releaseType);
    }


    @Test
    @Transactional
    public void testGenericBaseListDao() {
        GameEntryTypeDao gameEntryTypeDao = injector.getInstance(GameEntryTypeDao.class);
        GameEntryType gameEntryType = gameEntryTypeDao.findByName(GameEntryType.COMPILATION);
        Assert.assertNotNull(gameEntryType);
        Assert.assertEquals(GameEntryType.COMPILATION,gameEntryType.getValue());
    }

}
