package christmas.domain.event;

import static christmas.global.util.ObjectUtil.requireNonNull;

import christmas.domain.common.Date;
import christmas.domain.order.Order;

public class Event {

    private static final String UNKNOWN_ORDER_MESSAGE = "알 수 없는 주문입니다.";
    private static final String UNKNOWN_ORDER_DATE_MESSAGE = "알 수 없는 주문 일자입니다.";

    private final Order order;
    private final Date orderDate;

    private Event(Order order, Date orderDate) {
        this.order = order;
        this.orderDate = orderDate;
    }

    public static Event of(Order order, Date orderDate) {
        requireNonNull(order, UNKNOWN_ORDER_MESSAGE);
        requireNonNull(orderDate, UNKNOWN_ORDER_DATE_MESSAGE);

        return new Event(order, orderDate);
    }
}
