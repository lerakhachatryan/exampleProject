package ru.softrust;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class APITest {
    @Test
    void checkTotal20() {
        step("Проверяем, что общее количесвто элементов равно 20", () -> {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    });
    }

}
