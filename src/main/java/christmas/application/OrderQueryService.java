package christmas.application;

import static christmas.global.util.ObjectUtil.requireNonNull;

import christmas.application.response.OrderBenefitItemResponse;
import christmas.application.response.OrderBenefitResponse;
import christmas.application.response.OrderLineItemResponse;
import christmas.application.response.OrderResponse;
import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DiscountResult;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuRepository;
import christmas.domain.order.Order;
import christmas.domain.order.OrderEventBadge;
import christmas.domain.order.OrderLineItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderQueryService {

    public static final int PRESENT_MENU_COUNT = 1;
    private static final String PRESENT_MENU_NAME = "샴페인";
    private static final String PRESENT_EVENT_NAME = "증정 이벤트";
    private static final String UNKNOWN_ORDER_MESSAGE = "알 수 없는 주문입니다.";

    private final MenuRepository menuRepository;

    public OrderQueryService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public OrderResponse queryOrderResult(Order order) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return mapToOrderResponse(order.getLineItems());
    }

    private OrderResponse mapToOrderResponse(List<OrderLineItem> lineItems) {
        return new OrderResponse(lineItems.stream()
                .map(this::mapToOrderLineItemResponse)
                .toList());
    }

    private OrderLineItemResponse mapToOrderLineItemResponse(OrderLineItem orderLineItem) {
        return new OrderLineItemResponse(orderLineItem.getName(), orderLineItem.getQuantity());
    }

    public OrderEventBadge queryPresentBadge(Order order, Date orderDate) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);
        Money totalBenefitAmount = queryOrderBenefits(order, orderDate).getTotalBenefitAmount();
        System.out.println(totalBenefitAmount.getAmount());

        return OrderEventBadge.from(totalBenefitAmount);
    }

    public OrderBenefitResponse queryOrderBenefits(Order order, Date orderDate) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return new OrderBenefitResponse(
                Collections.unmodifiableList(createOrderBenefitItemResponses(order, orderDate))
        );
    }

    private List<OrderBenefitItemResponse> createOrderBenefitItemResponses(Order order, Date orderDate) {
        List<OrderBenefitItemResponse> orderBenefitItemResponses = new ArrayList<>(
                mapToOrderBenefitItemResponses(order.calculateDiscountBenefit(orderDate))
        );
        Menu presentMenu = queryPresentMenu(order);

        if (presentMenu.getName().equals(PRESENT_MENU_NAME)) {
            orderBenefitItemResponses.add(mapToOrderBenefitItemResponse(presentMenu));
        }

        return orderBenefitItemResponses;
    }

    private List<OrderBenefitItemResponse> mapToOrderBenefitItemResponses(List<DiscountResult> discountResults) {
        return discountResults.stream()
                .map(this::mapToOrderBenefitItemResponse)
                .toList();
    }

    private OrderBenefitItemResponse mapToOrderBenefitItemResponse(DiscountResult discountResult) {
        return new OrderBenefitItemResponse(discountResult.policyName(), discountResult.amount());
    }

    private OrderBenefitItemResponse mapToOrderBenefitItemResponse(Menu menu) {
        return new OrderBenefitItemResponse(PRESENT_EVENT_NAME, menu.calculatePriceWith(-PRESENT_MENU_COUNT));
    }

    public Menu queryPresentMenu(Order order) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return order.present(menuRepository.findByName(PRESENT_MENU_NAME));
    }

    public Money queryTotalPrice(Order order) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return order.calculateTotalPrice();
    }

    public Money queryDiscountedTotalPrice(Order order, Date orderDate) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return order.calculateDiscountedTotalPrice(orderDate);
    }

}
