package christmas.application;

import static christmas.util.ObjectUtil.requireIncludeNonNull;
import static christmas.util.ObjectUtil.requireNonNull;

import christmas.application.request.OrderLineItemRequest;
import christmas.application.request.OrderRequest;
import christmas.domain.menu.MenuRepository;
import christmas.domain.order.Order;
import christmas.domain.order.OrderLineItem;
import christmas.domain.order.OrderMenuService;
import java.util.List;

public class OrderService {

    private static final String INVALID_REQUEST_MESSAGE = "유효한 요청을 입력해주세요.";

    private final OrderMenuService orderMenuService;
    private final MenuRepository menuRepository;

    public OrderService(MenuRepository menuRepository) {
        this.orderMenuService = OrderMenuService.from(menuRepository.findAll());
        this.menuRepository = menuRepository;
    }

    public Order order(OrderRequest request) {
        requireNonNull(request, INVALID_REQUEST_MESSAGE);

        return orderMenuService.order(mapToDomainEntity(request));
    }

    private List<OrderLineItem> mapToDomainEntity(OrderRequest request) {
        List<OrderLineItemRequest> orderLineItemRequests = request.orderLineItemRequests();
        requireNonNull(orderLineItemRequests, INVALID_REQUEST_MESSAGE);
        requireIncludeNonNull(orderLineItemRequests, INVALID_REQUEST_MESSAGE);

        return orderLineItemRequests.stream()
                .map(this::mapToOrderLineItem)
                .toList();
    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemRequest itemRequest) {
        return OrderLineItem.of(menuRepository.findByName(itemRequest.name()), itemRequest.quantity());
    }
}
