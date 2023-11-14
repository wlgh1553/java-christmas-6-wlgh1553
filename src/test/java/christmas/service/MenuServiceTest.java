package christmas.service;

import static christmas.constant.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuServiceTest {
    @ParameterizedTest
    @ValueSource(strings = {"-", ",", "1-타파스", "", "타파스-1, 제로콜라-2", "1-19876543210", "파타스--1"
            , "파타스-1-제로콜라-1"})
    @DisplayName("{메뉴명}-{개수},형식을 지키지 않으면 예외가 발생한다.")
    void invalidFormatInputTest(String invalidInput) {
        assertThatThrownBy(() -> new MenuService(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-19876543210", "파타스-0"})
    @DisplayName("개수가 1 이상의 정수가 아닌 경우 않으면 예외가 발생한다.")
    void invalidNumberOfMenuTest(String invalidInput) {
        assertThatThrownBy(() -> new MenuService(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("중복 메뉴가 입력되면 예외가 발생한다.")
    void duplicatedMenuTest() {
        String duplicatedOrder = "타파스-1,타파스-1,제로콜라-1";
        assertThatThrownBy(() -> new MenuService(duplicatedOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("존재하지 않는 메뉴가 입력되면 예외가 발생한다.")
    void noSuchMenuTest() {
        String duplicatedOrder = "습하게띠-1";
        assertThatThrownBy(() -> new MenuService(duplicatedOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("메뉴 정보에 대한 출력용 리스트를 반환한다.")
    void getMenuInformationListTest() {
        String duplicatedOrder = "티본스테이크-1,초코케이크-2";
        MenuService menuService = new MenuService(duplicatedOrder);
        assertThat(menuService.getFormattedMenuInfos()).contains("티본스테이크 1개", "초코케이크 2개");
    }

    @Test
    @DisplayName("총 합계 금액을 ,를 찍어 형식에 맞는 문자열로 만든다.")
    void getFormattedTotalCostTest() {
        String duplicatedOrder = "티본스테이크-1,초코케이크-2";
        MenuService menuService = new MenuService(duplicatedOrder);
        assertThat(menuService.getFormattedTotalCost()).isEqualTo("85,000원");
    }
}
