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
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.dropwizard.OregamiConfiguration;

import javax.persistence.EntityManager;

public class RestTests {

    @ClassRule
    public static final DropwizardAppRule<OregamiConfiguration> RULE =
            new DropwizardAppRule<>(OregamiApplication.class, "oregami.yml");

    private static Injector injector;

    private static final String URL_LOGIN = "/jwt/login";

    static EntityManager entityManager = null;

    @BeforeClass
    public static void init() {
        JpaPersistModule jpaPersistModule = new JpaPersistModule(OregamiApplication.JPA_UNIT);
        injector = Guice.createInjector(jpaPersistModule);
        PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();
    	RestAssured.baseURI = "http://localhost";
    	RestAssured.port = 8080;
    	RestAssured.authentication = RestAssured.basic("username", "password");
        entityManager = injector.getInstance(EntityManager.class);

    }


    @Test
	public void callApiBase() {
        Response response = RestAssured.get("/");
        Assert.assertThat(response.getStatusCode(), Matchers.is(200));
	}


    @Test
    public void callSecuredResource() {
        Response response = RestAssured.get("/jwt/secured");
        Assert.assertThat(response.getStatusCode(), Matchers.greaterThanOrEqualTo(400));
    }

    @Test
    public void authenticateSuccess() {

        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Response response = RestAssured.given().formParam("username", "user1").formParam("password", "password1").header(header).request().post(URL_LOGIN);
        response.then().contentType(ContentType.JSON).statusCode(200);
        response.then().contentType(ContentType.JSON).body("token", Matchers.notNullValue());
        response.then().contentType(ContentType.JSON).body("token", Matchers.containsString("."));

    }

    @Test
    public void authenticateErrorWrongPassword() {
        //wrong password => no valid status code, no token
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Response response = RestAssured.given().formParam("username", "user1").formParam("password", "nonsense").header(header).request().post(URL_LOGIN);
        response.then().contentType(ContentType.JSON).statusCode(Matchers.greaterThanOrEqualTo(400));
        response.then().contentType(ContentType.JSON).body(Matchers.isEmptyString());

    }

    @Test
    public void authenticateErrorEmptyPassword() {
        //empty password => no valid status code, no token
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Response response = RestAssured.given().formParam("username", "user1").header(header).request().post(URL_LOGIN);
        response.then().contentType(ContentType.JSON).statusCode(Matchers.greaterThanOrEqualTo(400));
        response.then().contentType(ContentType.JSON).body(Matchers.isEmptyString());

    }

}
