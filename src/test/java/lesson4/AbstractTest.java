package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;

public abstract class AbstractTest {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;
    private static String query;
    private static String queryOther;
    private static String cuisine;
    private static String username;
    @lombok.Getter
    @lombok.Setter
    private static String hash;
    private static String startDate;
    private static String endDate;
    private static int id;
    private static String title;
    private static String titleOther;
    protected static ResponseSpecification responseSpecification;

    protected static RequestSpecification requestSpecification;
    protected static RequestSpecification requestSpecificationForChain;

    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);
        apiKey = prop.getProperty("apiKey");
        baseUrl = prop.getProperty("base_url");
        query = prop.getProperty("query");
        queryOther = prop.getProperty("queryOther");
        cuisine = prop.getProperty("cuisine");
        username = prop.getProperty("username");
        hash = prop.getProperty("hash");
        startDate = prop.getProperty("startDate");
        endDate = prop.getProperty("endDate");
        id = Integer.parseInt(prop.getProperty("id"));
        title = prop.getProperty("title");
        titleOther = prop.getProperty("titleOther");
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(10000L))
                .expectHeader("alt-svc", equalTo("h3=\":443\"; ma=86400"))
                .log(LogDetail.ALL)
                .build();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();


        RestAssured.responseSpecification = responseSpecification;
        RestAssured.requestSpecification = requestSpecification;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getQuery() {
        return query;
    }

    public static String getQueryOther() {
        return queryOther;
    }

    public static String getCuisine() {
        return cuisine;
    }

    public static void setUsername(String username) {
        AbstractTest.username = username;
    }

    public static String getUsername() {
        return username;
    }

    public static String getStartDate() {
        return startDate;
    }

    public static String getEndDate() {
        return endDate;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AbstractTest.id = id;
    }
    public static String getTitle() {
        return title;
    } public static String getTitleOther() {
        return titleOther;
    }
    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

}

