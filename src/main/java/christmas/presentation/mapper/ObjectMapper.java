package christmas.presentation.mapper;

import static christmas.global.util.ObjectUtil.requireNonNull;

import christmas.application.request.OrderLineItemRequest;
import christmas.application.request.OrderRequest;
import christmas.presentation.mapper.constants.OrderRequestMappingRule;
import java.util.Arrays;
import java.util.List;

public class ObjectMapper {

    private static final String UNKNOWN_INPUT_MESSAGE = "유효한 문자열을 입력해주세요.";

    private ObjectMapper() {
    }

    public static OrderRequest mapToOrderRequest(String input)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        requireNonNull(input, UNKNOWN_INPUT_MESSAGE);

        return new OrderRequest(mapToOrderLineItemRequest(input.split(OrderRequestMappingRule.SPLIT_DELIMITER)));
    }

    private static List<OrderLineItemRequest> mapToOrderLineItemRequest(String[] items) {
        return Arrays.stream(items)
                .map(ObjectMapper::generateOrderLineItemRequest)
                .toList();
    }

    private static OrderLineItemRequest generateOrderLineItemRequest(String item) {
        String[] splitItem = item.split(OrderRequestMappingRule.VALUE_DELIMITER);

        return new OrderLineItemRequest(splitItem[0], mapToInt(splitItem[1]));
    }

    public static int mapToInt(String input) throws IllegalArgumentException {
        requireNonNull(input, UNKNOWN_INPUT_MESSAGE);

        return Integer.parseInt(input);
    }
}
