package christmas.domain.order;

import christmas.domain.common.Money;
import java.util.List;

public class Order {

    private final List<OrderLineItem> lineItems;

    private Order(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    static Order from(List<OrderLineItem> lineItems) {
        return new Order(lineItems);
    }

    public Money calculateTotalPrice() {
        return lineItems.stream()
                .map(OrderLineItem::calculateEachPrice)
                .reduce(Money::add)
                .orElseGet(Money::zero);
    }
}
