package ru.yandex.practicum.scooter.courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.scooter.models.Courier;
import ru.yandex.practicum.scooter.models.CourierCreds;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.yandex.practicum.scooter.models.CourierCreds.fromCourier;

public class CourierClient {

    private static final String COURIER_URL = "api/v1/courier";
    private static final String COURIER_LOGIN_URL = "api/v1/courier/login";

    @Step("Creating courier {courier}")
    public Response create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER_URL);
    }

    @Step("Authorization with creds {courierCreds}")
    public Response login(CourierCreds courierCreds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierCreds)
                .when()
                .post(COURIER_LOGIN_URL);
    }

    @Step("Deleting courier")
    public Response delete(int id) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_URL + "/" + id);
    }

    @Step("Authorization and deleting earlier created courier")
    public Response loginAndDeleteCreatedCourier(Courier courier) {
        Response loginResponse = this.login(fromCourier(courier));
        int id = loginResponse.path("id");
        loginResponse.then().assertThat()
                .statusCode(SC_OK);
        return this.delete(id);
    }
}
