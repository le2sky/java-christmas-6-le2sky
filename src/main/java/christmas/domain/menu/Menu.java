package christmas.domain.menu;

import christmas.domain.common.Money;

public interface Menu {

    Money calculatePriceWith(int quantity);

    String getName();

    Boolean isBeverage();
}
