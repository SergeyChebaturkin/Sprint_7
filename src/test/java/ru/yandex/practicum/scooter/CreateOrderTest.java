package ru.yandex.practicum.scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.scooter.models.Order;
import ru.yandex.practicum.scooter.order.OrderClient;

import static ru.yandex.practicum.scooter.order.OrderGenerator.*;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    private final String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"BLACK"}},
                {new String[] {"GREY"}},
                {new String[] {"BLACK","GREY"}},
                {new String[] {}}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("creating order")
    public void createOrderTest() {
        Order order = randomValidOrderWithColorParameterized(color);
        OrderClient orderClient = new OrderClient();
        Response response = orderClient.createOrder(order);
        response.then().assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(201);
    }
}
