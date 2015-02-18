package org.oregami.service;

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
import org.mockito.Mockito;
import org.oregami.data.*;
import org.oregami.dropwizard.MailConfiguration;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.dropwizard.OregamiGuiceModule;
import org.oregami.entities.datalist.DemoContentType;
import org.oregami.entities.datalist.GameEntryType;
import org.oregami.entities.datalist.ReleaseType;
import org.oregami.entities.user.User;
import org.oregami.test.PersistenceTest;
import org.oregami.util.AuthHelper;
import org.oregami.util.MailHelper;

import javax.activation.MailcapCommandMap;
import java.security.SecureRandom;
import java.util.Random;

public class TestAuthHelper {

    private static Injector injector;

    public TestAuthHelper() {
    }

    @BeforeClass
    public static void init() {
        JpaPersistModule jpaPersistModule = new JpaPersistModule(OregamiApplication.JPA_UNIT);
        injector = Guice.createInjector(jpaPersistModule, new OregamiGuiceModule());
        injector.getInstance(PersistenceTest.class);
        PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();
        MailHelper.init(Mockito.mock(MailConfiguration.class));
    }

    @AfterClass
    public static void finish() {
        DatabaseFiller.getInstance().dropAllData();
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
