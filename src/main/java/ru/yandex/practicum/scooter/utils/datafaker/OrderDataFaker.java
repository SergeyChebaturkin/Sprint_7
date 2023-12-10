package ru.yandex.practicum.scooter.utils.datafaker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class OrderDataFaker extends DataFaker{
    public static String randomFirstName() {
        return faker.name().firstName();
    }
    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static String randomAddress() {
        return faker.address().streetAddress();
    }

    public static String randomPhone() {
        return fakeValuesService.regexify("(+7|8)9[1-9]{9}");
    }

    public static int randomMetroIndex() {
        return faker.number().numberBetween(1, 237);
    }

    public static int randomRentTime() {
        return Integer.parseInt(fakeValuesService.regexify("[1-7]{1}"));
    }

    public static String randomDeliveryDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(faker.date().future(7, TimeUnit.DAYS));
    }

    public static String randomComment() {
        return faker.rockBand().name();
    }
}
