package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_ORDER;
import static christmas.domain.Menu.toMenu;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {
    @Test
    @DisplayName("없는 메뉴를 찾는 경우 예외를 발생시킨다.")
    void noSuchMenuTest() {
        String input = "고르곤졸라피자";
        assertThatThrownBy(() -> toMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("메뉴를 찾아 카테고리와 가격, 이름을 확인한다.")
    void findMenuTest() {
        String input = "초코케이크";
        Menu menu = toMenu(input);
        assertThat(menu.isMain()).isFalse();
        assertThat(menu.isDrink()).isFalse();
        assertThat(menu.isDessert()).isTrue();
        assertThat(menu.getCost()).isEqualTo(15_000);
        assertThat(menu.getName()).isEqualTo(input);
    }
}
