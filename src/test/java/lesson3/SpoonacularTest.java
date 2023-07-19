package lesson3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;


public class SpoonacularTest extends AbstractTest {
    // test-1
    @Test
    void getExampleTest() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?" +
                        "query=getQuery1&apiKey=" + getApiKey())
                .then()
                .statusCode(200)
                .headers("Content-Type", equalTo("application/json"));
    }

    //    test-2
    @Test
    void getRecipeWithQueryParametersPositiveTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", getQuery1())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .headers("alt-svc", equalTo("h3=\":443\"; ma=86400"));
    }

    //    test-3
    @Test
    void getRecipeWithBodyChecksAfterRequestPositiveTest() {
        JsonPath response = given()
                .queryParam("query", getQuery1())
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("results"), is(notNullValue()));
    }

    //    test-4
    @Test
    void getRecipeWithBodyChecksPositive2Test() {
        given()
                .queryParam("query", getQuery4())
                .queryParam("apiKey", getApiKey())
                .response()
                .time(lessThan(3000L))
                .expect()
                .body("totalResults", equalTo(54))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");
    }

    //    test-5
    @Test
    void getRecipeWithBodyChecksAfterRequestPositive3Test() {
        given()
                .queryParam("cuisine", getCuisine3())
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .body("number", equalTo(10))
                .body("offset", equalTo(0));
    }

    //    test-6
    @Test
    void postRecipeCuisineTest() {
        String cuisine = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pork roast with green beans")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200)
                .extract().response()
                .jsonPath()
                .get("cuisine");
        assertThat(cuisine, equalTo("Mediterranean"));
    }

    //    test-7
    @Test
    void postRecipeCuisine2Test() {
        String cuisines = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "synthesizing")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200)
                .extract().response()
                .jsonPath()
                .get("cuisines[1]");
        assertThat(cuisines, equalTo("European"));
    }

    //    test-8
    @Test
    void postRecipeCuisine3Test() {
        String cuisine = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "synthesizing")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200)
                .extract().response()
                .jsonPath()
                .get("cuisine");
        assertThat(cuisine, is(notNullValue()));
    }

    //    test-9
    @Test
    void postRecipeCuisine4Test() {
        long responseTime = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "synthesizing")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200)
                .extract().response()
                .time();
        assertThat(responseTime, lessThan(3000L));
    }

    //    test-10
    @Test
    void postRecipeCuisine5Test() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "synthesizing")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200)
                .headers("alt-svc", is(notNullValue()));
    }

    //    test-11
    @Test
    void postMealPlanChainTest() {
//        создадим пользователя Connect User
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("x-api-key", getApiKey())
                .body("{\"username\": \"userf7\",\"firstName\": \"AllUsers\",\"lastName\": \"AllUsers\",\"email\": \"8rolex@mail.ru\"}")
                .when()
                .post(getBaseUrl() + "users/connect")
                .then()
                .statusCode(200)
                .extract()
                .response();
        String hash = response.jsonPath().get("hash").toString();
        String username = response.jsonPath().get("username").toString();
        //        создадим список покупок Generate Shopping List
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("x-api-key", getApiKey())
                .pathParam("username", username)
                .pathParam("start-date", "2020-06-01")
                .pathParam("end-date", "2020-06-07")
                .queryParam("hash", hash)
                .post(getBaseUrl() + "mealplanner/{username}/shopping-list/{start-date}/{end-date}", username, "2020-06-01", "2020-06-07")
                .then()
                .assertThat()
                .statusCode(200)
                .body("cost", equalTo(0.0F));
        //       добавим в список покупки
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("x-api-key", getApiKey())
                .pathParam("username", username)
                .queryParam("hash", hash)
                .body("{\"item\":\"1 package baking powder\",\"aisle\":\"Baking\",\"parse\":true}")
                .post(getBaseUrl() + "mealplanner/{username}/shopping-list/items", username)
                .then()
                .assertThat()
                .statusCode(200);
//        посмотрим товар из списка покупок и запомним id
        String id = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("x-api-key", getApiKey())
                .pathParam("username", username)
                .queryParam("hash", hash)
                .get(getBaseUrl() + "mealplanner/{username}/shopping-list", username)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("aisles[0].items[0].id")
                .toString();
//      удалим товар из списка покупок
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("x-api-key", getApiKey())
                .pathParam("username", username)
                .pathParam("id", id)
                .queryParam("hash", hash)
                .delete(getBaseUrl() + "mealplanner/{username}/shopping-list/items/{id}", username, id)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
