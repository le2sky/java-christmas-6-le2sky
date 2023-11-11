package christmas.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.application.request.OrderLineItemRequest;
import christmas.application.request.OrderRequest;
import christmas.domain.common.Money;
import christmas.domain.order.Order;
import christmas.infrastructure.persistence.menu.SimpleMenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class OrderServiceTest {

    private final OrderService orderService = new OrderService(new SimpleMenuRepository());

    @DisplayName("신규 주문을 생성할 수 있다.")
    @Test
    void order() {
        OrderRequest orderRequest = new OrderRequest(List.of(
                new OrderLineItemRequest("양송이수프", 2),
                new OrderLineItemRequest("티본스테이크", 1)));

        Order result = orderService.order(orderRequest);

        assertThat(result.calculateTotalPrice()).isEqualTo(Money.from(67_000));
    }

    @DisplayName("알 수 없는 요청으로 신규 주문을 생성할 수 없다.")
    @Test
    void orderWithNull() {
        assertThatThrownBy(() -> orderService.order(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 요청을 입력해주세요.");
    }

    @DisplayName("알 수 없는 항목 리스트가 포함된 요청으로 신규 주문을 생성할 수 없다.")
    @Test
    void requestListNonNull() {
        OrderRequest orderRequest = new OrderRequest(null);

        assertThatThrownBy(() -> orderService.order(orderRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 요청을 입력해주세요.");
    }

    @DisplayName("알 수 없는 항목이 포함된 요청으로 신규 주문을 생성할 수 없다.")
    @Test
    void requestIncludeNull() {
        List<OrderLineItemRequest> orderLineItemRequests = new ArrayList<>();
        orderLineItemRequests.add(new OrderLineItemRequest("양송이수프", 2));
        orderLineItemRequests.add(null);
        OrderRequest orderRequest = new OrderRequest(orderLineItemRequests);

        assertThatThrownBy(() -> orderService.order(orderRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 요청을 입력해주세요.");
    }

    @DisplayName("존재하지 않는 메뉴를 주문할 수 없다.")
    @Test
    void orderWithUnknownMenu() {
        OrderRequest orderRequest = new OrderRequest(List.of(
                new OrderLineItemRequest("양송이수프", 2),
                new OrderLineItemRequest("까르보나라", 1)));

        assertThatThrownBy(() -> orderService.order(orderRequest))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 메뉴입니다.");
    }
}
