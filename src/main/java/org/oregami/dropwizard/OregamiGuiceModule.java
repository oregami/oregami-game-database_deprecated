package org.oregami.dropwizard;

import org.oregami.data.GameDao;
import org.oregami.data.GenericDAO;
import org.oregami.entities.Game;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class OregamiGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		
		bind(new TypeLiteral<GenericDAO<Game, Long>>() {}).to(GameDao.class);
//		bind(GameDAO.class);
		//bind(IUserService.class).to(UserServiceImpl.class);
	}

}
