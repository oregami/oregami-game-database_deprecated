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

public class RestAuthenticationTest {

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


    @Test
    public void callSecuredResource() {
        Response response = RestAssured.get(RestTestHelper.URL_SECURED);
        Assert.assertThat(response.getStatusCode(), Matchers.greaterThanOrEqualTo(400));
    }

    /**
     * login with correct password => ok, check if auth token is available
     */
    @Test
    public void authenticateSuccess() {

        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Response response = RestAssured.given().formParam("username", "user1").formParam("password", "password1").header(header).request().post(RestTestHelper.URL_LOGIN);
        response.then().contentType(ContentType.JSON).statusCode(200);
        response.then().contentType(ContentType.JSON).body("token", Matchers.notNullValue());
        response.then().contentType(ContentType.JSON).body("token", Matchers.containsString("."));

    }

    /**
     * login with wrong password => error
     */
    @Test
    public void authenticateErrorWrongPassword() {
        //wrong password => no valid status code, no token
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Response response = RestAssured.given().formParam("username", "user1").formParam("password", "nonsense").header(header).request().post(RestTestHelper.URL_LOGIN);
        response.then().contentType(ContentType.JSON).statusCode(Matchers.greaterThanOrEqualTo(400));
        response.then().contentType(ContentType.JSON).body(Matchers.isEmptyString());

    }

    /**
     * login with empty password => error
     */
    @Test
    public void authenticateErrorEmptyPassword() {
        //empty password => no valid status code, no token
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Response response = RestAssured.given().formParam("username", "user1").header(header).request().post(RestTestHelper.URL_LOGIN);
        response.then().contentType(ContentType.JSON).statusCode(Matchers.greaterThanOrEqualTo(400));
        response.then().contentType(ContentType.JSON).body(Matchers.isEmptyString());

    }

    /**
     * load secured page should give an error
     */
    @Test
    public void loadSecuredResourceWithoutAuthentication() {

        Response response = RestAssured.get(RestTestHelper.URL_SECURED);
        Assert.assertThat(response.getStatusCode(), Matchers.greaterThanOrEqualTo(400));

    }

    /**
     * login and get auth token, make request to secured page with auth token => ok
     */
    @Test
    public void loadSecuredResourceWithCorrectAuthentication() {

        Response response = RestAssured.get(RestTestHelper.URL_SECURED);
        Assert.assertThat(response.getStatusCode(), Matchers.greaterThanOrEqualTo(400));

        //login:
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        response = RestAssured.given().formParam("username", "user1").formParam("password", "password1").header(header).request().post(RestTestHelper.URL_LOGIN);
        response.then().contentType(ContentType.JSON).statusCode(200);
        response.then().contentType(ContentType.JSON).body("token", Matchers.notNullValue());
        response.then().contentType(ContentType.JSON).body("token", Matchers.containsString("."));

        //get JsonWebToken from response:
        String token = response.body().jsonPath().get("token");

        //set Header for secured request:
        header = new Header("Authorization", "bearer " + token);
        response = RestAssured.given().header(header).get(RestTestHelper.URL_SECURED);
        Assert.assertThat(response.getStatusCode(), Matchers.is(200));



    }

}
