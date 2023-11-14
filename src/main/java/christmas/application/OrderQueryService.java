package christmas.application;

import static christmas.global.util.ObjectUtil.requireNonNull;

import christmas.application.response.OrderLineItemResponse;
import christmas.application.response.OrderResponse;
import christmas.domain.common.Money;
import christmas.domain.event.Event;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuRepository;
import christmas.domain.order.Order;
import christmas.domain.order.OrderLineItem;
import java.util.List;

public class OrderQueryService {

    private static final String UNKNOWN_ORDER_MESSAGE = "알 수 없는 주문입니다.";
    private static final String PRESENT_MENU_NAME = "샴페인";

    private final MenuRepository menuRepository;

    public OrderQueryService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

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

    public Money queryTotalPrice(Order order) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return order.calculateTotalPrice();
    }

    public Menu queryPresentMenu(Event event) {
        return event.present(menuRepository.findByName(PRESENT_MENU_NAME));
    }
}
