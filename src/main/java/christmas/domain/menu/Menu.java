package christmas.domain.menu;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DiscountResult;

public interface Menu {

    Money calculatePriceWith(int quantity);

    DiscountResult calculateDiscountBenefitWith(Date date, int quantity);

    boolean isDiscountable(Date date);

    boolean isBeverage();

    String getName();
}
