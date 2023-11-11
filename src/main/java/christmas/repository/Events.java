package christmas.repository;

import christmas.domain.Event;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Events {
    private Map<Event, Integer> events;

    public Events(int date, Menus menus) {
        events = new HashMap<>();

        if (!isOrderCostValid(menus.getTotalCost())) {
            return;
        }

        Arrays.stream(Event.values())
                .filter(event -> event.getBenefit(date, menus) != 0)
                .forEach(event -> events.put(event, event.getBenefit(date, menus)));
    }

    private Boolean isOrderCostValid(Integer orderCost) {
        return orderCost >= 10_000;
    }


}
