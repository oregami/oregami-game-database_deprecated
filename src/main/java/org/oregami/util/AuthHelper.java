package org.oregami.util;

import com.google.inject.Inject;
import org.oregami.entities.user.User;
import org.oregami.service.IUserService;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by sebastian on 09.02.15.
 */
public class AuthHelper {

    public final static byte[] authKey;

    static {
        Random random = new SecureRandom();
        authKey = new byte[64];
        random.nextBytes(authKey);
    }

    @Inject
    IUserService userService;

    public User getUserByUsername(String username) {
        return userService.loadUserByUsername(username);
    }

    public boolean checkCredentials(String username, String unencryptedPassword) {
        User user = userService.loadUserByUsername(username);
        if (user!=null) {
            String encryptedPassword = Sha.hash256(unencryptedPassword);
            if (encryptedPassword.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
