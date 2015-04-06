package org.oregami.test;

import com.google.inject.Injector;
import com.google.inject.persist.Transactional;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oregami.data.BaseListFinder;
import org.oregami.data.DatabaseFiller;
import org.oregami.data.GameEntryTypeDao;
import org.oregami.entities.datalist.DemoContentType;
import org.oregami.entities.datalist.GameEntryType;
import org.oregami.entities.datalist.ReleaseType;
import org.oregami.util.StartHelper;

public class FullDataTest {

    public FullDataTest() {
    }

    @BeforeClass
    public static void initClass() {
        StartHelper.init(StartHelper.CONFIG_FILENAME_TEST);
        StartHelper.getInstance(DatabaseFiller.class).initData();
    }

    @AfterClass
    public static void finish() {
        StartHelper.getInstance(DatabaseFiller.class).dropAllData();
    }


    @Test
    @Transactional
    public void testGeneric() {
        GameEntryType compilationLoaded = StartHelper.getInstance(BaseListFinder.class).getGameEntryType(GameEntryType.EPISODE);
        Assert.assertNotNull(compilationLoaded);

        DemoContentType demoContentType = StartHelper.getInstance(BaseListFinder.class).getDemoContentType(DemoContentType.ABSOLUTE_PLAY_COUNT_LIMIT);
        Assert.assertNotNull(demoContentType);

        ReleaseType releaseType = StartHelper.getInstance(BaseListFinder.class).getReleaseType(ReleaseType.EMULATOR_RELEASE);
        Assert.assertNotNull(releaseType);
    }


    @Test
    @Transactional
    public void testGenericBaseListDao() {
        GameEntryTypeDao gameEntryTypeDao = StartHelper.getInstance(GameEntryTypeDao.class);
        GameEntryType gameEntryType = gameEntryTypeDao.findByName(GameEntryType.COMPILATION);
        Assert.assertNotNull(gameEntryType);
        Assert.assertEquals(GameEntryType.COMPILATION,gameEntryType.getValue());
    }

}
