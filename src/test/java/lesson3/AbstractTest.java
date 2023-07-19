package lesson3;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;
    private static String query1;
    private static String query4;
    private static String cuisine3;
    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        baseUrl = prop.getProperty("base_url");
        query1 = prop.getProperty("query");
        query4 = prop.getProperty("query4");
        cuisine3 = prop.getProperty("cuisine3");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
    public static String getQuery1() {
        return query1;
    }
    public static String getQuery4() {
        return query4;
    }
    public static String getCuisine3() {
        return cuisine3;
    }
}

