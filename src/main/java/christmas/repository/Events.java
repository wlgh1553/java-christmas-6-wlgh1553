package christmas.repository;

import static christmas.domain.Event.GIFT_EVENT;
import static christmas.domain.Menu.CHAMPAGNE;

import christmas.domain.Event;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Events {
    private Map<Event, Integer> events;
    //결국 총 금액을 기억해야 하는구나...
    private Integer totalMenuCost;

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
        totalMenuCost = orderCost;
        return orderCost >= 10_000;
    }

    public Map<String, Integer> getEventDetails() {
        Map<String, Integer> benefits = new HashMap<>();
        events.entrySet().forEach(
                entry -> benefits.put(entry.getKey().getEventName(), entry.getValue())
        );
        return benefits;
    }

    public Integer totalBenefitCost() {
        if (events.isEmpty()) {
            return 0;
        }
        return events.values().stream().reduce(0, Integer::sum);
    }

    public Integer getEstimatedCost() {
        System.out.println(totalMenuCost);
        return totalMenuCost - totalDiscountCost();
    }

    private Integer totalDiscountCost() {
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
