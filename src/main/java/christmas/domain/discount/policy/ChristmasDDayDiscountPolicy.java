package christmas.domain.discount.policy;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DateDiscountPolicy;
import christmas.domain.discount.DiscountResult;

public class ChristmasDDayDiscountPolicy extends DateDiscountPolicy {

    private static final Date DISCOUNTABLE_STANDARD_DATE = Date.from(25);
    private static final Money BASE_DISCOUNT_AMOUNT = Money.from(-1_000);
    private static final Money ADD_AMOUNT = Money.from(-100);
    private static final String POLICY_NAME = "크리스마스 디데이 할인";

    @Override
    protected boolean isSatisfied(Date date) {
        return !date.isLaterThan(DISCOUNTABLE_STANDARD_DATE);
    }

    @Override
    protected DiscountResult bulidDiscountResult(Date date) {
        Money discountAmount = calculateDiscountAmount(date);

        return new DiscountResult(POLICY_NAME, discountAmount);
    }

    private Money calculateDiscountAmount(Date date) {
        Money discountAmount = BASE_DISCOUNT_AMOUNT;
        for (int day = 1; day < date.getDayOfMonth(); day++) {
            discountAmount = discountAmount.add(ADD_AMOUNT);
        }

        return discountAmount;
    }
}
