package ru.yandex.practicum.scooter.models;

public class DetailedOrder {

    private int id;
    private int courierId;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private int track;
    private String[] color;
    private String comment;
    private String createdAt;
    private String updatedAt;
    private int status;

    public int getTrack() {
        return track;
    }
}
