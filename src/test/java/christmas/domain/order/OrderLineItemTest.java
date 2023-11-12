package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.common.Money;
import christmas.domain.menu.specific.MainMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderLineItemTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        OrderLineItem item = OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 2);
    }

    @DisplayName("알 수 없는 메뉴로 주문 항목을 생성할 수 없다.")
    @Test
    void checkMenuNonNull() {
        assertThatThrownBy(() -> OrderLineItem.of(null, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 메뉴입니다.");
    }

    @DisplayName("수량은 양수여야 한다.")
    @Test
    void checkPositiveQuantity() {
        assertThatThrownBy(() -> OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("각 주문 항목의 수량은 적어도 1개 이상이어야 합니다.");
    }

    @DisplayName("각 주문 항목의 금액을 계산할 수 있다.")
    @Test
    void calculateEachPrice() {
        OrderLineItem item = OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 2);

        Money result = item.calculateEachPrice();

        assertThat(result).isEqualTo(Money.from(2_000));
    }
}
