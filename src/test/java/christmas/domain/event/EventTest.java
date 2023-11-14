package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.common.Date;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import christmas.domain.menu.specific.BeverageMenu;
import christmas.domain.menu.specific.MainMenu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderLineItem;
import christmas.domain.order.OrderMenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class EventTest {

    private final OrderMenuService orderMenuService =
            OrderMenuService.from(Menus.from(List.of(MainMenu.of("잡채밥", 20_000))));

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Event event = Event.of(createOrder(6), Date.from(2));
    }

    @DisplayName("알 수 없는 주문으로 이벤트를 생성할 수 없다.")
    @Test
    void checkOrderNonNull() {
        assertThatThrownBy(() -> Event.of(null, Date.from(2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 주문입니다.");
    }

    @DisplayName("알 수 없는 주문 일자로 이벤트를 생성할 수 없다.")
    @Test
    void checkOrderDateNonNull() {
        assertThatThrownBy(() -> Event.of(createOrder(6), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 주문 일자입니다.");
    }

    @DisplayName("할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개를 증정한다.")
    @Test
    void presentMenu() {
        Event event = Event.of(createOrder(6), Date.from(2));

        Menu result = event.present(BeverageMenu.of("샴페인", 2_000));

        assertThat(result.getName()).isEqualTo("샴페인");
    }

    @DisplayName("할인 전 총주문 금액이 12만 원보다 낮다면, 아무것도 증정하지 않는다.")
    @Test
    void presentEmptyMenu() {
        Event event = Event.of(createOrder(1), Date.from(2));

        Menu result = event.present(BeverageMenu.of("샴페인", 2_000));

        assertThat(result.getName()).isEqualTo("없음");
    }

    private Order createOrder(int quantity) {
        return orderMenuService.order(
                List.of(OrderLineItem.of(MainMenu.of("잡채밥", 20_000), quantity)));
    }
}
