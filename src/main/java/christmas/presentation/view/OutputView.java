package christmas.presentation.view;

import christmas.application.response.OrderLineItemResponse;
import christmas.application.response.OrderResponse;
import christmas.domain.common.Money;
import christmas.global.util.CurrencyFormatter;

public class OutputView {

    private static final String RESULT_HEADER = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_RESULT_HEADER = "<주문 메뉴>";
    private static final String ORDER_ITEM_FORMAT = "%s %d개%n";
    private static final String ORDER_PRICE_BEFORE_DISCOUNT_HEADER = "<할인 전 총주문 금액>";
    private static final String PRICE_FORMAT = "%s%n";

    private OutputView() {
    }

    public static void printResultHeader() {
        System.out.println(RESULT_HEADER);
        System.out.println();
    }

    public static void printOrderResult(OrderResponse orderResponse) {
        System.out.println(ORDER_RESULT_HEADER);
        orderResponse.orderLineItemResponses().forEach(OutputView::printOrderItem);
        System.out.println();
    }

    private static void printOrderItem(OrderLineItemResponse orderLineItemResponse) {
        System.out.format(ORDER_ITEM_FORMAT, orderLineItemResponse.name(), orderLineItemResponse.quantity());
    }

    public static void printPriceBeforeApplyDiscount(Money money) {
        System.out.println(ORDER_PRICE_BEFORE_DISCOUNT_HEADER);
        System.out.format(PRICE_FORMAT, CurrencyFormatter.format(money.getAmount()));
        System.out.println();
    }
}
