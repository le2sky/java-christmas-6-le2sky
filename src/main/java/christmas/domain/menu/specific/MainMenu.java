package christmas.domain.menu.specific;

import christmas.domain.common.Money;
import christmas.domain.discount.policy.WeekendDiscountPolicy;
import christmas.domain.menu.AbstractMenu;
import christmas.domain.menu.MenuName;

public class MainMenu extends AbstractMenu {

    private MainMenu(MenuName name, Money price) {
        super(name, price, new WeekendDiscountPolicy());
    }

    public static MainMenu of(String name, int price) {
        return new MainMenu(MenuName.from(name), Money.from(price));
    }

    @Override
    public boolean isBeverage() {
        return false;
    }
}
