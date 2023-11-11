package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.repository.Menus;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EventTest {
    @ParameterizedTest
    @MethodSource("provideDatesAndBenefitResults")
    @DisplayName("할인 금액을 계산한다.")
    void benefitCalculateTest(Integer date, Menus menus, List<Integer> benefits) {
        assertThat(Arrays.stream(Event.values())
                .map(event -> event.getBenefit(date, menus))
                .collect(Collectors.toList())).isEqualTo(benefits);
    }

    private static Stream<Arguments> provideDatesAndBenefitResults() {
        return Stream.of(
                Arguments.of(25, new Menus(new HashMap<>() {{
                    put("초코케이크", 1);
                }}), List.of(3400, 2023, 0, 1000, 0)),
                Arguments.of(22, new Menus(new HashMap<>() {{
                    put("바비큐립", 3);
                }}), List.of(3100, 0, 6069, 0, 25_000))
        );
    }
}
