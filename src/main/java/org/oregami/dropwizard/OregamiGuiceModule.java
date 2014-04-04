package org.oregami.dropwizard;

import org.oregami.service.IUserService;
import org.oregami.service.UserServiceImpl;

import com.google.inject.AbstractModule;

public class OregamiGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		
//		bind(new TypeLiteral<GenericDAO<Game, Long>>() {}).to(GameDao.class);
		bind(IUserService.class).to(UserServiceImpl.class);
//		bind(GameDAO.class);
		//bind(IUserService.class).to(UserServiceImpl.class);
	}

}
