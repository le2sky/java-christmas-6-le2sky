package christmas.application.response;

import christmas.domain.common.Money;
import java.util.List;

public record OrderBenefitResponse(List<OrderBenefitItemResponse> orderBenefitItemResponses) {

    public boolean isEmpty() {
        return orderBenefitItemResponses().isEmpty();
    }

    public Money getTotalBenefitAmount() {
        return orderBenefitItemResponses.stream()
                .map(OrderBenefitItemResponse::benefitTotalAmount)
                .reduce(Money.zero(), Money::add);
    }
}
