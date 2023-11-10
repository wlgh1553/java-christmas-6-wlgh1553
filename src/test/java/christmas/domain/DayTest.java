package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_DATE;
import static christmas.domain.Day.MON;
import static christmas.domain.Day.getDay;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DayTest {
    @Test
    @DisplayName("날짜가 1이상 31이하가 아닌경우 예외가 발생한다.")
    void dateExceptionTest() {
        int date = 0;
        assertThatThrownBy(() -> getDay(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE.getMessage());
    }

    @Test
    @DisplayName("날짜를 넣으면 요일은 반환한다.")
    void dateToDayTest() {
        int date = 25;
        assertThat(getDay(date)).isEqualTo(MON);
    }
}
