package christmas.domain.order;

import christmas.domain.common.Money;
import java.util.Arrays;
import java.util.function.Predicate;

public enum OrderEventBadge {

    SANTA("산타", (amount) -> amount >= 20_000),
    TREE("트리", (amount) -> amount >= 10_000),
    STAR("별", (amount) -> amount >= 5_000),
    NONE("없음", (amount) -> true);

    private final String name;
    private final Predicate<Integer> condition;

    OrderEventBadge(String name, Predicate<Integer> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static OrderEventBadge from(Money money) {
        return Arrays.stream(values())
                .filter(orderEventBadge -> orderEventBadge.condition.test((int) Math.abs(money.getAmount())))
                .findFirst()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
