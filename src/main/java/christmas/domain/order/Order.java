package christmas.domain.order;

import christmas.domain.common.Money;
import christmas.domain.menu.Menu;
import christmas.domain.menu.specific.EmptyMenu;
import java.util.List;

public class Order {

    private static final int TOTAL_PRICE_STANDARD_FOR_PRESENT = 120_000;

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

    public Menu present(Menu presentMenu) {
        Money totalPrice = calculateTotalPrice();
        if (totalPrice.isGreaterThanEqual(TOTAL_PRICE_STANDARD_FOR_PRESENT)) {
            return presentMenu;
        }

        return EmptyMenu.newInstance();
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }
}
