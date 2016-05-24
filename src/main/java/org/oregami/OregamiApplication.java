package org.oregami;

import org.oregami.data.BaseListFiller;
import org.oregami.data.DatabaseFiller;
import org.oregami.data.LanguageDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@ComponentScan({"org.oregami", "org.oregami.resources", "org.oregami.service", "org.oregami.entities", "org.oregami.data"})
@SpringBootApplication(scanBasePackages = {"org.oregami", "org.oregami.data"})
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class, basePackages = {"org.oregami", "org.oregami.entities", "org.oregami.data"})
@EntityScan(basePackages = "org.oregami.entities")
public class OregamiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(OregamiApplication.class, args);

        BaseListFiller baseListFiller = ctx.getBean(BaseListFiller.class);
        baseListFiller.initBaseLists();

        DatabaseFiller databaseFiller = ctx.getBean(DatabaseFiller.class);
        databaseFiller.addLanguages();
        databaseFiller.addRegions();
        databaseFiller.addPublications();
        databaseFiller.addGamingEnvironments();

    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US); // Set default Locale as US
        return slr;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("i18n/messages");  // name of the resource bundle
        source.setUseCodeAsDefaultMessage(false);
        source.setCacheSeconds(-1);
        return source;
    }
}
