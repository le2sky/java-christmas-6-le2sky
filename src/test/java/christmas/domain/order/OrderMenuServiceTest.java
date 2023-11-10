package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.common.Money;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class OrderMenuServiceTest {

    private final OrderMenuService orderMenuService = OrderMenuService.from(createMenus());


    private Menus createMenus() {
        return Menus.from(List.of(
                Menu.of("까르보나라", 1_000),
                Menu.of("불닭볶음면", 2_000)));
    }

    @DisplayName("생성 테스트")
    @Test
    void create() {
        OrderMenuService orderMenuService = OrderMenuService.from(createMenus());
    }

    @DisplayName("알 수 없는 메뉴 목록으로 메뉴 주문 서비스를 생성할 수 없다.")
    @Test
    void checkMenusNonNull() {
        assertThatThrownBy(() -> OrderMenuService.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 메뉴 목록입니다.");
    }

    @DisplayName("신규 주문을 생성할 수 있다.")
    @Test
    void order() {
        OrderMenuService orderMenuService = OrderMenuService.from(createMenus());

        Order order = orderMenuService.order(List.of(OrderLineItem.of(Menu.of("까르보나라", 1_000), 2)));

        assertThat(order.calculateTotalPrice()).isEqualTo(Money.from(2_000));
    }

    @DisplayName("알 수 없는 주문 항목 목록으로 주문을 생성할 수 없다.")
    @Test
    void checkLineItemsNonNull() {
        assertThatThrownBy(() -> orderMenuService.order(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 주문 항목 목록입니다.");
    }

    @DisplayName("알 수 없는 주문 항목이 포함된 목록으로 주문을 생성할 수 없다.")
    @Test
    void checkLineItemsIncludeNull() {
        List<OrderLineItem> lineItems = new ArrayList<>();
        lineItems.add(OrderLineItem.of(Menu.of("까르보나라", 1_000), 2));
        lineItems.add(null);

        assertThatThrownBy(() -> orderMenuService.order(lineItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 주문 항목이 포함되어 있습니다.");
    }

    @DisplayName("최대 주문 수량은 20개이다.")
    @Test
    void checkTotalQuantity() {
        List<OrderLineItem> lineItems = new ArrayList<>();
        int maxCount = 21;
        for (int count = 0; count < maxCount; count = count + 2) {
            lineItems.add(OrderLineItem.of(Menu.of("까르보나라" + count, 1_000), 2));
        }

        assertThatThrownBy(() -> orderMenuService.order(lineItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 주문 수량은 20개입니다.");
    }

    @DisplayName("주문에는 중복 메뉴가 포함될 수 없다.")
    @Test
    void checkDuplicatedItem() {
        List<OrderLineItem> duplicatedLineItems = List.of(
                OrderLineItem.of(Menu.of("까르보나라", 1_000), 2),
                OrderLineItem.of(Menu.of("까르보나라", 1_000), 1)
        );

        assertThatThrownBy(() -> orderMenuService.order(duplicatedLineItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 주문 항목이 존재합니다.");
    }

    @DisplayName("적어도 한 개 이상의 주문을 포함해야 한다.")
    @Test
    void checkMinQuantity() {
        assertThatThrownBy(() -> orderMenuService.order(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적어도 1개 이상 주문해야합니다.");
    }

    @DisplayName("메뉴에 존재하지 않는 항목은 주문할 수 없다.")
    @Test
    void checkMenusNotIncludeItem() {
        List<OrderLineItem> notIncludedLineItems = List.of(
                OrderLineItem.of(Menu.of("존재하지 않는 상품명", 1_000), 2)
        );

        assertThatThrownBy(() -> orderMenuService.order(notIncludedLineItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 상품은 주문할 수 없습니다.");
    }
}
