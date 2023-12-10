package ru.yandex.practicum.scooter.models;

import ru.yandex.practicum.scooter.utils.datafaker.CourierDataFaker;

public class CourierCreds {

    private final String login;
    private final String password;

    public CourierCreds(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCreds fromCourier(Courier courier) {
        return new CourierCreds(courier.getLogin(), courier.getPassword());
    }

    public static CourierCreds fromCourierWithoutLogin(Courier courier) {
        return new CourierCreds(null, courier.getPassword());
    }

    public static CourierCreds fromCourierWithoutPassword(Courier courier) {
        return new CourierCreds(courier.getLogin(), null);
    }

    @Override
    public String toString() {
        return "CourierCreds{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
