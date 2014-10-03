package org.oregami.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

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


public class OregamiApplication extends Application<OregamiConfiguration> {

    public static final String JPA_UNIT =
            "data";
    //"dataMysql";

    private GuiceBundle<OregamiConfiguration> guiceBundle;
    private static final JpaPersistModule jpaPersistModule = new JpaPersistModule(JPA_UNIT);

    public static void main(String[] args) throws Exception {
        new OregamiApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<OregamiConfiguration> bootstrap) {

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
        bootstrap.getObjectMapper().registerModule(module);

    }


    @Override
    public void run(OregamiConfiguration config, Environment environment)
            throws Exception {

        environment.servlets().addFilter("persistFilter", guiceBundle.getInjector().getInstance(PersistFilter.class)).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        environment.jersey().register(new BasicAuthProvider<User>(new OregamiAuthenticator(),
                "only visible with valid user/password"));

        if (config.isInitBaseLists()) {
            DatabaseFiller.getInstance().initBaseLists();
        }
        if (config.isInitGames()) {
            DatabaseFiller.getInstance().initGameData();
        }

        WebsiteHelper.init(config.getPhantomJSConfiguration().getPhantomjsCommandLocation(), config.getPhantomJSConfiguration().getRasterizeJSFileLocation());
        MailHelper.init(config.getMailConfiguration());

        Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
//
//	    filter.setInitParameter("allow", "GET,PUT,POST,DELETE,OPTIONS");
//	    filter.setInitParameter("preflightMaxAge", "5184000"); // 2 months


        environment.jersey().register(guiceBundle.getInjector().getInstance(GamesResource.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(HomeResource.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(AdminResource.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(GameTitleResource.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(PublicationFranchiseResource.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(WebsiteResource.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(UserResource.class));

    }

    public static JpaPersistModule createJpaModule() {
        return jpaPersistModule;
    }



}
