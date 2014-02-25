package org.oregami.dropwizard;

import org.oregami.entities.user.User;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;

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
