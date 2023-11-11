package christmas.service;

import static christmas.domain.Badge.getBadge;

public class EventService {
    public static Integer getEstimatedCost(Integer totalCost, Integer discountCost) {
        return totalCost - discountCost;
    }

    public static String getBadgeName(Integer totalBenefit) {
        return getBadge(totalBenefit).getName();
    }
}
