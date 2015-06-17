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
import org.oregami.data.*;
import org.oregami.dropwizard.OregamiAppRule;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.dropwizard.OregamiConfiguration;
import org.oregami.entities.*;
import org.oregami.entities.datalist.Script;
import org.oregami.entities.datalist.TitleType;
import org.oregami.util.StartHelper;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

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
     * login, create new GameTitle
     */
    /*
    @Test
	public void createNewGameTitle() throws JsonProcessingException {

        String token = loginAndGetToken();

        Response response = RestAssured.get(RestTestHelper.URL_GAMETITLES);
        JsonPath jsonPath = new JsonPath(response.body().prettyPrint());
        List<HashMap> liste = jsonPath.getList("$");
        Assert.assertNotNull(liste);
        Assert.assertThat(liste.size(), Matchers.greaterThan(0));

        //GameTitle gameTitle = new GameTitle();
        //gameTitle.setNativeSpelling("The Secret of Monkey Island");

        //set Header for secured request:
        Header authHeader = new Header("Authorization", "bearer " + token);
        response = saveNewEntity(gameTitle, RestTestHelper.URL_GAMETITLES, authHeader);

        //Load entity with new GET request
        String location = response.header("Location");
        response = RestAssured.get(location);
        System.out.println(response.prettyPrint());
        String nativeSpelling = response.body().jsonPath().get("nativeSpelling");
        Assert.assertThat(nativeSpelling, equalTo(gameTitle.getNativeSpelling()));
        Assert.assertThat(location, containsString(response.body().jsonPath().get("id").toString()));

    }
    */

    private String loginAndGetToken() {
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Response response = RestAssured.given().formParam("username", "user1").formParam("password", "password1").header(header).request().post(RestTestHelper.URL_LOGIN);
        response.then().contentType(ContentType.JSON).statusCode(200);
        response.then().contentType(ContentType.JSON).body("token", Matchers.notNullValue());
        response.then().contentType(ContentType.JSON).body("token", Matchers.containsString("."));
        //get JsonWebToken from response:
        String token = response.body().jsonPath().get("token");
        return token;
    }

    private String getJsonString(Object entity) {
        try {
            return getJsonMapper().writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper getJsonMapper() {
        if (mapper==null) {
            mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return mapper;
    }

    @Test
    public void createNewHardwarePlatform() throws JsonProcessingException {

        String token = loginAndGetToken();
        Header authHeader = new Header("Authorization", "bearer " + token);

        Response response = RestAssured.get(RestTestHelper.URL_HARDWAREPLATFORM);
        JsonPath jsonPath = new JsonPath(response.body().prettyPrint());
        List<HashMap> liste = jsonPath.getList("$");
        Assert.assertNotNull(liste);

        HardwarePlatform hp = new HardwarePlatform();

        PlatformTitle pt1 = TitleFactory.createPlatformTitle(
                StartHelper.getInstance(RegionDao.class).findByExactName(Region.UNITED_STATES),
                StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
                StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN),
                StartHelper.getInstance(LanguageDao.class).findByExactName(Language.ENGLISH),
                "Sony Playstation"
        );
        //hp.addTitle(pt1);

        PlatformTitle pt2 = TitleFactory.createPlatformTitle(
                StartHelper.getInstance(RegionDao.class).findByExactName(Region.JAPAN),
                StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE),
                StartHelper.getInstance(BaseListFinder.class).getScript(Script.JAPANESE),
                StartHelper.getInstance(LanguageDao.class).findByExactName(Language.JAPANESE),
                "プレイステーション"
        );
        //hp.addTitle(pt2);

        response = saveNewEntity(hp, RestTestHelper.URL_HARDWAREPLATFORM, authHeader);

        List<HardwarePlatform> hardwarePlatformList = StartHelper.getInstance(HardwarePlatformDao.class).findAll();
        Assert.assertThat(hardwarePlatformList, notNullValue());
        Assert.assertThat(hardwarePlatformList.size(), greaterThan(0));

        //Load entity with new GET request
        String location = response.header("Location");
        System.out.println("location: " + location);

        response = RestAssured.get(location);
        System.out.println(response.prettyPrint());
        response.then().statusCode(equalTo(javax.ws.rs.core.Response.Status.OK.getStatusCode()));
        Assert.assertThat(location, containsString(response.body().jsonPath().get("id").toString()));

        //String text = response.body().jsonPath().get("title.text");
        System.out.println(response.body().prettyPrint());


    }

    private Response saveNewEntity(BaseEntityUUID entity, String url, Header authHeader) {
        entity.setChangeTime(null);
        Header jsonContentHeader = new Header("Content-Type", "application/json");
        System.out.println(getJsonString(entity));
        Response response = RestAssured.given()
                .header(authHeader)
                .header(jsonContentHeader)
                .body(getJsonString(entity))
                .post(url);
        System.out.println(response.prettyPrint());
        System.out.println(response.body().prettyPrint());
        response.then().statusCode(equalTo(javax.ws.rs.core.Response.Status.CREATED.getStatusCode()));
        return response;
    }

}
