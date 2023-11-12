package christmas.service;

import static christmas.domain.Badge.getBadge;

import christmas.repository.Events;
import christmas.repository.Menus;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventService {
    private Events events;

    public EventService(Integer date, Menus menus) {
        events = new Events(date, menus);
    }

    public List<String> getGiftMenu() {
        Map<String, Integer> gifts = events.getGiftNameAndNumber();
        if (gifts.isEmpty()) {
            return new ArrayList<>(List.of("없음"));
        }
        return gifts.entrySet().stream().map(entry -> entry.getKey() + " "
                + entry.getValue() + "개").toList();
    }

    public List<String> getBenefitDetails() {
        Map<String, Integer> benefits = events.getEventDetails();
        if (benefits.isEmpty()) {
            return new ArrayList<>(List.of("없음"));
        }
        return benefits.entrySet().stream()
                .map(entry -> getFormattedBenefitDetail(entry.getKey(), entry.getValue()))
                .toList();
    }

    private String formatMinusCost(Integer cost) {
        if (cost == 0) {
            return "0원";
        }
        DecimalFormat minusFormat = new DecimalFormat("-###,###원");
        return minusFormat.format(cost);
    }

    private String getFormattedBenefitDetail(String name, Integer cost) {
        return name + ": " + formatMinusCost(cost);
    }

    public String getFormattedTotalBenefitCost() {
        return formatMinusCost(events.totalBenefitCost());
    }

    public String getFormattedEstimatedCost() {
        DecimalFormat costFormat = new DecimalFormat("###,###원");
        return costFormat.format(events.getEstimatedCost());
    }

    public String getBadgeName() {
        return getBadge(events.totalBenefitCost()).getName();
    }
}
