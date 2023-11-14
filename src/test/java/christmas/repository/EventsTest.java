package christmas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EventsTest {
    @ParameterizedTest
    @MethodSource("provideDatesAndCost")
    @DisplayName("총 혜택 금액과 예상 결제 금액 계산")
    void totalEstimatedCostTest(Integer date, Menus menus, Integer benefitCost, Integer totalSum) {
        Events events = new Events(date, menus);
        assertThat(events.totalBenefitCost()).isEqualTo(benefitCost);
        assertThat(events.getEstimatedCost()).isEqualTo(totalSum);
    }

    private static Stream<Arguments> provideDatesAndCost() {
        return Stream.of(
                Arguments.of(3, new Menus(Map.of("티본스테이크", 1, "바비큐립", 1,
                        "초코케이크", 2, "제로콜라", 1)), 31_246, 135_754),
                Arguments.of(26, new Menus(Map.of("타파스", 1, "제로콜라", 1)),
                        0, 8500)
        );
    }
}
