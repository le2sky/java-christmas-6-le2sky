package christmas.application.request;

import java.util.List;

public record OrderRequest(List<OrderLineItemRequest> orderLineItemRequests) {
}