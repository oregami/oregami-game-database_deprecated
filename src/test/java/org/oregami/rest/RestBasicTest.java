package org.oregami.rest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.hamcrest.Matchers;
import org.junit.*;
import org.oregami.data.DatabaseFiller;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.dropwizard.OregamiConfiguration;

import javax.persistence.EntityManager;

public class RestBasicTest {

    @ClassRule
    public static final DropwizardAppRule<OregamiConfiguration> RULE =
            new DropwizardAppRule<>(OregamiApplication.class, "src/test/resources/oregami.yml");

    private static Injector injector;

    static EntityManager entityManager = null;

    @BeforeClass
    public static void init() {
        JpaPersistModule jpaPersistModule = new JpaPersistModule(OregamiApplication.JPA_UNIT);
        injector = Guice.createInjector(jpaPersistModule);
        PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();
        entityManager = injector.getInstance(EntityManager.class);
        RestTestHelper.initRestAssured();
    }

    @AfterClass
    public static void finish() {
        DatabaseFiller.getInstance().dropAllData();
    }


    /**
     * get base api url and check return code
     */
    @Test
	public void callApiBase() {
        Response response = RestAssured.get("/");
        Assert.assertThat(response.getStatusCode(), Matchers.is(200));
	}

}
