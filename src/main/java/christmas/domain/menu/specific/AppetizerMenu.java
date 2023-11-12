package christmas.domain.menu.specific;

import christmas.domain.common.Money;
import christmas.domain.menu.AbstractMenu;
import christmas.domain.menu.MenuName;

public class AppetizerMenu extends AbstractMenu {

    private AppetizerMenu(MenuName name, Money price) {
        super(name, price);
    }

    public static AppetizerMenu of(String name, int price) {
        return new AppetizerMenu(MenuName.from(name), Money.from(price));
    }

    @Override
    public Boolean isBeverage() {
        return false;
    }
}
