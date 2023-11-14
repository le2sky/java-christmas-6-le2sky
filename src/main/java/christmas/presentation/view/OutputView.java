package christmas.presentation.view;

import christmas.application.response.OrderLineItemResponse;
import christmas.application.response.OrderResponse;
import christmas.domain.common.Money;
import christmas.domain.menu.Menu;
import christmas.global.util.CurrencyFormatter;

public class OutputView {

    private static final String EMPTY_MESSAGE = "없음";
    private static final String RESULT_HEADER_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n";
    private static final String ORDER_RESULT_HEADER = "<주문 메뉴>";
    private static final String ORDER_ITEM_FORMAT = "%s %d개%n";
    private static final String ORDER_PRICE_BEFORE_DISCOUNT_HEADER = "<할인 전 총주문 금액>";
    private static final String PRICE_FORMAT = "%s%n";
    private static final String PRESENT_MENU_HEADER = "<증정 메뉴>";
    private static final String PRESENT_MENU_FORMAT = "%s 1개";

    private OutputView() {
    }

    public static void printResultHeader(int month, int dayOfMonth) {
        System.out.format(RESULT_HEADER_FORMAT, month, dayOfMonth);
        System.out.println();
    }

    public static void printOrderItem(OrderResponse orderResponse) {
        System.out.println(ORDER_RESULT_HEADER);
        orderResponse.orderLineItemResponses().forEach(OutputView::printEachOrderItem);
        System.out.println();
    }

    private static void printEachOrderItem(OrderLineItemResponse orderLineItemResponse) {
        System.out.format(ORDER_ITEM_FORMAT, orderLineItemResponse.name(), orderLineItemResponse.quantity());
    }

    public static void printPriceBeforeApplyDiscount(Money money) {
        System.out.println(ORDER_PRICE_BEFORE_DISCOUNT_HEADER);
        System.out.format(PRICE_FORMAT, CurrencyFormatter.format(money.getAmount()));
        System.out.println();
    }

    public static void printPresentedMenu(Menu menu) {
        System.out.println(PRESENT_MENU_HEADER);
        System.out.println(buildPresentedMenuMessage(menu.getName()));
        System.out.println();
    }

    private static String buildPresentedMenuMessage(String menuName) {
        if (menuName.isEmpty()) {
            return EMPTY_MESSAGE;
        }

        return String.format(PRESENT_MENU_FORMAT, menuName);
    }
}
