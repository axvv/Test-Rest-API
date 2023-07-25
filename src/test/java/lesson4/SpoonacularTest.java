package lesson4;


import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

public class SpoonacularTest extends AbstractTest {
    // test-1
    @Test
    void getRecipePositiveTest() {
        given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl() +"recipes/716429/information")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getExampleTest() {
        given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    //    test-2
    @Test
    void getRecipeWithQueryParametersPositiveTest() {
        given().spec(getRequestSpecification())
                .queryParam("query", getQuery())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    //    test-3
    @Test
    void getRecipeWithBodyChecksAfterRequestPositiveTest() {
        ResponseGetComplexSearch responseGetComplexSearch = given().spec(getRequestSpecification())
                .queryParam("query", getQuery())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .extract()
                .body()
                .as(ResponseGetComplexSearch.class);
        assertThat(responseGetComplexSearch.getTotalResults(), is(notNullValue()));
    }

    //    test-4
    @Test
    void getRecipeWithBodyChecksPositive2Test() {
        given().spec(getRequestSpecification())
                .queryParam("query", getQueryOther())
                .response()
                .time(lessThan(3000L))
                .body("totalResults", equalTo(54))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");
    }

    //    test-5
    @Test
    void getRecipeWithBodyChecksAfterRequestPositive3Test() {
      ResponseGetComplexSearch responseGetComplexSearch = given().spec(getRequestSpecification())
                .queryParam("cuisine", getCuisine())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .extract()
                .body()
                .as(ResponseGetComplexSearch.class);
        assertThat(responseGetComplexSearch.getNumber(),equalTo(10.0F));
        assertThat(responseGetComplexSearch.getOffset(),equalTo(0.0F));
    }

    //    test-6
    @Test
    void postRecipeCuisineTest() {
     ResponseExample responseExample = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", getTitle())
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
             .then()
             .extract()
             .body()
             .as(ResponseExample.class);
        assertThat(responseExample.getCuisine(),equalTo("Mediterranean"));
    }

    //    test-7
    @Test
    void postRecipeCuisine2Test() {
        ResponseExample responseExample = given()
                .formParam("titleOther", getTitleOther())
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .extract()
                .body()
                .as(ResponseExample.class);
        assertThat(responseExample.getCuisines().get(1),equalTo("European"));
    }

    //    test-8
    @Test
    void postRecipeCuisine3Test() {
        ResponseExample responseExample = given()
                .formParam("title", getTitleOther())
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .extract()
                .body()
                .as(ResponseExample.class);
        assertThat(responseExample.getCuisine(),is(notNullValue()));
    }

    //    test-9
    @Test
    void postRecipeCuisine4Test() {
        ResponseExample responseExample = given()
                .formParam("title", getTitleOther())
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .extract()
                .body()
                .as(ResponseExample.class);
        assertThat(responseExample.getConfidence(),is(notNullValue()));
    }

    //    test-10
    @Test
    void postRecipeCuisine5Test() {
        given()

                .formParam("title", getTitleOther())
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
        ResponseConnectUser responseConnectUser = given()
                .header("x-api-key", getApiKey())
                .body("{\"username\": \"userf7\",\"firstName\": \"AllUsers\",\"lastName\": \"AllUsers\",\"email\": \"8rolex@mail.ru\"}")
                .when()
                .post(getBaseUrl() + "users/connect").prettyPeek()
                .then()
                .extract()
                .body()
                .as(ResponseConnectUser.class);
        assertThat(responseConnectUser.getHash(), is(notNullValue()));
        setHash(responseConnectUser.getHash());
        setUsername(responseConnectUser.getUsername());

        //        создадим список покупок Generate Shopping List

        ResponseCreateShoppingList responseCreateShoppingList = given(getRequestSpecification())
                .header("x-api-key", getApiKey())
                .pathParam("username", getUsername())
                .pathParam("start-date", getStartDate())
                .pathParam("end-date", getEndDate())
                .queryParam("hash", getHash())
                .post(getBaseUrl() + "mealplanner/{username}/shopping-list/{start-date}/{end-date}").prettyPeek()
                .then()
                .extract()
                .body()
                .as(ResponseCreateShoppingList.class);
        assertThat(responseCreateShoppingList.getCost(), equalTo(0.0));

        //       добавим в список покупки Add to Shopping List
        ResponseAddToShoppingList responseAddToShoppingList = given(getRequestSpecification())

                .header("x-api-key", getApiKey())
                .pathParam("username", getUsername())
                .queryParam("hash", getHash())
                .body("{\"item\":\"1 package baking powder\",\"aisle\":\"Baking\",\"parse\":true}")
                .post(getBaseUrl() + "mealplanner/{username}/shopping-list/items").prettyPeek()
                .then()
                .extract()
                .body()
                .as(ResponseAddToShoppingList.class);
        assertThat(responseAddToShoppingList.getId(), greaterThanOrEqualTo(1607077));

        //        посмотрим товар из списка покупок и запомним id Get Shopping List
        ResponseGetShoppingList responseGetShoppingList = given(getRequestSpecification())
                .header("x-api-key", getApiKey())
                .pathParam("username", getUsername())
                .queryParam("hash", getHash())
                .get(getBaseUrl() + "mealplanner/{username}/shopping-list")
                .then()
                .extract()
                .body()
                .as(ResponseGetShoppingList.class);

        assertThat(responseGetShoppingList.getAisles(), is(notNullValue()));
    setId(responseGetShoppingList.getAisles().get(0).getItems().get(0).getId());


    //      удалим товар из списка покупок Delete from Shopping List
    given(getRequestSpecification())
                .header("x-api-key", getApiKey())
            .pathParam("username", getUsername())
                .pathParam("id", getId())
                .queryParam("hash", getHash())
                .delete(getBaseUrl() + "mealplanner/{username}/shopping-list/items/{id}")
            .then()
            .assertThat()
            .statusCode(200);
}

}
