package christmas.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.application.request.OrderLineItemRequest;
import christmas.application.request.OrderRequest;
import christmas.application.response.OrderLineItemResponse;
import christmas.application.response.OrderResponse;
import christmas.domain.order.Order;
import christmas.infrastructure.persistence.menu.SimpleMenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class OrderQueryServiceTest {

    private final OrderQueryService orderQueryService = new OrderQueryService();

    @DisplayName("주문 내역을 조회할 수 있다")
    @Test
    void queryOrderResult() {
        OrderService orderService = new OrderService(new SimpleMenuRepository());
        OrderRequest orderRequest = new OrderRequest(List.of(
                new OrderLineItemRequest("해산물파스타", 3)
        ));
        Order order = orderService.order(orderRequest);

        OrderResponse result = orderQueryService.queryOrderResult(order);

        OrderResponse expected = new OrderResponse(List.of(new OrderLineItemResponse("해산물파스타", 3)));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("알 수 없는 주문으로 결과 객체를 조회할 수 없다.")
    @Test
    void queryWithNull() {
        assertThatThrownBy(() -> orderQueryService.queryOrderResult(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 주문입니다.");
    }
}
