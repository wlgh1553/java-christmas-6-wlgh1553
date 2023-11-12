package christmas.service;

import static christmas.service.EventService.getBadgeName;
import static christmas.service.EventService.getEstimatedCost;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.repository.Events;
import christmas.repository.Menus;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EventServiceTest {
    @Test
    @DisplayName("증정 메뉴 리스트를 만든다.")
    void showGiftMenu() {
        Menus menus = new Menus(new HashMap<>() {{
            put("티본스테이크", 1);
            put("바비큐립", 1);
            put("초코케이크", 2);
            put("제로콜라", 1);
        }});
        EventService eventService = new EventService(3, menus);
        assertThat(eventService.getGiftMenu()).isEqualTo(List.of("샴페인 1개"));
    }

    @Test
    @DisplayName("증정 메뉴가 없으면 없음을 보여준다.")
    void showGiftMenuNoTest() {
        Menus menus = new Menus(new HashMap<>() {{
            put("티본스테이크", 1);
        }});
        EventService eventService = new EventService(3, menus);
        assertThat(eventService.getGiftMenu()).isEqualTo(List.of("없음"));
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    void estimatedCostTest() {
        Menus menus = new Menus(new HashMap<>() {{
            put("티본스테이크", 1);
            put("바비큐립", 1);
            put("초코케이크", 2);
            put("제로콜라", 1);
        }});
        Events events = new Events(3, menus);

        assertThat(getEstimatedCost(menus.getCostSum(), events.totalDiscountCost()))
                .isEqualTo(135_754);
    }
    
    @ParameterizedTest
    @CsvSource(value = {"0 : 없음", "5000 : 별", "12345 : 트리", "30000 : 산타"}, delimiter = ':')
    @DisplayName("가격에 따라 뱃지를 제공한다.")
    void badgeTest(Integer benefitCost, String badgeName) {
        assertThat(getBadgeName(benefitCost)).isEqualTo(badgeName);
    }
}
