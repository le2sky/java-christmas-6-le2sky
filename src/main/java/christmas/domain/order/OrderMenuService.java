package christmas.domain.order;

import static christmas.util.ObjectUtil.requireNonNull;

import christmas.domain.menu.Menus;
import java.util.List;

class OrderMenuService {

    private static final String UNKNOWN_MENUS_MESSAGE = "알 수 없는 메뉴 목록입니다.";

    private final Menus menus;

    private OrderMenuService(Menus menus) {
        this.menus = menus;
    }

    public static OrderMenuService from(Menus menus) {
        requireNonNull(menus, UNKNOWN_MENUS_MESSAGE);

        return new OrderMenuService(menus);
    }

    public Order order(List<OrderLineItem> lineItems) {
        OrderRule.checkSatisfiedLineItem(lineItems);
        OrderRule.checkIncludedLineItem(lineItems, menus);

        return Order.from(lineItems);
    }
}
