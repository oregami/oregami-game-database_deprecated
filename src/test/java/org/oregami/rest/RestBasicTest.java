package org.oregami.rest;

import com.google.inject.Injector;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.hamcrest.Matchers;
import org.junit.*;
import org.oregami.data.DatabaseFiller;
import org.oregami.dropwizard.OregamiAppRule;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.dropwizard.OregamiConfiguration;
import org.oregami.util.StartHelper;

import javax.persistence.EntityManager;

public class RestBasicTest {

    @ClassRule
    public static final DropwizardAppRule<OregamiConfiguration> RULE =
            new OregamiAppRule(OregamiApplication.class, StartHelper.CONFIG_FILENAME_TEST);

    private static Injector injector;

    static EntityManager entityManager = null;

    @BeforeClass
    public static void initClass() {
        injector = StartHelper.getInjector();
    }

    @AfterClass
    public static void finish() {
        StartHelper.getInstance(DatabaseFiller.class).dropAllData();
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
