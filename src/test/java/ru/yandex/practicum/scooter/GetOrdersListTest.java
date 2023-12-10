package ru.yandex.practicum.scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.models.DetailedOrder;
import ru.yandex.practicum.scooter.models.ListOfDetailedOrders;
import ru.yandex.practicum.scooter.models.Order;
import ru.yandex.practicum.scooter.order.OrderClient;

import static ru.yandex.practicum.scooter.order.OrderGenerator.randomValidOrderWithColorParameterized;


public class GetOrdersListTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("get orders list")
    public void getOrdersList() {
        Order order = randomValidOrderWithColorParameterized(new String[] {});
        OrderClient orderClient = new OrderClient();
        int trackId = orderClient.createOrder(order).path("track");
        ListOfDetailedOrders listOfDetailedOrders = orderClient
                .getOrdersList()
                .body()
                .as(ListOfDetailedOrders.class);
        Assert.assertFalse("Список заказов пустой", listOfDetailedOrders.getOrders().isEmpty());
        boolean hasOrderInList = false;
        for (DetailedOrder detailedOrder : listOfDetailedOrders.getOrders()) {
            if (detailedOrder.getTrack() == trackId) {
                hasOrderInList = true;
                break;
            }
        }
        Assert.assertTrue("В списке нет созданного заказа", hasOrderInList);
    }
}
