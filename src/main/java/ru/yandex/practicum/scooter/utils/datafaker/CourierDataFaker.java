package ru.yandex.practicum.scooter.utils.datafaker;

public class CourierDataFaker extends DataFaker {
    public static String randomValidLogin() {
        return faker.name().username();
    }
    public static String randomValidPassword() {
        return fakeValuesService.regexify("[A-Z]{1}[a-z]{4}[1-9]{5}");
    }
    public static String randomValidFirstName() {
        return faker.name().firstName();
    }
}
