package christmas.domain.order;

import static christmas.global.util.ObjectUtil.requireNonNull;

import christmas.domain.discount.DateDiscountPolicy;
import christmas.domain.discount.policy.ChristmasDDayDiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import christmas.domain.menu.Menus;
import java.util.List;

public class OrderMenuService {

    private static final String UNKNOWN_MENUS_MESSAGE = "알 수 없는 메뉴 목록입니다.";

    private final Menus menus;
    private final List<DateDiscountPolicy> dateDiscountPolicies = List.of(
            new ChristmasDDayDiscountPolicy(),
            new SpecialDiscountPolicy()
    );

    private OrderMenuService(Menus menus) {
        this.menus = menus;
    }

    public static OrderMenuService from(Menus menus) {
        requireNonNull(menus, UNKNOWN_MENUS_MESSAGE);

        return new OrderMenuService(menus);
    }

    public Order order(List<OrderLineItem> lineItems) {
        OrderRule.validateLineItem(lineItems);
        OrderRule.validateExistLineItem(lineItems, menus);
        OrderRule.validateOnlyBeverage(lineItems);

        return Order.of(lineItems, dateDiscountPolicies);
    }
}
