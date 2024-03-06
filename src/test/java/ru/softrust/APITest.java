package ru.softrust;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class APITest {
    @Test
    void checkTotal20() {
        step("Проверяем, что количество элементов равно 20", () -> {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    });
    }

    @Test
    void checkTotal20WithAssertJ() {
        step("Проверяем ответ с помощью AssertJ", () -> {
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract()
                .path("total");

        System.out.println(response);

        assertThat(response).isEqualTo(20);
    });
    }
}

