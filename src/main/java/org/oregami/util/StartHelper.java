package org.oregami.util;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.jackson.Jackson;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.oregami.dropwizard.OregamiConfiguration;
import org.oregami.dropwizard.OregamiGuiceModule;

import javax.validation.Validation;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by sebastian on 06.04.15.
 */
public class StartHelper {

    private static Injector injector = null;
    public static final String JPA_UNIT = "d1";
    public static final String CONFIG_FILENAME_TEST = "src/test/resources/oregami.yml";
    private static String configFilename;

    public static String getConfigFilename() {
        return configFilename;
    }

    public static void setConfigFilename(String configFilename) {
        StartHelper.configFilename = configFilename;
    }

    public static Properties createPropertiesFromConfiguration(OregamiConfiguration localConfiguration) {

        OregamiConfiguration.DatabaseConfiguration databaseConfiguration = localConfiguration.getDatabaseConfiguration();
        List<String> propertiesList = new ArrayList<>();
        propertiesList.add("hibernate.dialect");
        propertiesList.add("hibernate.show_sql");
        propertiesList.add("hibernate.hbm2ddl.auto");
        propertiesList.add("hibernate.dialect");
        propertiesList.add("hibernate.archive.autodetection");
        propertiesList.add("hibernate.connection.driver_class");
        propertiesList.add("hibernate.username");
        propertiesList.add("hibernate.password");

        Properties properties = new Properties();
        properties.setProperty("javax.persistence.jdbc.url", databaseConfiguration.getUrl());

        for (String p : propertiesList) {
            String val = databaseConfiguration.getProperties().get(p);
            if (val != null) {
                properties.setProperty(p, val);
            }
        }

        return properties;
    }


    public static Injector getInjector() {
        if (injector == null) {
            throw new RuntimeException("call StartHelper.init() first!");
        }
        return injector;
    }

    public static void init() {
        init(CONFIG_FILENAME_TEST);
    }

    public static void init(String localConfigFilename) {
        configFilename = localConfigFilename;
        OregamiConfiguration configuration = createConfiguration(localConfigFilename);
        Properties properties = createPropertiesFromConfiguration(configuration);
        JpaPersistModule jpaPersistModule = new JpaPersistModule(JPA_UNIT);
        jpaPersistModule.properties(properties);
        injector = Guice.createInjector(jpaPersistModule, new OregamiGuiceModule());
        injector.getInstance(PersistService.class).start();
    }

    public static OregamiConfiguration createConfiguration(String configFilename) {
        ConfigurationFactory<OregamiConfiguration> factory =
                new ConfigurationFactory<>(
                        OregamiConfiguration.class,
                        Validation.buildDefaultValidatorFactory().getValidator(),
                        Jackson.newObjectMapper(),
                        ""
                );
        OregamiConfiguration configuration;
        try {
            configuration = factory.build(new File(configFilename));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(ToStringBuilder.reflectionToString(configuration, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(configuration.getDatabaseConfiguration(), ToStringStyle.MULTI_LINE_STYLE));
        return configuration;
    }

    public static <T> T getInstance(Class<T> c) {
        return getInjector().getInstance(c);
    }
}
