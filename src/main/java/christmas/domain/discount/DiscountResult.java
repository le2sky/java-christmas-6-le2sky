package christmas.domain.discount;

import christmas.domain.common.Money;

public record DiscountResult(String policyName, Money amount) {
}
