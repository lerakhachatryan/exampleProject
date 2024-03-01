import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class ReqresTests {


    @Test
    void successfulLogin() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void negativeLogin() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void createUser() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"nameofanotheruser\", \"job\": \"leader\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("nameofanotheruser"));
    }

    @Test
    void getCreatedUser() {
        given()
                .when()
                .get("https://reqres.in/api/users/12")
                .then()
                .statusCode(200)
                .body("name", is("nameofuser"));
    }

    @Test
    void updateUserWithPut() {
        given()
                .contentType(ContentType.JSON)
                //поменяем полностью тело
                .body("{ \"name\": \"morpheuse\", \"job\": \"zion ex-resident\"}")
                .when()
                .put("https://reqres.in/api/users/12")
                .then()
                .statusCode(200)
                .body("name", is("morpheuse"));
    }

    @Test
    void updateUserWithPatch() {
        given()
                .contentType(ContentType.JSON)
                //поменяем только имя
                .body("{ \"name\": \"morpheus\", \"job\": \"zion ex-resident\"}")
                .when()
                .patch("https://reqres.in/api/users/12")
                .then()
                .statusCode(200)
                .body("name", is("morpheus"));
    }


    @Test
    void deleteUser() {
        given()
                .when()
                .delete("https://reqres.in/api/users/12")
                .then()
                .statusCode(204);
    }
}
