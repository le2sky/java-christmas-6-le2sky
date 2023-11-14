package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuNameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        MenuName menu = MenuName.from("까르보나라");
        MenuName other = MenuName.from("까르보나라");

        assertThat(menu).isEqualTo(other);
    }

    @DisplayName("알 수 없는 문자열로 메뉴의 이름을 생성할 수 없다.")
    @Test
    void checkNameNonNull() {
        assertThatThrownBy(() -> MenuName.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 이름입니다.");
    }
}
