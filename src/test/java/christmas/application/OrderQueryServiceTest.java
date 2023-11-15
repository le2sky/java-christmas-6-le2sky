package christmas.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.application.request.OrderLineItemRequest;
import christmas.application.request.OrderRequest;
import christmas.application.response.OrderBenefitItemResponse;
import christmas.application.response.OrderBenefitResponse;
import christmas.application.response.OrderLineItemResponse;
import christmas.application.response.OrderResponse;
import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.infrastructure.SimpleMenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class OrderQueryServiceTest {

    private final OrderQueryService orderQueryService = new OrderQueryService(new SimpleMenuRepository());

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

    @DisplayName("알 수 없는 주문에 대한 증정 메뉴를 조회할 수 없다.")
    @Test
    void queryPresentMenuWithNull() {
        assertThatThrownBy(() -> orderQueryService.queryPresentMenu(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 주문입니다.");
    }

    @DisplayName("주문에 대한 증정 메뉴를 조회할 수 있다.")
    @Test
    void queryPresentMenu() {
        OrderService orderService = new OrderService(new SimpleMenuRepository());
        OrderRequest orderRequest = new OrderRequest(List.of(
                new OrderLineItemRequest("해산물파스타", 12)
        ));
        Order order = orderService.order(orderRequest);

        Menu result = orderQueryService.queryPresentMenu(order);

        assertThat(result.getName()).isEqualTo("샴페인");
    }

    @DisplayName("알 수 없는 주문의 총 주문 금액을 조회할 수 없다.")
    @Test
    void queryTotalPriceWithNull() {
        assertThatThrownBy(() -> orderQueryService.queryTotalPrice(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 주문입니다.");
    }

    @DisplayName("할인 이전 총 주문 금액을 조회할 수 있다.")
    @Test
    void queryTotalPrice() {
        OrderService orderService = new OrderService(new SimpleMenuRepository());
        OrderRequest orderRequest = new OrderRequest(List.of(
                new OrderLineItemRequest("해산물파스타", 2)
        ));
        Order order = orderService.order(orderRequest);

        Money result = orderQueryService.queryTotalPrice(order);

        assertThat(result).isEqualTo(Money.from(70_000));
    }

    @DisplayName("혜택 결과를 조회할 수 있다.")
    @Test
    void queryOrderBenefits() {
        OrderService orderService = new OrderService(new SimpleMenuRepository());
        OrderRequest orderRequest = new OrderRequest(List.of(
                new OrderLineItemRequest("해산물파스타", 12)
        ));
        Order order = orderService.order(orderRequest);

        OrderBenefitResponse result = orderQueryService.queryOrderBenefits(order, Date.from(3));

        OrderBenefitResponse expected = new OrderBenefitResponse(
                List.of(
                        new OrderBenefitItemResponse("크리스마스 디데이 할인", Money.from(-1_200)),
                        new OrderBenefitItemResponse("특별 할인", Money.from(-1_000)),
                        new OrderBenefitItemResponse("증정 이벤트", Money.from(-25_000))
                ));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("알 수 없는 주문의 혜택 결과를 조회할 수 없다.")
    @Test
    void queryOrderBenefitsWithNullOrder() {
        assertThatThrownBy(() -> orderQueryService.queryOrderBenefits(null, Date.from(3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 주문입니다.");
    }
}
