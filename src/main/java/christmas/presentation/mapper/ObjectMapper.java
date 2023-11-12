package christmas.presentation.mapper;

import christmas.application.request.OrderLineItemRequest;
import christmas.application.request.OrderRequest;
import christmas.presentation.mapper.constants.OrderRequestMappingRule;
import java.util.Arrays;
import java.util.List;

public class ObjectMapper {

    private ObjectMapper() {
    }

    public static OrderRequest mapToOrderRequest(String input) throws NumberFormatException, IndexOutOfBoundsException {
        String[] items = input.split(OrderRequestMappingRule.SPLIT_DELIMITER);

        return new OrderRequest(mapToOrderLineItemRequest(items));
    }

    private static List<OrderLineItemRequest> mapToOrderLineItemRequest(String[] items) {
        return Arrays.stream(items)
                .map(ObjectMapper::generateOrderLineItemRequest)
                .toList();
    }

    private static OrderLineItemRequest generateOrderLineItemRequest(String item) {
        String[] splitItem = item.split(OrderRequestMappingRule.VALUE_DELIMITER);

        return new OrderLineItemRequest(splitItem[0], Integer.parseInt(splitItem[1]));
    }
}
