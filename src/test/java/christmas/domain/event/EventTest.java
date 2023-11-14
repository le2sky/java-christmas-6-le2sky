package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.common.Date;
import christmas.domain.menu.Menus;
import christmas.domain.menu.specific.MainMenu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderLineItem;
import christmas.domain.order.OrderMenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class EventTest {

    private final OrderMenuService orderMenuService =
            OrderMenuService.from(Menus.from(List.of(MainMenu.of("잡채밥", 2_000))));

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Order order = createOrder();
        Event event = Event.of(order, Date.from(2));
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
        assertThatThrownBy(() -> Event.of(createOrder(), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 주문 일자입니다.");
    }

    private Order createOrder() {
        return orderMenuService.order(
                List.of(OrderLineItem.of(MainMenu.of("잡채밥", 2_000), 2)));
    }
}
