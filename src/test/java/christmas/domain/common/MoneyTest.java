package christmas.domain.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Money money = Money.from(1_000);
        Money other = Money.from(1_000);

        assertThat(money).isEqualTo(other);
    }

    @DisplayName("다른 돈과 더하기 연산을 수행할 수 있다.")
    @Test
    void add() {
        Money money = Money.from(1_000);

        Money result = money.add(Money.from(1_000));

        assertThat(result).isEqualTo(Money.from(2_000));
    }

    @DisplayName("알 수 없는 돈과 더하기 연산을 수행할 수 없다.")
    @Test
    void addWithNull() {
        Money money = Money.from(1_000);

        assertThatThrownBy(() -> money.add(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 돈과 해당 연산을 수행할 수 없습니다.");
    }

    @DisplayName("다른 돈과 빼기 연산을 수행할 수 있다.")
    @Test
    void subtract() {
        Money money = Money.from(1_000);

        Money result = money.subtract(Money.from(1_000));

        assertThat(result).isEqualTo(Money.from(0));
    }

    @DisplayName("알 수 없는 돈과 빼기 연산을 수행할 수 없다.")
    @Test
    void subtractWithNull() {
        Money money = Money.from(1_000);

        assertThatThrownBy(() -> money.subtract(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 돈과 해당 연산을 수행할 수 없습니다.");
    }
}
