package org.oregami.data;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.OregamiApplication;
import org.oregami.entities.Language;
import org.oregami.entities.PublicationFranchise;
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
public class TestPublicationFranchiseDao {

    @Autowired
    PublicationFranchiseDao publicationFranchiseDao;

    @Test
    public void test() {
        PublicationFranchise publicationFranchise = new PublicationFranchise();
        String id = publicationFranchiseDao.save(publicationFranchise);

        //Language loaded = languageDao.findOne(id);
        //Assert.assertThat(loaded.getName(), Matchers.is("German"));



    }

}
