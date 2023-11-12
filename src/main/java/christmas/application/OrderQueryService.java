package christmas.application;

import static christmas.global.util.ObjectUtil.requireNonNull;

import christmas.application.response.OrderLineItemResponse;
import christmas.application.response.OrderResponse;
import christmas.domain.order.Order;
import christmas.domain.order.OrderLineItem;
import java.util.List;

public class OrderQueryService {

    private static final String UNKNOWN_ORDER_MESSAGE = "알 수 없는 주문입니다.";

    public OrderResponse queryOrderResult(Order order) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return mapToResponseEntity(order.getLineItems());
    }

    private OrderResponse mapToResponseEntity(List<OrderLineItem> lineItems) {
        return new OrderResponse(lineItems.stream()
                .map(this::mapToOrderLineItemResponse)
                .toList());
    }

    public OrderLineItemResponse mapToOrderLineItemResponse(OrderLineItem orderLineItem) {
        return new OrderLineItemResponse(orderLineItem.getName(), orderLineItem.getQuantity());
    }
}
