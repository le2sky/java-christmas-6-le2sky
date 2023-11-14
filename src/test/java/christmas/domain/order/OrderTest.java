package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.common.Money;
import christmas.domain.menu.Menu;
import christmas.domain.menu.specific.BeverageMenu;
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


    @DisplayName("할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개를 증정한다.")
    @Test
    void presentMenu() {
        Order order = Order.from(List.of(
                OrderLineItem.of(MainMenu.of("까르보나라", 100_000), 2)));

        Menu result = order.present(BeverageMenu.of("샴페인", 2_000));

        assertThat(result.getName()).isEqualTo("샴페인");
    }

    @DisplayName("할인 전 총주문 금액이 12만 원보다 낮다면, 아무것도 증정하지 않는다.")
    @Test
    void presentEmptyMenu() {
        Order order = Order.from(List.of(
                OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 2),
                OrderLineItem.of(MainMenu.of("불닭까르보나라", 1_000), 2)
        ));

        Menu result = order.present(BeverageMenu.of("샴페인", 2_000));

        assertThat(result.getName()).isEmpty();
    }
}
