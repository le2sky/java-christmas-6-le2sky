package christmas.domain.order;

import static christmas.util.ObjectUtil.requireIncludeNonNull;
import static christmas.util.ObjectUtil.requireNonNull;

import java.util.List;

class Order {

    private static final int MAX_ORDER_QUANTITY = 20;
    private static final String UNKNOWN_LINE_ITEMS_MESSAGE = "알 수 없는 주문 항목 목록입니다.";
    private static final String UNKNOWN_LINE_ITEM_MESSAGE = "알 수 없는 주문 항목이 포함되어 있습니다.";
    private static final String DUPLICATED_LINE_ITEM_MESSAGE = "중복된 주문 항목이 존재합니다.";
    private static final String EXCEED_ORDER_QUANTITY_MESSAGE = "최대 주문 수량은 " + MAX_ORDER_QUANTITY + "개입니다.";
    private static final String EMPTY_LINE_ITEM_MESSAGE = "적어도 1개 이상 주문해야합니다.";

    private final List<OrderLineItem> lineItems;

    private Order(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public static Order from(List<OrderLineItem> lineItems) {
        validate(lineItems);

        return new Order(lineItems);
    }

    private static void validate(List<OrderLineItem> lineItems) {
        requireNonNull(lineItems, UNKNOWN_LINE_ITEMS_MESSAGE);
        requireIncludeNonNull(lineItems, UNKNOWN_LINE_ITEM_MESSAGE);
        checkDuplicatedItem(lineItems);
        checkTotalQuantity(lineItems);
    }

    private static void checkDuplicatedItem(List<OrderLineItem> lineItems) {
        if (calculateUniqueCount(lineItems) != lineItems.size()) {
            throw new IllegalArgumentException(DUPLICATED_LINE_ITEM_MESSAGE);
        }
    }

    private static int calculateUniqueCount(List<OrderLineItem> lineItems) {
        return (int) lineItems.stream()
                .distinct()
                .count();
    }

    private static void checkTotalQuantity(List<OrderLineItem> lineItems) {
        if (calculateTotalQuantity(lineItems) >= MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(EXCEED_ORDER_QUANTITY_MESSAGE);
        }
    }

    private static int calculateTotalQuantity(List<OrderLineItem> lineItems) {
        return lineItems.stream()
                .map(OrderLineItem::getQuantity)
                .reduce(Integer::sum)
                .orElseThrow(() -> new IllegalArgumentException(EMPTY_LINE_ITEM_MESSAGE));
    }
}
