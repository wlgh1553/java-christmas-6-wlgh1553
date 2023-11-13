package christmas.repository;

import static christmas.constant.ErrorMessage.CANNOT_ONLY_DRINK;
import static christmas.constant.ErrorMessage.CANNOT_ORDER_MORE_THAN_20;
import static christmas.constant.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MenusTest {
    @Test
    @DisplayName("주문한 메뉴의 총 가격을 계산한다.")
    void calculateTotalCost() {
        Menus menus = new Menus(new HashMap<>() {{
            put("타파스", 1);
            put("제로콜라", 3);
        }});

        assertThat(menus.getCostSum()).isEqualTo(14_500);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidOrdersAndExceptionMessages")
    @DisplayName("잘못된 주문이 들어오는 경우 예외가 발생한다.")
    void noSuchMenuException(Map<String, Integer> order, String exceptionMessage) {
        assertThatThrownBy(() -> new Menus(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(exceptionMessage);
    }

    private static Stream<Arguments> provideInvalidOrdersAndExceptionMessages() {
        return Stream.of(
                Arguments.of(new HashMap<>() {{
                    put("제로사이다", 3);
                }}, INVALID_ORDER.getMessage()),
                Arguments.of(new HashMap<>() {{
                    put("제로콜라", 3);
                }}, CANNOT_ONLY_DRINK.getMessage()),
                Arguments.of(new HashMap<>() {{
                    put("타파스", 1);
                    put("제로콜라", 20);
                }}, CANNOT_ORDER_MORE_THAN_20.getMessage())
        );
    }

    @Test
    @DisplayName("메인 메뉴 개수와 디저트 메뉴 개수를 계산한다.")
    void getNumberOfMainAndDessertMenu() {
        Map<String, Integer> inputOrders = new HashMap<>() {{
            put("양송이수프", 1);
            put("초코케이크", 4);
            put("티본스테이크", 2);
            put("바비큐립", 3);
            put("레드와인", 5);
        }};
        Menus menus = new Menus(inputOrders);
        assertThat(menus.getNumberOfMain()).isEqualTo(5);
        assertThat(menus.getNumberOfDessert()).isEqualTo(4);
    }
}
