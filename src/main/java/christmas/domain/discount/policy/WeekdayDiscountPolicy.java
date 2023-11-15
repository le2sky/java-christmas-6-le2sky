package christmas.domain.discount.policy;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DateDiscountPolicy;
import christmas.domain.discount.DiscountResult;

public class WeekdayDiscountPolicy extends DateDiscountPolicy {

    private static final Money DISCOUNT_AMOUNT = Money.from(-2_023);
    private static final String POLICY_NAME = "평일 할인";

    @Override
    protected boolean isSatisfied(Date date) {
        return date.isWeekDay();
    }

    @Override
    protected DiscountResult bulidDiscountResult(Date date) {
        return new DiscountResult(POLICY_NAME, DISCOUNT_AMOUNT);
    }
}
