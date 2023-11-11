package christmas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EventsTest {
    @ParameterizedTest
    @MethodSource("provideDatesAndBenefitResults")
    @DisplayName("혜택 내역으로 출력될 문자열 확인")
    void benefitListTest(Integer date, Menus menus, List<String> benefitList) {
        Events events = new Events(date, menus);
        benefitList.forEach(benefitOutput ->
                assertThat(events.getEventList()).contains(benefitOutput));
    }

    private static Stream<Arguments> provideDatesAndBenefitResults() {
        return Stream.of(
                Arguments.of(3, new Menus(new HashMap<>() {{
                    put("티본스테이크", 1);
                    put("바비큐립", 1);
                    put("초코케이크", 2);
                    put("제로콜라", 1);
                }}), List.of("크리스마스 디데이 할인: -1,200원",
                        "평일 할인: -4,046원",
                        "특별 할인: -1,000원",
                        "증정 이벤트: -25,000원")),
                Arguments.of(26, new Menus(new HashMap<>() {{
                    put("타파스", 1);
                    put("제로콜라", 1);
                }}), List.of("없음"))
        );
    }
}
