package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_DATE;
import static christmas.domain.Day.isDdayPeriod;
import static christmas.domain.Day.isSpecialDay;
import static christmas.domain.Day.isWeekday;
import static christmas.domain.Day.isWeekend;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DayTest {
    @Test
    @DisplayName("날짜가 1이상 31이하가 아닌데 주말인지 물어보면 예외가 발생한다.")
    void dateExceptionTest() {
        int date = 0;
        assertThatThrownBy(() -> isWeekend(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE.getMessage());
    }

    @Test
    @DisplayName("날짜를 넣으면 주말인지 주중인지 알려준다.")
    void weekdayTest() {
        int date = 25;
        assertThat(isWeekend(date)).isFalse();
        assertThat(isWeekday(date)).isTrue();
    }

    @Test
    @DisplayName("날짜를 넣으면 별표시된 날짜인지 알려준다.")
    void specialDayTest() {
        int specialDate = 3, noSpecialDate = 4;
        assertThat(isSpecialDay(specialDate)).isTrue();
        assertThat(isSpecialDay(noSpecialDate)).isFalse();
    }

    @Test
    @DisplayName("날짜를 넣으면 디데이 할인 기간인지 알려준다.")
    void dDayPeriodTest() {
        int preChristmas = 10;
        int posChristmas = 30;
        assertThat(isDdayPeriod(preChristmas)).isTrue();
        assertThat(isDdayPeriod(posChristmas)).isFalse();
    }
}
