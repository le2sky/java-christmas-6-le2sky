package christmas.domain.menu.specific;

import christmas.domain.common.Money;
import christmas.domain.discount.policy.WeekdayDiscountPolicy;
import christmas.domain.menu.AbstractMenu;
import christmas.domain.menu.MenuName;

public class DessertMenu extends AbstractMenu {

    private DessertMenu(MenuName name, Money price) {
        super(name, price, new WeekdayDiscountPolicy());
    }

    public static DessertMenu of(String name, int price) {
        return new DessertMenu(MenuName.from(name), Money.from(price));
    }

    @Override
    public boolean isBeverage() {
        return false;
    }
}
