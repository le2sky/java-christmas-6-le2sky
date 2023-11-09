package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Menu menu = Menu.of("까르보나라", 1_000);
        Menu other = Menu.of("까르보나라", 1_000);

        assertThat(menu).isEqualTo(other);
    }
}
