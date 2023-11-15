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

        return mapToOrderResultResponseEntity(order.getLineItems());
    }

    private OrderResponse mapToOrderResultResponseEntity(List<OrderLineItem> lineItems) {
        return new OrderResponse(lineItems.stream()
                .map(this::mapToOrderLineItemResponse)
                .toList());
    }

    public OrderLineItemResponse mapToOrderLineItemResponse(OrderLineItem orderLineItem) {
        return new OrderLineItemResponse(orderLineItem.getName(), orderLineItem.getQuantity());
    }

    public OrderBenefitResponse queryOrderBenefits(Order order, Date orderDate) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return mapToBenefitResponseEntity(order.calculateDiscountBenefit(orderDate));
    }

    private OrderBenefitResponse mapToBenefitResponseEntity(List<DiscountResult> discountResults) {
        return new OrderBenefitResponse(discountResults.stream()
                .map(this::mapToOrderBenefitItemResponse)
                .toList());
    }

    private OrderBenefitItemResponse mapToOrderBenefitItemResponse(DiscountResult discountResult) {
        return new OrderBenefitItemResponse(discountResult.policyName(), discountResult.amount());
    }

    public Money queryTotalPrice(Order order) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return order.calculateTotalPrice();
    }

    public Menu queryPresentMenu(Order order) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);

        return order.present(menuRepository.findByName(PRESENT_MENU_NAME));
    }
}
