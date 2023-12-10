package ru.yandex.practicum.scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.courier.CourierClient;
import ru.yandex.practicum.scooter.models.Courier;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;
import static ru.yandex.practicum.scooter.courier.CourierGenerator.*;
import static ru.yandex.practicum.scooter.models.CourierCreds.*;

public class LoginCourierTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("log in with valid parameters")
    public void loginCourier() {
        Courier courier = randomValidCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        Response response = courierClient.login(fromCourier(courier));
        response.then().assertThat()
                .body("id", notNullValue())
                .and()
                .statusCode(SC_OK);
        courierClient.delete(response.path("id"));
    }

    @Test
    @DisplayName("log in without login")
    public void loginCourierWithoutLogin() {
        Courier courier = randomValidCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        Response response = courierClient.login(fromCourierWithoutLogin(courier));
        response.then().assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
        courierClient.loginAndDeleteCreatedCourier(courier);
    }

    @Test
    @DisplayName("log in without password")
    public void loginCourierWithoutPassword() {
        Courier courier = randomValidCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        Response response = courierClient.login(fromCourierWithoutPassword(courier));
        response.then().assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
        courierClient.loginAndDeleteCreatedCourier(courier);
    }

    @Test
    @DisplayName("log in with wrong login")
    public void loginCourierWithWrongLogin() {
        Courier courier = randomValidCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        Courier courierWithSamePassword = courierWithSamePassword(courier);
        Response response = courierClient.login(fromCourier(courierWithSamePassword));
        response.then().assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
        courierClient.loginAndDeleteCreatedCourier(courier);
    }

    @Test
    @DisplayName("log in with wrong password")
    public void loginCourierWithWrongPassword() {
        Courier courier = randomValidCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        Courier courierWithSameLogin = courierWithSameLogin(courier);
        Response response = courierClient.login(fromCourier(courierWithSameLogin));
        response.then().assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
        courierClient.loginAndDeleteCreatedCourier(courier);
    }


}
