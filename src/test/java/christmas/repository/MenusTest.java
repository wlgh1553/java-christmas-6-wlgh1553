package christmas.repository;

import static christmas.constant.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenusTest {
    @Test
    @DisplayName("주문한 메뉴의 총 가격을 계산한다.")
    void calculateTotalCost() {
        Map<String, Integer> inputOrders = new HashMap<>() {{
            put("타파스", 1);
            put("제로콜라", 3);
        }};

        Menus menus = new Menus(inputOrders);

        assertThat(menus.getTotalCost()).isEqualTo(14_500);
    }
    
    @Test
    @DisplayName("없는 메뉴가 들어오는 경우 예외가 발생한다.")
    void noSuchMenuException() {
        Map<String, Integer> inputOrders = new HashMap<>() {{
            put("타파스", 1);
            put("제로사이다", 3);
        }};

        assertThatThrownBy(() -> new Menus(inputOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
    }
}
