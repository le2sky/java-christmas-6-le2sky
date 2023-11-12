package christmas.application.response;

import java.util.List;

public record OrderResponse(List<OrderLineItemResponse> orderLineItemResponses) {
}
