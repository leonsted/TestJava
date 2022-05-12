package tests.api;

import Data.TestData;
import Endpoints.AuthController;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.LogFilter.filters;
import static com.codeborne.selenide.Configuration.baseUrl;
import static io.restassured.RestAssured.given;

public class PoolValidTest {
    @BeforeAll
    static void configureBaseUrl() {
        baseUrl = TestData.getApiUrl();
    }

    @Test
    @Tag("api")
    @DisplayName("check status code 200 test for Validation pool")
    void check200StatusCodeValidPool(){
        given()
                .filter(filters().withCustomTemplates())
                .log().all()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .when()
                .get(AuthController.valid)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    @Tag("api")
    @DisplayName("check status code 403 test for Validation pool")
    void check403StatusCodeValidPool(){
        given()
                .filter(filters().withCustomTemplates())
                .log().all()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .when()
                .get(AuthController.valid)
                .then()
                .statusCode(403)
                .log().all();
    }
}
