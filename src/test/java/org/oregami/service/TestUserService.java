package org.oregami.service;

import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;
import org.oregami.data.UserDao;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.dropwizard.OregamiConfiguration;
import org.oregami.entities.user.User;
import org.oregami.util.MailHelper;

public class TestUserService {

    @ClassRule
    public static final DropwizardAppRule<OregamiConfiguration> RULE =
            new DropwizardAppRule<OregamiConfiguration>(OregamiApplication.class, "src/test/resources/oregami.yml");

    private UserServiceImpl userService;

    @Before
    public void setup() {
        userService = new UserServiceImpl();
        UserDao userRepository = Mockito.mock(UserDao.class);
        userService.setUserDao(userRepository);
    }

    @After
    public void tearDown() {
        userService = null;
    }

    @Test
    public void testRegisterInvalidUser() {
        User user = new User();

        ServiceResult<User> result = userService.register(user);
        Assert.assertEquals(false, result.wasSuccessful());
        Assert.assertEquals(true, result.hasErrors());
        Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext("user.email"), ServiceErrorMessage.USER_EMAIL_EMPTY)));
        Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext("user.password"), ServiceErrorMessage.USER_PASSWORD_EMPTY)));
        Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext("user.username"), ServiceErrorMessage.USER_USERNAME_EMPTY)));
        Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext("user.username"), ServiceErrorMessage.USER_USERNAME_TOO_SHORT)));
        Assert.assertEquals(4,  result.getErrors().size());
        
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setEmail("testuser@oregami.org");
        user.setUsername("testuser");
        user.setPassword("testpassword");

        userService.setMailhelper(Mockito.mock(MailHelper.class));

        ServiceResult<User> result = userService.register(user);
        Assert.assertEquals(true, result.wasSuccessful());
        Assert.assertEquals(false, result.hasErrors());

        //        Assert.assertNotNull(user.getRegistrationTime());
    }
    
    

}
