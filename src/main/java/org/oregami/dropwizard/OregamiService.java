package org.oregami.dropwizard;

import org.oregami.resources.GamesResource;

import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
//import com.yammer.dropwizard.db.DatabaseConfiguration;
//import com.yammer.dropwizard.hibernate.HibernateBundle;


public class OregamiService extends Service<OregamiConfiguration> {

	private GuiceBundle<OregamiConfiguration> guiceBundle;
	
	public static void main(String[] args) throws Exception {
		new OregamiService().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<OregamiConfiguration> bootstrap) {
		bootstrap.setName("hello-world");
//		bootstrap.addBundle(hibernate);
		bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
		
		guiceBundle = GuiceBundle.<OregamiConfiguration>newBuilder()
				.addModule(new OregamiGuiceModule())
				.addModule(new JpaPersistModule("data"))
				.enableAutoConfig("org.oregami")
				.setConfigClass(OregamiConfiguration.class)
				.build();
		bootstrap.addBundle(guiceBundle);
//		OregamiGuiceInjector.get().getInstance(JPAInitializer.class);
		
				
	}

	
	@Override
	public void run(OregamiConfiguration config, Environment environment)
			throws Exception {
		
		PersistService persistService = guiceBundle.getInjector().getInstance(PersistService.class);
		persistService.start();
		
		environment.addResource(guiceBundle.getInjector().getInstance(GamesResource.class));
//		environment.addResource(GamesResource.class);
	}



}
