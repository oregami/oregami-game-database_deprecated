package org.oregami.dropwizard;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import org.oregami.entities.user.User;

import com.google.common.base.Optional;

public class OregamiAuthenticator implements Authenticator<BasicCredentials, User> {
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if ("auth".equals(credentials.getUsername())
        	&& "secret".equals(credentials.getPassword())) {
        	User u = new User();
        	u.setUsername("auth");
            return Optional.of(u);
        }
        return Optional.absent();
    }
}
