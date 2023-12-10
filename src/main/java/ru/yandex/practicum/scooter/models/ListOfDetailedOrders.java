package ru.yandex.practicum.scooter.models;

import java.util.List;

public class ListOfDetailedOrders {
    private List<DetailedOrder> orders;
    private PageInfo pageInfo;
    private List<AvailableStations> availableStations;

    public List<DetailedOrder> getOrders() {
        return orders;
    }
}
