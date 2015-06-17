package org.oregami.test;

import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.junit.*;
import org.oregami.data.*;
import org.oregami.entities.*;
import org.oregami.entities.datalist.Script;
import org.oregami.entities.datalist.TitleType;
import org.oregami.util.StartHelper;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public class GamingEnvironmentTest {

	private static Injector injector;

	private static EntityManager entityManager = null;

    private Logger logger = Logger.getLogger(this.getClass());

	public GamingEnvironmentTest() {
	}

    @BeforeClass
    public static void initClass() {
        StartHelper.init(StartHelper.CONFIG_FILENAME_TEST);
        injector = StartHelper.getInjector();
		injector.getInstance(DatabaseFiller.class).initBaseLists();
        injector.getInstance(DatabaseFiller.class).addRegions();
        injector.getInstance(DatabaseFiller.class).addLanguages();
		//injector.getInstance(DatabaseFiller.class).initData();
        entityManager = injector.getInstance(EntityManager.class);
    }


    @AfterClass
    public static void finishClass() {
        injector.getInstance(DatabaseFiller.class).dropAllData();
    }


	@Test
	public void testGamingEnvironment() {
		GamingEnvironmentDao geDao = injector.getInstance(GamingEnvironmentDao.class);

        entityManager.getTransaction().begin();

        List<GamingEnvironment> findAll = geDao.findAll();
        int startSize = findAll.size();

		GamingEnvironment gamingEnvironmentPlaystation = new GamingEnvironment();
        String titleLatin = "Sony Playstation";
		PlatformTitle pt1 = TitleFactory.createPlatformTitle(
                StartHelper.getInstance(RegionDao.class).findByExactName(Region.UNITED_STATES),
                StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
                StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN),
                StartHelper.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH),
                titleLatin
        );
		gamingEnvironmentPlaystation.addTitle(pt1);

        String titleJapanese = "プレイステーション";
		PlatformTitle pt2 = TitleFactory.createPlatformTitle(
                StartHelper.getInstance(RegionDao.class).findByExactName(Region.JAPAN),
                StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
                StartHelper.getInstance(BaseListFinder.class).getScript(Script.JAPANESE),
                StartHelper.getInstance(LanguageDao.class).findByExactName(Language.JAPANESE),
                titleJapanese
        );
		gamingEnvironmentPlaystation.addTitle(pt2);

		String id1 = geDao.save(gamingEnvironmentPlaystation);

        entityManager.getTransaction().commit();

		findAll = geDao.findAll();
		Assert.assertNotNull(findAll);
		Assert.assertEquals(findAll.size(), startSize+1);

		Assert.assertNotNull(id1);

		GamingEnvironment gLoaded = geDao.findOne(id1);
		Assert.assertNotNull(gLoaded);

        GamingEnvironment oneByExactTitle = geDao.findOneByExactTitle(titleLatin);
        System.out.println(oneByExactTitle);
        Assert.assertNotNull(oneByExactTitle);
        Assert.assertEquals(id1, oneByExactTitle.getId());

        boolean latinTitleFound = false;
        boolean japanesTitleFound = false;

        Set<PlatformTitle> platformTitles = oneByExactTitle.getTitle();
        for (PlatformTitle pt : platformTitles) {
            if (titleLatin.equals(pt.getText().getText())) {
                latinTitleFound=true;
            } else if (titleJapanese.equals(pt.getText().getText())) {
                japanesTitleFound=true;
            }
        }
        Assert.assertTrue(latinTitleFound);
        Assert.assertTrue(japanesTitleFound);


        oneByExactTitle = geDao.findOneByExactTitle(titleLatin);
        System.out.println(oneByExactTitle);
        Assert.assertNotNull(oneByExactTitle);
        Assert.assertEquals(id1, oneByExactTitle.getId());

        latinTitleFound = false;
        japanesTitleFound = false;

        platformTitles = oneByExactTitle.getTitle();
        for (PlatformTitle pt : platformTitles) {
            if (titleLatin.equals(pt.getText().getText())) {
                latinTitleFound=true;
            } else if (titleJapanese.equals(pt.getText().getText())) {
                japanesTitleFound=true;
            }
        }
        Assert.assertTrue(latinTitleFound);
        Assert.assertTrue(japanesTitleFound);


    }


}
