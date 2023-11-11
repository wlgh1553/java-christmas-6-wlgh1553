package christmas.service;

import static christmas.service.EventService.getEstimatedCost;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.repository.Events;
import christmas.repository.Menus;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventServiceTest {
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

        assertThat(getEstimatedCost(menus.getTotalCost(), events.totalDiscountCost()))
                .isEqualTo(135_754);
    }
}
