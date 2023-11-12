package christmas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EventsTest {
    @ParameterizedTest
    @MethodSource("provideDatesAndBenefitSum")
    @DisplayName("총 혜택 금액 계산")
    void totalBenefitTest(Integer date, Menus menus, Integer totalSum) {
        assertThat(new Events(date, menus).totalBenefitCost()).isEqualTo(totalSum);
    }

    @ParameterizedTest
    @MethodSource("provideDatesAndEstimatedCost")
    @DisplayName("예상 결제 금액 계산")
    void totalEstimatedCostTest(Integer date, Menus menus, Integer totalSum) {
        Events events = new Events(date, menus);
        assertThat(events.getEstimatedCost()).isEqualTo(totalSum);
    }

    private static Stream<Arguments> provideDatesAndBenefitSum() {
        return Stream.of(
                Arguments.of(3, new Menus(new HashMap<>() {{
                    put("티본스테이크", 1);
                    put("바비큐립", 1);
                    put("초코케이크", 2);
                    put("제로콜라", 1);
                }}), 31_246),
                Arguments.of(26, new Menus(new HashMap<>() {{
                    put("타파스", 1);
                    put("제로콜라", 1);
                }}), 0)
        );
    }

    private static Stream<Arguments> provideDatesAndEstimatedCost() {
        return Stream.of(
                Arguments.of(3, new Menus(new HashMap<>() {{
                    put("티본스테이크", 1);
                    put("바비큐립", 1);
                    put("초코케이크", 2);
                    put("제로콜라", 1);
                }}), 135_754),
                Arguments.of(26, new Menus(new HashMap<>() {{
                    put("타파스", 1);
                    put("제로콜라", 1);
                }}), 8500)
        );
    }
}
