package ru.yandex.practicum.scooter.order;

import ru.yandex.practicum.scooter.models.Order;

import static ru.yandex.practicum.scooter.utils.datafaker.OrderDataFaker.*;


public class OrderGenerator {

    private OrderGenerator() {}

    public static Order randomValidOrderWithColorParameterized(String[] color) {
        return new Order()
                .withFirstName(randomFirstName())
                .withLastName(randomLastName())
                .withAddress(randomAddress())
                .withMetroStation(Integer.toString(randomMetroIndex()))
                .withPhone(randomPhone())
                .withRentTime(randomRentTime())
                .withDeliveryDate(randomDeliveryDate())
                .withComment(randomComment())
                .withColor(color);
    }
}
