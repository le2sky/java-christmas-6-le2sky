package christmas.domain.order;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DateDiscountPolicy;
import christmas.domain.discount.DiscountResult;
import christmas.domain.menu.Menu;
import christmas.domain.menu.specific.EmptyMenu;
import java.util.Collections;
import java.util.List;

public class Order {

    private static final int TOTAL_PRICE_STANDARD_FOR_PRESENT = 120_000;
    private static final int TOTAL_PRICE_STANDARD_FOR_APPLY_DISCOUNT = 10_000;

    private final List<OrderLineItem> lineItems;
    private final List<DateDiscountPolicy> policies;

    private Order(List<OrderLineItem> lineItems, List<DateDiscountPolicy> policies) {
        this.lineItems = lineItems;
        this.policies = policies;
    }

    static Order of(List<OrderLineItem> lineItems, List<DateDiscountPolicy> policies) {
        return new Order(lineItems, policies);
    }

    public Money calculateTotalPrice() {
        return lineItems.stream()
                .map(OrderLineItem::calculateEachPrice)
                .reduce(Money::add)
                .orElseGet(Money::zero);
    }

    public Menu present(Menu presentMenu) {
        Money totalPrice = calculateTotalPrice();
        if (totalPrice.isGreaterThanEqual(TOTAL_PRICE_STANDARD_FOR_PRESENT)) {
            return presentMenu;
        }

        return EmptyMenu.newInstance();
    }

    public List<DiscountResult> calculateDiscountBenefit(Date orderDate) {
        Money totalPrice = calculateTotalPrice();
        if (totalPrice.isGreaterThanEqual(TOTAL_PRICE_STANDARD_FOR_APPLY_DISCOUNT)) {
            return applyDiscountPolicies(orderDate);
        }

        return Collections.emptyList();
    }

    private List<DiscountResult> applyDiscountPolicies(Date orderDate) {
        return policies.stream()
                .filter(dateDiscountPolicy -> dateDiscountPolicy.isDiscountable(orderDate))
                .map(dateDiscountPolicy -> dateDiscountPolicy.discount(orderDate))
                .toList();
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }
}
