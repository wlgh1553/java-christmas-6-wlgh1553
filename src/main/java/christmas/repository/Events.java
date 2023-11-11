package christmas.repository;

import christmas.domain.Event;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    public List<String> getEventList() {
        if (events.isEmpty()) {
            return new ArrayList<>(List.of("없음"));
        }
        return events.entrySet().stream().map(entry ->
                getEventInfo(entry.getKey(), entry.getValue())).toList();
    }

    private String getEventInfo(Event event, Integer benefit) {
        DecimalFormat eventFormat = new DecimalFormat(": -###,###원");
        return event.getEventName() + eventFormat.format(benefit);
    }

    
}
