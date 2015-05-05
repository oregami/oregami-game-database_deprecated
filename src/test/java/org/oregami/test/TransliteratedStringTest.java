package org.oregami.test;

import com.google.inject.Injector;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oregami.data.*;
import org.oregami.entities.HardwarePlatform;
import org.oregami.entities.Language;
import org.oregami.entities.TransliteratedString;
import org.oregami.entities.datalist.Script;
import org.oregami.util.StartHelper;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sebastian on 25.04.15.
 */
public class TransliteratedStringTest {

    private static Injector injector;

    static EntityManager entityManager = null;

    @BeforeClass
    public static void initClass() {
        StartHelper.init(StartHelper.CONFIG_FILENAME_TEST);
        injector = StartHelper.getInjector();
        entityManager = injector.getInstance(EntityManager.class);
        injector.getInstance(DatabaseFiller.class).addLanguages();
        injector.getInstance(BaseListFiller.class).initBaseLists();
    }

    @AfterClass
    public static void afterClass() {
        injector.getInstance(DatabaseFiller.class).dropAllData();
    }

    @Test
    public void saveAndReadEntity() {
        entityManager.getTransaction().begin();

        TransliteratedStringDao dao = injector.getInstance(TransliteratedStringDao.class);

        TransliteratedString s = new TransliteratedString();
        Language l = injector.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH);
        s.setLanguage(l);
        Script script = injector.getInstance(BaseListFinder.class).getScript(Script.LATIN);
        s.setScript(script);
        s.setText("a latin text");

        dao.save(s);

        String id = s.getId();

        entityManager.getTransaction().commit();


        entityManager.getTransaction().begin();
        TransliteratedString loadedEntity = dao.findOne(id);
        System.out.println(loadedEntity);
        Assert.assertThat(loadedEntity, Matchers.is(Matchers.notNullValue()));
        Assert.assertThat(loadedEntity.getId(), Matchers.is(id));
        Assert.assertThat(loadedEntity.getText(), Matchers.is(s.getText()));
        entityManager.getTransaction().commit();

    }

    @Test
    public void saveAndReadEntityChinese() {
        entityManager.getTransaction().begin();

        TransliteratedStringDao dao = injector.getInstance(TransliteratedStringDao.class);

        TransliteratedString s = new TransliteratedString();
        Language l = injector.getInstance(LanguageDao.class).findByExactName(Language.CHINESE);
        s.setLanguage(l);
        Script script = injector.getInstance(BaseListFinder.class).getScript(Script.CHINESE);
        s.setScript(script);
        s.setText("计算机"); //chinese for "computer"

        dao.save(s);

        String id = s.getId();

        entityManager.getTransaction().commit();


        entityManager.getTransaction().begin();
        TransliteratedString loadedEntity = dao.findOne(id);
        System.out.println(loadedEntity);
        Assert.assertThat(loadedEntity, Matchers.is(Matchers.notNullValue()));
        Assert.assertThat(loadedEntity.getId(), Matchers.is(id));
        Assert.assertThat(loadedEntity.getText(), Matchers.is(s.getText()));
        entityManager.getTransaction().commit();

    }

    @Test
    public void saveAndReadHardwarePlatform() {
        entityManager.getTransaction().begin();

        TransliteratedStringDao transliteratedStringDao = injector.getInstance(TransliteratedStringDao.class);


        TransliteratedString playstationLatinEnglish = new TransliteratedString();
        Language langEnglish = injector.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH);
        playstationLatinEnglish.setLanguage(langEnglish);
        Script scriptLatin = injector.getInstance(BaseListFinder.class).getScript(Script.LATIN);
        playstationLatinEnglish.setScript(scriptLatin);
        playstationLatinEnglish.setText("Sony Playstation");
        transliteratedStringDao.save(playstationLatinEnglish);
        String playstationLatinEnglishId = playstationLatinEnglish.getId();

        TransliteratedString playstationJapanese = new TransliteratedString();
        Language langJapanese = injector.getInstance(LanguageDao.class).findByExactName(Language.JAPANESE);
        playstationJapanese.setLanguage(langJapanese);
        Script scriptJapanese = injector.getInstance(BaseListFinder.class).getScript(Script.JAPANESE);
        playstationJapanese.setScript(scriptJapanese);
        playstationJapanese.setText("プレイステーション");
        transliteratedStringDao.save(playstationJapanese);
        String playstationJapaneseId = playstationJapanese.getId();
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        HardwarePlatform hp = new HardwarePlatform();
        hp.addTitle(playstationJapanese);
        hp.addTitle(playstationLatinEnglish);
        HardwarePlatformDao hardwarePlatformDao = injector.getInstance(HardwarePlatformDao.class);
        hardwarePlatformDao.save(hp);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        List<HardwarePlatform> hardwarePlatformList = hardwarePlatformDao.findAll();
        Assert.assertThat(hardwarePlatformList, Matchers.notNullValue());
        Assert.assertThat(hardwarePlatformList.size(), Matchers.is(1));
        HardwarePlatform hardwarePlatformLoaded = hardwarePlatformList.get(0);
        Assert.assertThat(hardwarePlatformLoaded.getId(), Matchers.equalTo(hp.getId()));
        System.out.println(hardwarePlatformLoaded);
        entityManager.getTransaction().commit();

    }

}
