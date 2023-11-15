package christmas.domain.discount.policy;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DateDiscountPolicy;
import christmas.domain.discount.DiscountResult;
import java.util.List;

public class SpecialDiscountPolicy extends DateDiscountPolicy {

    private static final List<Date> specialDays;
    private static final Money DISCOUNT_AMOUNT = Money.from(-1_000);
    private static final String POLICY_NAME = "특별 할인";

    static {
        specialDays = List.of(
                Date.from(3),
                Date.from(10),
                Date.from(17),
                Date.from(24),
                Date.from(25),
                Date.from(31)
        );
    }

    @Override
    protected boolean isSatisfied(Date date) {
        return specialDays.contains(date);
    }

    @Override
    protected DiscountResult bulidDiscountResult(Date date) {
        return new DiscountResult(POLICY_NAME, DISCOUNT_AMOUNT);
    }
}
