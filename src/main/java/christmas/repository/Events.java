package christmas.repository;

import static christmas.domain.Event.GIFT_EVENT;
import static christmas.domain.Menu.CHAMPAGNE;

import christmas.domain.Event;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Events {
    private Map<Event, Integer> events;

    public Events(Integer date, Menus menus) {
        events = new HashMap<>();

        if (!isOrderCostValid(menus.getCostSum())) {
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

    public Integer totalBenefitCost() {
        if (events.isEmpty()) {
            return 0;
        }
        return events.values().stream().reduce(0, Integer::sum);
    }

    public Integer totalDiscountCost() {
        if (events.isEmpty()) {
            return 0;
        }
        return events.entrySet().stream().filter(entry -> entry.getKey() != GIFT_EVENT)
                .map(Entry::getValue).reduce(0, Integer::sum);
    }

    public Map<String, Integer> getGiftNameAndNumber() {
        Map<String, Integer> gifts = new HashMap<>();
        if (events.containsKey(GIFT_EVENT)) {
            gifts.put(CHAMPAGNE.getName(), 1);
        }
        return gifts;
    }
}
