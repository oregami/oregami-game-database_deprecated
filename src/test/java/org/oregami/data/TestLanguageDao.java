package org.oregami.data;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.OregamiApplication;
import org.oregami.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sebastian on 16.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OregamiApplication.class)
@ActiveProfiles("hsqldb")
public class TestLanguageDao {

    @Autowired
    LanguageDao languageDao;

    @Autowired
    DatabaseFiller databaseFiller;

    @Before
    public void init() {
        databaseFiller.addLanguages();
        databaseFiller.addPublications();
    }

    @Test
    public void test() {
        Language l = new Language();
        l.setName("German");
        String id = languageDao.save(l);

        Language loaded = languageDao.findOne(id);
        Assert.assertThat(loaded.getName(), Matchers.is("German"));

    }


}
