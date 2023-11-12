package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.common.Money;
import christmas.domain.menu.specific.MainMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class OrderTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Order order = Order.from(List.of(
                OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 2),
                OrderLineItem.of(MainMenu.of("불닭까르보나라", 1_000), 2)
        ));
    }

    @DisplayName("할인 이전 총 주문 금액을 계산할 수 있다.")
    @Test
    void calculateTotalPrice() {
        Order order = Order.from(List.of(
                OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 2),
                OrderLineItem.of(MainMenu.of("불닭까르보나라", 1_000), 2)
        ));

        Money result = order.calculateTotalPrice();

        assertThat(result).isEqualTo(Money.from(4_000));
    }
}
