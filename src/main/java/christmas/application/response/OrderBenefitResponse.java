package christmas.application.response;

import java.util.List;

public record OrderBenefitResponse(List<OrderBenefitItemResponse> orderBenefitItemResponses) {

    public boolean isEmpty() {
        return orderBenefitItemResponses().isEmpty();
    }
}
