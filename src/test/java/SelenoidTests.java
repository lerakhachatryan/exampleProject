import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.restassured.specification.Argument;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenoidTests {

    //make request to url = 'https://selenoid.autotests.cloud/status'

    //check total = 20

    @Test
    void checkTotal20() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotal20WithoutGiven() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotal20WithResponseAndBadPractice() {
        String response =
        get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().response().asString();

        System.out.println(response);

        //DONT DO THAT, AWFUL!
        assertEquals("{\n" +
                "  \"total\": 20,\n" +
                "  \"used\": 0,\n" +
                "  \"queued\": 0,\n" +
                "  \"pending\": 0,\n" +
                "  \"browsers\": {\n" +
                "    \"android\": {\n" +
                "      \"8.1\": { }\n" +
                "    },\n" +
                "    \"chrome\": {\n" +
                "      \"100.0\": { },\n" +
                "      \"99.0\": { }\n" +
                "    },\n" +
                "    \"chrome-mobile\": {\n" +
                "      \"86.0\": { }\n" +
                "    },\n" +
                "    \"firefox\": {\n" +
                "      \"97.0\": { },\n" +
                "      \"98.0\": { }\n" +
                "    },\n" +
                "    \"opera\": {\n" +
                "      \"84.0\": { },\n" +
                "      \"85.0\": { }\n" +
                "    }\n" +
                "  }\n" +
                "}", response);
    }

    @Test
    void checkTotal20WithAssertJ() {
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract()
                .path("total");

        System.out.println(response);

        assertThat(response).isEqualTo(20);

    }

    @Test
    void checkWbHubStatus401() {
        get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(401);

    }

    @Test
    void checkWbHubStatus200() {
        get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200)
                .body("value.ready", is(true));


    }

    @Test
    void checkWbHubStatus200WithAuth() {
        given()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200)
                .body("value.ready", is(true));
    }
}
