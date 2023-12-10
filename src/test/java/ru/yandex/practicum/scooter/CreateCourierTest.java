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

public class CreateCourierTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";


    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("creating courier with valid parameters")
    public void createCourier() {
        Courier courier = randomValidCourier();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        response.then().assertThat()
                .body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
        courierClient.loginAndDeleteCreatedCourier(courier);
    }

    @Test
    @DisplayName("creating already existing courier with same login, password and firstName")
    public void createAlreadyExistingCourierWithAllSameParameters() {
        Courier courier = randomValidCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        Response response = courierClient.create(courier);
        response.then().assertThat()
                .body("message", containsString("Этот логин уже используется"))
                .and()
                .statusCode(SC_CONFLICT);
        courierClient.loginAndDeleteCreatedCourier(courier);
    }

    @Test
    @DisplayName("creating already existing courier with same login")
    public void createAlreadyExistingCourierWithSameLogin() {
        Courier courier = randomValidCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        Courier courierWithSameLogin = courierWithSameLogin(courier);
        Response response = courierClient.create(courierWithSameLogin);
        response.then().assertThat()
                .body("message", containsString("Этот логин уже используется"))
                .and()
                .statusCode(SC_CONFLICT);
        courierClient.loginAndDeleteCreatedCourier(courier);
    }

    @Test
    @DisplayName("creating courier without login")
    public void createCourierWithoutLogin() {
        Courier courier = courierWithoutLogin();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        response.then().assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("creating courier without password")
    public void createCourierWithoutPassword() {
        Courier courier = courierWithoutPassword();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        response.then().assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }


}
