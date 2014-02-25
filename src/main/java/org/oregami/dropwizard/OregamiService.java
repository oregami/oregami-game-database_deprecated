package org.oregami.dropwizard;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.oregami.data.DatabaseFiller;
import org.oregami.entities.CustomLocalDateSerializer;
import org.oregami.entities.CustomLocalDateTimeSerializer;
import org.oregami.entities.user.User;
import org.oregami.resources.AdminResource;
import org.oregami.resources.GameTitleResource;
import org.oregami.resources.GamesResource;
import org.oregami.resources.HomeResource;
import org.oregami.resources.PublicationFranchiseResource;
import org.oregami.resources.UserResource;
import org.oregami.resources.WebsiteResource;
import org.oregami.util.MailHelper;
import org.oregami.util.WebsiteHelper;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.auth.basic.BasicAuthProvider;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.FilterBuilder;
//import com.yammer.dropwizard.db.DatabaseConfiguration;
//import com.yammer.dropwizard.hibernate.HibernateBundle;


public class OregamiService extends Service<OregamiConfiguration> {

	public static final String JPA_UNIT = 
			"data"; 
			//"dataMysql";
	
	private GuiceBundle<OregamiConfiguration> guiceBundle;
	private static final JpaPersistModule jpaPersistModule = new JpaPersistModule(JPA_UNIT);
	
	public static void main(String[] args) throws Exception {
		new OregamiService().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<OregamiConfiguration> bootstrap) {
		bootstrap.setName("oregami-server");
		
		guiceBundle = GuiceBundle.<OregamiConfiguration>newBuilder()
				.addModule(new OregamiGuiceModule())
				.addModule(jpaPersistModule)
				.enableAutoConfig("org.oregami")
				.setConfigClass(OregamiConfiguration.class)
				.build();
		bootstrap.addBundle(guiceBundle);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
		module.addSerializer(LocalDate.class, new CustomLocalDateSerializer());
		bootstrap.getObjectMapperFactory().registerModule(module);
				
	}

	
	@Override
	public void run(OregamiConfiguration config, Environment environment)
			throws Exception {

		environment.addProvider(new BasicAuthProvider<User>(new OregamiAuthenticator(),
                "only visible with valid user/password"));
		
		DatabaseFiller.getInstance().initData();
		WebsiteHelper.init(config.getPhantomJSConfiguration().getPhantomjsCommandLocation(), config.getPhantomJSConfiguration().getRasterizeJSFileLocation());
		MailHelper.init(config.getMailConfiguration());
		
		FilterBuilder fconfig = environment.addFilter(CrossOriginFilter.class, "/*");
		fconfig.setInitParam(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");	
		fconfig.setInitParam(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
		
		fconfig.setInitParam(CrossOriginFilter.ALLOWED_METHODS_PARAM, "PUT,GET,POST,DELETE");
		
		environment.addFilter(guiceBundle.getInjector().getInstance(PersistFilter.class), "/*");

		environment.addResource(guiceBundle.getInjector().getInstance(GamesResource.class));
		environment.addResource(guiceBundle.getInjector().getInstance(HomeResource.class));
		environment.addResource(guiceBundle.getInjector().getInstance(AdminResource.class));
		environment.addResource(guiceBundle.getInjector().getInstance(GameTitleResource.class));
		environment.addResource(guiceBundle.getInjector().getInstance(PublicationFranchiseResource.class));
		environment.addResource(guiceBundle.getInjector().getInstance(WebsiteResource.class));
		environment.addResource(guiceBundle.getInjector().getInstance(UserResource.class));
		
	}

	public static JpaPersistModule createJpaModule() {
		return jpaPersistModule;
	}



}
