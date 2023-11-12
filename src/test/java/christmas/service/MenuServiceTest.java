package christmas.service;

import static christmas.constant.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuServiceTest {
    @ParameterizedTest
    @ValueSource(strings = {"-", ",", "1-타파스", "", "타파스-1, 제로콜라-2", "1-19876543210"})
    @DisplayName("{메뉴명}-{개수},형식을 지키지 않으면 예외가 발생한다.")
    void invalidFormatInputTest(String invalidInput) {
        assertThatThrownBy(() -> new MenuService(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
    }


}
