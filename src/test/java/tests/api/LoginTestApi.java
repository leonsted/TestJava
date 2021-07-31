//package tests.api;//package tests.api;
//
//import Data.TestData;
//import Endpoints.AuthController;
//import com.codeborne.selenide.Configuration;
//import io.restassured.RestAssured;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//
//import static api.LogFilter.filters;
//import static io.qameta.allure.Allure.step;
//import static io.restassured.RestAssured.baseURI;
//import static io.restassured.RestAssured.given;
//
//public class LoginTestApi {
//
//    @BeforeAll
//    static void configureBaseApiUrl() {
//        RestAssured.baseURI = TestData.getApiUrl();
//    }
//
//    @Test
//    @Tag("api")
//    @DisplayName("Successful authorization with set cookie, received by API")
//    void loginWithCookieTest() {
//        given()
//                .filter(filters().withCustomTemplates())
//                .log().all()
//                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                .when()
//                .body(TestData.getUserLogin())
//                .body(TestData.getUserPassword())
//                .post(AuthController.login)
//                .then()
//                .statusCode(200)
//                .log().all();
//    }
//}
