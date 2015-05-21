package org.oregami.dropwizard;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.toastshaman.dropwizard.auth.jwt.JWTAuthFactory;
import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenParser;
import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenValidator;
import com.github.toastshaman.dropwizard.auth.jwt.exceptions.TokenExpiredException;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Verifier;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.parser.DefaultJsonWebTokenParser;
import com.github.toastshaman.dropwizard.auth.jwt.validator.ExpiryValidator;
import com.google.common.base.Optional;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.hk2.utilities.Binder;
import org.joda.time.Duration;
import org.oregami.data.DatabaseFiller;
import org.oregami.entities.user.User;
import org.oregami.resources.*;
import org.oregami.util.AuthHelper;
import org.oregami.util.MailHelper;
import org.oregami.util.StartHelper;
import org.oregami.util.WebsiteHelper;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import java.util.EnumSet;
import java.util.Properties;


public class OregamiApplication extends Application<OregamiConfiguration> {

    private GuiceBundle<OregamiConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        for (int i=0; i<args.length; i++) {
            if (args[i].endsWith(".yml")) {
                StartHelper.setConfigFilename(args[i]);
            }
        }
        new OregamiApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<OregamiConfiguration> bootstrap) {

        OregamiConfiguration configuration = StartHelper.createConfiguration(StartHelper.getConfigFilename());
        Properties jpaProperties = StartHelper.createPropertiesFromConfiguration(configuration);

        JpaPersistModule jpaPersistModule = new JpaPersistModule(StartHelper.JPA_UNIT);
        jpaPersistModule.properties(jpaProperties);

        guiceBundle = GuiceBundle.<OregamiConfiguration>newBuilder()
                .addModule(new OregamiGuiceModule())
                .addModule(jpaPersistModule)
                .enableAutoConfig("org.oregami")
                .setConfigClass(OregamiConfiguration.class)
                .build();
        bootstrap.addBundle(guiceBundle);

        SimpleModule module = new SimpleModule();
        //module.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        //module.addSerializer(LocalDate.class, new CustomLocalDateSerializer());
        bootstrap.getObjectMapper().registerModule(module);

    }


    @Override
    public void run(OregamiConfiguration config, Environment environment)
            throws Exception {

        environment.servlets().addFilter("persistFilter", guiceBundle.getInjector().getInstance(PersistFilter.class)).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        StartHelper.init(StartHelper.getConfigFilename());

        DatabaseFiller databaseFiller = StartHelper.getInjector().getInstance(DatabaseFiller.class);
        if (config.isInitBaseLists()) {
            databaseFiller.initBaseLists();
        }
        if (config.isInitGames()) {
            databaseFiller.initGameData();
        }
        databaseFiller.initDemoUser();

        WebsiteHelper.init(config.getPhantomJSConfiguration().getPhantomjsCommandLocation(), config.getPhantomJSConfiguration().getRasterizeJSFileLocation());
        MailHelper.init(config.getMailConfiguration());

        Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter(CrossOriginFilter.EXPOSED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location");
        filter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
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
        environment.jersey().register(guiceBundle.getInjector().getInstance(ScriptResource.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(TransliteratedStringResource.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(HardwarePlatformResource.class));

        environment.jersey().register(createAuthProvider());


        environment.jersey().register(guiceBundle.getInjector().getInstance(SecuredResource.class));

    }

    private static class ExampleAuthenticator implements Authenticator<JsonWebToken, User> {
        @Override
        public Optional<User> authenticate(JsonWebToken token) throws AuthenticationException {

            final JsonWebTokenValidator expiryValidator = new ExpiryValidator(Duration.standardSeconds(1));
            //final JsonWebTokenValidator expiryValidator = new ExpiryValidator();

            // Provide your own implementation to lookup users based on the principal attribute in the
            // JWT Token. E.g.: lookup users from a database etc.
            // This method will be called once the token's signature has been verified

            // In case you want to verify different parts of the token you can do that here.
            // E.g.: Verifying that the provided token has not expired.
            try {
                expiryValidator.validate(token);
            } catch (TokenExpiredException e) {
                throw new AuthenticationException(e.getMessage(), e);
            }

            final AuthHelper authHelper = StartHelper.getInstance(AuthHelper.class);

            //check if username is present:
            Object tokenUsername = token.claim().getParameter("username");
            if (tokenUsername != null) {
                return Optional.of(authHelper.getUserByUsername(tokenUsername.toString()));
            }

            return Optional.absent();
        }
    }

    private Binder createAuthProvider() {
        JsonWebTokenParser tokenParser = new DefaultJsonWebTokenParser();
        final HmacSHA512Verifier tokenVerifier = new HmacSHA512Verifier(AuthHelper.authKey);
        Binder authProvider = AuthFactory.binder(new JWTAuthFactory<>(new ExampleAuthenticator(), "realm", User.class, tokenVerifier, tokenParser));
        return authProvider;
    }


}
