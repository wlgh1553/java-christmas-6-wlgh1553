package christmas.service;

import static christmas.domain.Badge.getBadge;

import java.text.DecimalFormat;

public class EventService {
    //menus의 totalcost로부터 받아서 포맷팅
    public static String formatTotalCost(Integer totalCost) {
        DecimalFormat costFormat = new DecimalFormat("###,###원");
        return costFormat.format(totalCost);
    }
    
    public static String formatDiscountCost(Integer totalCost) {
        DecimalFormat costFormat = new DecimalFormat("-###,###원");
        return costFormat.format(totalCost);
    }

    public static Integer getEstimatedCost(Integer totalCost, Integer discountCost) {
        return totalCost - discountCost;
    }

    public static String getBadgeName(Integer totalBenefit) {
        return getBadge(totalBenefit).getName();
    }
}
