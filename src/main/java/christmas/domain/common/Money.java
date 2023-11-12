package christmas.domain.common;

import static christmas.global.util.ObjectUtil.requireNonNull;

import java.util.Objects;

public class Money {

    private static final int ZERO_MONEY_AMOUNT = 0;
    private static final String UNKNOWN_MONEY_MESSAGE = "알 수 없는 돈과 해당 연산을 수행할 수 없습니다.";
    public static final String CURRENCY = "원";

    private final long amount;

    private Money(long amount) {
        this.amount = amount;
    }

    public static Money from(long amount) {
        return new Money(amount);
    }

    public static Money zero() {
        return new Money(ZERO_MONEY_AMOUNT);
    }

    public Money add(Money target) {
        requireNonNull(target, UNKNOWN_MONEY_MESSAGE);

        return new Money(this.amount + target.amount);
    }

    public Money subtract(Money target) {
        requireNonNull(target, UNKNOWN_MONEY_MESSAGE);

        return new Money(this.amount - target.amount);
    }

    public Money multiply(int multiplier) {
        return new Money(this.amount * multiplier);
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
