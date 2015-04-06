package org.oregami.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.hamcrest.Matchers;
import org.junit.*;
import org.oregami.data.DatabaseFiller;
import org.oregami.dropwizard.OregamiAppRule;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.dropwizard.OregamiConfiguration;
import org.oregami.entities.GameTitle;
import org.oregami.util.StartHelper;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RestEntityTest {

    @ClassRule
    public static final DropwizardAppRule<OregamiConfiguration> RULE =
            new OregamiAppRule(OregamiApplication.class, StartHelper.CONFIG_FILENAME_TEST);

    private static ObjectMapper mapper;

    @BeforeClass
    public static void initClass() {
        StartHelper.getInstance(DatabaseFiller.class).initData();
    }

    @AfterClass
    public static void finish() {
        StartHelper.getInstance(DatabaseFiller.class).dropAllData();
    }


    /**
     * get base api url and check return code
     */
    @Test
	public void createNewGameTitle() throws JsonProcessingException {
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Response response = RestAssured.given().formParam("username", "user1").formParam("password", "password1").header(header).request().post(RestTestHelper.URL_LOGIN);
        response.then().contentType(ContentType.JSON).statusCode(200);
        response.then().contentType(ContentType.JSON).body("token", Matchers.notNullValue());
        response.then().contentType(ContentType.JSON).body("token", Matchers.containsString("."));

        //get JsonWebToken from response:
        String token = response.body().jsonPath().get("token");

        response = RestAssured.get(RestTestHelper.URL_GAMETITLES);
        JsonPath jsonPath = new JsonPath(response.body().prettyPrint());
        List<HashMap> liste = jsonPath.getList("$");
        Assert.assertNotNull(liste);
        Assert.assertThat(liste.size(), Matchers.greaterThan(0));

        Header jsonContentHeader = new Header("Content-Type", "application/json");


        GameTitle gameTitle = new GameTitle();
        gameTitle.setNativeSpelling("The Secret of Monkey Island");

        //set Header for secured request:
        Header authHeader = new Header("Authorization", "bearer " + token);
        response = RestAssured.given()
                .header(authHeader)
                .header(jsonContentHeader)
                .body(getJsonString(gameTitle))
                .post(RestTestHelper.URL_GAMETITLES);

        response.then().contentType(ContentType.JSON).statusCode(equalTo(javax.ws.rs.core.Response.Status.CREATED.getStatusCode()));

        String location = response.header("Location");

        response = RestAssured.get(location);
        String nativeSpelling = response.body().jsonPath().get("nativeSpelling");
        Assert.assertThat(nativeSpelling, equalTo(gameTitle.getNativeSpelling()));

        Assert.assertThat(location, containsString(response.body().jsonPath().get("id").toString()));



    }

    private String getJsonString(Object entity) throws JsonProcessingException {
        return getJsonMapper().writeValueAsString(entity);
    }

    private static ObjectMapper getJsonMapper() {
        if (mapper==null) {
            mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return mapper;
    }

}
