package christmas.presentation.view;

import christmas.application.response.OrderLineItemResponse;
import christmas.application.response.OrderResponse;

public class OutputView {

    private static final String RESULT_HEADER = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_RESULT_HEADER = "<주문 메뉴>";
    private static final String ORDER_ITEM_FORMAT = "%s %d개%n";

    private OutputView() {
    }

    public static void printResultHeader() {
        System.out.println(RESULT_HEADER);
        System.out.println();
    }

    public static void printOrderResult(OrderResponse orderResponse) {
        System.out.println(ORDER_RESULT_HEADER);
        orderResponse.orderLineItemResponses().forEach(OutputView::printOrderItem);
    }

    private static void printOrderItem(OrderLineItemResponse orderLineItemResponse) {
        System.out.format(ORDER_ITEM_FORMAT, orderLineItemResponse.name(), orderLineItemResponse.quantity());
    }
}
