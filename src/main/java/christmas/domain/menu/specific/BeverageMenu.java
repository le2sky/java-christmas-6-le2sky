package christmas.domain.menu.specific;

import christmas.domain.common.Money;
import christmas.domain.menu.AbstractMenu;
import christmas.domain.menu.MenuName;

public class BeverageMenu extends AbstractMenu {

    private BeverageMenu(MenuName name, Money price) {
        super(name, price);
    }

    public static BeverageMenu of(String name, int price) {
        return new BeverageMenu(MenuName.from(name), Money.from(price));
    }

    @Override
    public Boolean isBeverage() {
        return true;
    }
}
