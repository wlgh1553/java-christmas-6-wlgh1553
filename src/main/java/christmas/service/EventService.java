package christmas.service;

public class EventService {
    public static Integer getEstimatedCost(Integer totalCost, Integer discountCost) {
        return totalCost - discountCost;
    }

}
