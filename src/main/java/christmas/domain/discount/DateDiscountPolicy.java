package christmas.domain.discount;

import static christmas.global.util.ObjectUtil.requireNonNull;

import christmas.domain.common.Date;

public abstract class DateDiscountPolicy {

    private static final String UNKNOWN_DATE_MESSAGE = "알 수 없는 날짜입니다.";
    private static final String CANT_APPLY_DISCOUNT_MESSAGE = "할인을 적용할 수 없습니다.";

    public DiscountResult discount(Date date) {
        requireNonNull(date, UNKNOWN_DATE_MESSAGE);
        if (!isDiscountable(date)) {
            throw new IllegalStateException(CANT_APPLY_DISCOUNT_MESSAGE);
        }

        return bulidDiscountResult(date);
    }

    public boolean isDiscountable(Date date) {
        requireNonNull(date, UNKNOWN_DATE_MESSAGE);

        return isSatisfied(date);
    }

    protected abstract boolean isSatisfied(Date date);

    protected abstract DiscountResult bulidDiscountResult(Date date);
}
