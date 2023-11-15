package christmas.domain.discount.policy;

import christmas.domain.common.Date;
import christmas.domain.discount.DateDiscountPolicy;
import christmas.domain.discount.DiscountResult;

public class NoneDiscountPolicy extends DateDiscountPolicy {

    @Override
    protected boolean isSatisfied(Date date) {
        return false;
    }

    @Override
    protected DiscountResult bulidDiscountResult(Date date) {
        return null;
    }
}
