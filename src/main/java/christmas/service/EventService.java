package christmas.service;

import static christmas.domain.Badge.getBadge;

import christmas.repository.Events;
import christmas.repository.Menus;
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

    //혜택 내역 출력 직전까지 만들어서 반환하는 기능

    //총 혜택금액(할인+증정) 포맷해서 반환하는 기능
    //할인 후 예상 결제 금액 포맷해서 반환하는 기능
    //뱃지 이름 반환하는 기능


    //아래는 아직 손 안댄 함수들. 좀 바꿔야해
    public static Integer getEstimatedCost(Integer totalCost, Integer discountCost) {
        return totalCost - discountCost;
    }

    public static String getBadgeName(Integer totalBenefit) {
        return getBadge(totalBenefit).getName();
    }
}
