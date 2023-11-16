package christmas.domain.order;

import static christmas.global.util.NumberUtil.requirePositiveNumber;
import static christmas.global.util.ObjectUtil.requireNonNull;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DiscountResult;
import christmas.domain.menu.Menu;
import java.util.Objects;

public class OrderLineItem {

    private static final String UNKNOWN_MENU_MESSAGE = "알 수 없는 메뉴입니다.";
    private static final String INVALID_QUANTITY_MESSAGE = "각 주문 항목의 수량은 적어도 1개 이상이어야 합니다.";

    private final Menu menu;
    private final int quantity;

    private OrderLineItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderLineItem of(Menu menu, int quantity) {
        requireNonNull(menu, UNKNOWN_MENU_MESSAGE);
        requirePositiveNumber(quantity, INVALID_QUANTITY_MESSAGE);

        return new OrderLineItem(menu, quantity);
    }

    public Money calculateEachPrice() {
        return menu.calculatePriceWith(quantity);
    }

    public DiscountResult calculateEachDiscountBenefit(Date orderDate) {
        return menu.calculateDiscountBenefitWith(orderDate, quantity);
    }

    public boolean isDiscountable(Date orderDate) {
        return menu.isDiscountable(orderDate);
    }

    public Menu getMenu() {
        return menu;
    }

    public String getName() {
        return menu.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineItem that = (OrderLineItem) o;
        return Objects.equals(menu, that.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
