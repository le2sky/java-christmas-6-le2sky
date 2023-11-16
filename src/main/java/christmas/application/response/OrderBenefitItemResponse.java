package christmas.application.response;

import christmas.domain.common.Money;

public record OrderBenefitItemResponse(String benefitName, Money benefitTotalAmount) {
}
