package christmas.repository;

import static christmas.constant.ErrorMessage.CANNOT_ONLY_DRINK;
import static christmas.constant.ErrorMessage.CANNOT_ORDER_MORE_THAN_20;
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

    @Test
    @DisplayName("음료만 주문한 경우 예외가 발생한다.")
    void onlyDrinkException() {
        Map<String, Integer> inputOrders = new HashMap<>() {{
            put("레드와인", 1);
            put("제로콜라", 3);
        }};

        assertThatThrownBy(() -> new Menus(inputOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CANNOT_ONLY_DRINK.getMessage());
    }

    @Test
    @DisplayName("20개 초과 주문 시 예외가 발생한다.")
    void moreThan20Exception() {
        Map<String, Integer> inputOrders = new HashMap<>() {{
            put("타파스", 10);
            put("제로콜라", 20);
        }};

        assertThatThrownBy(() -> new Menus(inputOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CANNOT_ORDER_MORE_THAN_20.getMessage());
    }

    @Test
    @DisplayName("메인 메뉴 개수와 디저트 메뉴 개수를 계산한다.")
    void getNumberOfMainAndDessertMenu() {
        Map<String, Integer> inputOrders = new HashMap<>() {{
            put("양송이수프", 1);
            put("티본스테이크", 2);
            put("바비큐립", 3);
            put("초코케이크", 4);
            put("레드와인", 5);
        }};
        Menus menus = new Menus(inputOrders);

        assertThat(menus.getNumberOfMain()).isEqualTo(5);
        assertThat(menus.getNumberOfDessert()).isEqualTo(4);
    }
}
