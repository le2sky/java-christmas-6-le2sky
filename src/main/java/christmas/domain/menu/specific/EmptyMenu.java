package christmas.domain.menu.specific;

import christmas.domain.common.Money;
import christmas.domain.discount.policy.NoneDiscountPolicy;
import christmas.domain.menu.AbstractMenu;
import christmas.domain.menu.MenuName;

public class EmptyMenu extends AbstractMenu {

    private static final String EMPTY_MENU_NAME = "";
    private static final int EMPTY_MENU_PRICE_AMOUNT = 0;

    private EmptyMenu(MenuName name, Money price) {
        super(name, price, new NoneDiscountPolicy());
    }

    public static EmptyMenu newInstance() {
        return new EmptyMenu(MenuName.from(EMPTY_MENU_NAME), Money.from(EMPTY_MENU_PRICE_AMOUNT));
    }

    @Override
    public boolean isBeverage() {
        return false;
    }
}
