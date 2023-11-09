package christmas.domain.common;

import java.util.Objects;

public class Money {

    private static final String UNKNOWN_MONEY_MESSAGE = "알 수 없는 돈과 해당 연산을 수행할 수 없습니다.";

    private final long amount;

    private Money(long amount) {
        this.amount = amount;
    }

    public static Money from(long amount) {
        return new Money(amount);
    }

    public Money add(Money target) {
        checkMoneyNonNull(target);

        return new Money(this.amount + target.amount);
    }

    public Money subtract(Money target) {
        checkMoneyNonNull(target);

        return new Money(this.amount - target.amount);
    }

    private void checkMoneyNonNull(Money target) {
        if (Objects.isNull(target)) {
            throw new IllegalArgumentException(UNKNOWN_MONEY_MESSAGE);
        }
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