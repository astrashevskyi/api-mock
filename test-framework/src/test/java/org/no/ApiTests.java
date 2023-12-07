package org.no;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class ApiTests {
    private final String SERVER_URL = "http://localhost:8080";
    private final String MOCK_SERVER_URL = "http://localhost:8081";
    private final String API_V1 = "/api/v1";
    private final String API_V2 = "/api/v2";

    @BeforeAll
    public static void setupAll() {
        WireMock.configureFor("localhost", 8081);
    }

    @Test
    public void apiTest() {
        String responseBody = RestAssured.given()
                .baseUri(SERVER_URL)
                .when()
                .get(API_V1)
                .then().extract().body().asString();
        Assertions.assertEquals("real api service response v1", responseBody);
    }

    @Test
    public void apiMockTest() {
        String responseBody = RestAssured.given()
                .baseUri(MOCK_SERVER_URL)
                .when()
                .get(API_V1)
                .then().extract().body().asString();
        Assertions.assertEquals("mocked api service response v1", responseBody);
    }

    @Test
    public void setResponseValue() {
        var stub = stubFor(get(API_V2)
                .willReturn(aResponse().withBody("mocked api service response v2").withStatus(200)
                        ));

        String responseBody = RestAssured.given()
                .baseUri(MOCK_SERVER_URL)
                .when()
                .get(API_V2)
                .then().extract().body().asString();
        Assertions.assertEquals("mocked api service response v2", responseBody);

        WireMock.removeStub(stub);

        responseBody = RestAssured.given()
                .baseUri(MOCK_SERVER_URL)
                .when()
                .get(API_V2)
                .then().extract().body().asString();
        Assertions.assertEquals("real api service response v2", responseBody);
    }

    @Test
    public void proxyTest() {
        String responseBody = RestAssured.given()
                .baseUri(SERVER_URL)
                .when()
                .get(API_V2)
                .then().extract().body().asString();
        Assertions.assertEquals("real api service response v2", responseBody);
    }

    @Test
    public void proxyMockTest() {
        String responseBody = RestAssured.given()
                .baseUri(MOCK_SERVER_URL)
                .when()
                .get(API_V2)
                .then().extract().body().asString();
        Assertions.assertEquals("real api service response v2", responseBody);
    }
}
