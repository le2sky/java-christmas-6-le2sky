package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.common.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Menu menu = Menu.of("까르보나라", 1_000);
        Menu other = Menu.of("까르보나라", 1_000);

        assertThat(menu).isEqualTo(other);
    }

    @DisplayName("수량이 주어지면 총 금액을 계산해서 반환한다.")
    @Test
    void calculatePriceWith() {
        Menu menu = Menu.of("까르보나라", 1_000);

        Money result = menu.calculatePriceWith(3);

        assertThat(result).isEqualTo(Money.from(3_000));
    }
}
