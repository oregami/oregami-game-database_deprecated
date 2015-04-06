package org.oregami.dropwizard;

import com.google.inject.AbstractModule;
import org.oregami.service.IUserService;
import org.oregami.service.UserServiceImpl;

public class OregamiGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IUserService.class).to(UserServiceImpl.class);
	}

}
