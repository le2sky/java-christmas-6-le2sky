package christmas.domain.order;

import java.util.List;

class Order {

    private final List<OrderLineItem> lineItems;

    private Order(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public static Order from(List<OrderLineItem> lineItems) {
        OrderRule.checkSatisfiedLineItem(lineItems);

        return new Order(lineItems);
    }
}
