package org.oregami.service;

import com.google.inject.Injector;
import com.google.inject.persist.Transactional;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.oregami.data.DatabaseFiller;
import org.oregami.data.UserDao;
import org.oregami.dropwizard.MailConfiguration;
import org.oregami.entities.user.User;
import org.oregami.rest.RestTestHelper;
import org.oregami.util.AuthHelper;
import org.oregami.util.MailHelper;
import org.oregami.util.StartHelper;

public class TestAuthHelper {

    private static Injector injector;

    public TestAuthHelper() {
    }

    @BeforeClass
    public static void initClass() {
        StartHelper.init(StartHelper.CONFIG_FILENAME_TEST);
        injector = StartHelper.getInjector();
        RestTestHelper.initRestAssured();
        MailHelper.init(Mockito.mock(MailConfiguration.class));
    }


    @AfterClass
    public static void finish() {
        StartHelper.getInstance(DatabaseFiller.class).dropAllData();
    }

    @Test
    @Transactional
    public void testGeneric() {

        UserDao userDao = injector.getInstance(UserDao.class);

        User user = new User();
        user.setUsername("user1");
        user.setPasswordAndEncryptIt("password1");
        user.setEmail("test@oregami.org");
        userDao.save(user);
        AuthHelper helper = injector.getInstance(AuthHelper.class);
        User user1 = helper.getUserByUsername("user1");
        Assert.assertNotNull(user1);
        Assert.assertEquals("user1", user1.getUsername());

        Assert.assertNotNull(AuthHelper.authKey);

        Assert.assertTrue(helper.checkCredentials("user1", "password1"));
        Assert.assertFalse(helper.checkCredentials("user1", "wrong password"));

    }



}
