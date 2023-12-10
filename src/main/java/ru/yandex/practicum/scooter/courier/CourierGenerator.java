package ru.yandex.practicum.scooter.courier;

import ru.yandex.practicum.scooter.models.Courier;
import static ru.yandex.practicum.scooter.utils.datafaker.CourierDataFaker.*;

public class CourierGenerator {

    private CourierGenerator() {}

    public static Courier randomValidCourier() {
        return new Courier()
                .withLogin(randomValidLogin())
                .withPassword(randomValidPassword())
                .withFirstName(randomValidFirstName());
    }

    public static Courier courierWithoutLogin() {
        return new Courier()
                .withPassword(randomValidPassword())
                .withFirstName(randomValidFirstName());
    }

    public static Courier courierWithoutPassword() {
        return new Courier()
                .withLogin(randomValidLogin())
                .withFirstName(randomValidFirstName());
    }

    public static Courier courierWithSameLogin(Courier courier) {
        return new Courier()
                .withLogin(courier.getLogin())
                .withPassword(randomValidPassword())
                .withFirstName(courier.getFirstName());
    }

    public static Courier courierWithSamePassword(Courier courier) {
        return new Courier()
                .withLogin(randomValidLogin())
                .withPassword(courier.getPassword())
                .withFirstName(courier.getFirstName());
    }
}
