package org.oregami.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.OregamiApplication;
import org.oregami.entities.datalist.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sebastian on 16.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OregamiApplication.class)
//@ActiveProfiles("mysql")
public class TestData {

    @Autowired
    DatabaseFiller databaseFiller;

    @Autowired
    BaseListFiller baseListFiller;

    @Autowired
    BaseListFinder baseListFinder;

    @Autowired
    GameDao gameDao;

    @Autowired
    ScriptDao scriptDao;

    @Before
    public void init() {

        if (scriptDao.findAll().size()==0) {
            baseListFiller.initBaseLists();

            databaseFiller.addLanguages();
            databaseFiller.addRegions();
            databaseFiller.addGamingEnvironments();

            databaseFiller.addPublications();
            databaseFiller.addGames();

        }
    }

    @Test
    //@Transactional
    public void test() {

        List<Script> scriptList = scriptDao.findAll();
        for (Script s : scriptList) {
            System.out.println(s.toString());
        }
        /*
        Script script = baseListFinder.getScript(Script.LATIN);
        Assert.assertNotNull(script);
        */
        /*
        List<Game> games = gameDao.findAll();
        for (Game g : games) {
            System.out.println(g);
        }
        */

/*
        List<Game> monkeyList = gameDao.findByName("Monkey");
        for (Game g : monkeyList) {
            System.out.println(g);
        }
*/

    }

    @Test
    //@Transactional
    public void test2() {

        List<Script> scriptList = scriptDao.findAll();
        for (Script s : scriptList) {
            System.out.println(s.toString());
        }
    }


}
