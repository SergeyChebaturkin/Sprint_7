package ru.yandex.practicum.scooter.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.scooter.models.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {
    private static final String ORDERS_URL = "/api/v1/orders";
    @Step("Creating order")
    public Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(ORDERS_URL);
    }

    @Step("Get list of all orders")
    public Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(ORDERS_URL);
    }
}
