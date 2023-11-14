package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.repository.Menus;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EventServiceTest {
    private static Map<String, Integer> exampleOrder
            = Map.of("티본스테이크", 1, "바비큐립", 1, "초코케이크", 2, "제로콜라", 1);

    @Test
    @DisplayName("증정 메뉴 리스트를 만든다.")
    void showGiftMenu() {
        Menus menus = new Menus(exampleOrder);
        EventService eventService = new EventService(3, menus);
        assertThat(eventService.getGiftMenu()).isEqualTo(List.of("샴페인 1개"));
    }

    @Test
    @DisplayName("증정 메뉴가 없으면 없음을 보여준다.")
    void showGiftMenuNoTest() {
        Menus menus = new Menus(Map.of("티본스테이크", 1));
        EventService eventService = new EventService(3, menus);
        assertThat(eventService.getGiftMenu()).isEqualTo(List.of("없음"));
    }

    @ParameterizedTest
    @MethodSource("provideDatesAndBenefitResults")
    @DisplayName("혜택 내역으로 출력될 문자열 확인")
    void benefitListTest(Integer date, Menus menus, List<String> benefitList) {
        EventService eventService = new EventService(date, menus);
        benefitList.forEach(benefitOutput ->
                assertThat(eventService.getBenefitDetails()).contains(benefitOutput));
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액을 포맷한다.")
    void estimatedCostTest() {
        Menus menus = new Menus(exampleOrder);
        EventService eventService = new EventService(3, menus);
        assertThat(eventService.getFormattedEstimatedCost()).isEqualTo("135,754원");
        assertThat(eventService.getBadgeName()).isEqualTo("산타");
    }

    private static Stream<Arguments> provideDatesAndBenefitResults() {
        return Stream.of(
                Arguments.of(3, new Menus(exampleOrder),
                        List.of("크리스마스 디데이 할인: -1,200원", "평일 할인: -4,046원",
                                "특별 할인: -1,000원", "증정 이벤트: -25,000원")),
                Arguments.of(26, new Menus(Map.of("타파스", 1, "제로콜라", 1)),
                        List.of("없음"))
        );
    }
}
