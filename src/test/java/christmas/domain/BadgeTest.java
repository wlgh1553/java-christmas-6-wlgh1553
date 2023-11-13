package christmas.domain;

import static christmas.domain.Badge.STAR;
import static christmas.domain.Badge.getBadge;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadgeTest {
    @Test
    @DisplayName("가격에 따라 뱃지를 판단한다.")
    void badgeResultTest() {
        int cost = 5000;
        assertThat(getBadge(cost)).isEqualTo(STAR);
    }
}
