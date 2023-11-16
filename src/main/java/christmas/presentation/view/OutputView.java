package christmas.presentation.view;

import christmas.application.response.OrderBenefitItemResponse;
import christmas.application.response.OrderBenefitResponse;
import christmas.application.response.OrderLineItemResponse;
import christmas.application.response.OrderResponse;
import christmas.domain.common.Money;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderEventBadge;
import christmas.global.util.CurrencyFormatter;
import christmas.presentation.mapper.constants.OrderRequestMappingRule;
import java.util.stream.Collectors;

public class OutputView {

    private static final String READ_ORDER_DATE_FORMAT =
            "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.%n%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)%n";
    private static final String READ_ORDER_MENU_FORMAT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. %s)%n";
    private static final String EMPTY_MESSAGE = "없음";
    private static final String RESULT_HEADER_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n";
    private static final String ORDER_RESULT_HEADER = "<주문 메뉴>";
    private static final String ORDER_ITEM_FORMAT = "%s %d개%n";
    private static final String ORDER_PRICE_BEFORE_DISCOUNT_HEADER = "<할인 전 총주문 금액>";
    private static final String PRICE_FORMAT = "%s%n";
    private static final String PRESENT_MENU_HEADER = "<증정 메뉴>";
    private static final String PRESENT_MENU_FORMAT = "%s %d개";
    private static final String ORDER_BENEFIT_HEADER = "<혜택 내역>";
    private static final String ORDER_BENEFIT_FORMAT = "%s: %s";
    private static final String ORDER_BENEFIT_PRICE_HEADER = "<총혜택 금액>";
    private static final String ORDER_PRICE_AFTER_DISCOUNT_HEADER = "<할인 후 예상 결제 금액>";
    private static final String PRESENT_BADGE_HEADER_FORMAT = "<%d월 이벤트 배지>%n";

    private OutputView() {
    }

    public static void printReadDateMessage(int baseMonth) {
        System.out.format(READ_ORDER_DATE_FORMAT, baseMonth, baseMonth);
    }

    public static void printReadOrderMessage() {
        System.out.format(READ_ORDER_MENU_FORMAT, OrderRequestMappingRule.createSample());
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

    public static void printPresentedMenu(Menu menu, int presentMenuCount) {
        System.out.println(PRESENT_MENU_HEADER);
        System.out.println(buildPresentedMenuMessage(menu.getName(), presentMenuCount));
        System.out.println();
    }

    private static String buildPresentedMenuMessage(String menuName, int presentMenuCount) {
        if (menuName.isEmpty()) {
            return EMPTY_MESSAGE;
        }

        return String.format(PRESENT_MENU_FORMAT, menuName, presentMenuCount);
    }

    public static void printOrderBenefits(OrderBenefitResponse orderBenefitResponse) {
        printBenefitList(orderBenefitResponse);
        printBenefitTotalAmount(orderBenefitResponse);
    }

    private static void printBenefitList(OrderBenefitResponse orderBenefitResponse) {
        System.out.println(ORDER_BENEFIT_HEADER);
        System.out.println(buildOrderBenefitMessage(orderBenefitResponse));
        System.out.println();
    }

    private static String buildOrderBenefitMessage(OrderBenefitResponse orderBenefitResponse) {
        if (orderBenefitResponse.isEmpty()) {
            return EMPTY_MESSAGE;
        }

        return orderBenefitResponse.orderBenefitItemResponses().stream()
                .map(OutputView::buildOrderBenefitItemMessage)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static String buildOrderBenefitItemMessage(OrderBenefitItemResponse orderBenefitItemResponse) {
        return String.format(ORDER_BENEFIT_FORMAT,
                orderBenefitItemResponse.benefitName(),
                CurrencyFormatter.format(orderBenefitItemResponse.benefitTotalAmount().getAmount()));
    }

    private static void printBenefitTotalAmount(OrderBenefitResponse orderBenefitResponse) {
        System.out.println(ORDER_BENEFIT_PRICE_HEADER);
        System.out.println(CurrencyFormatter.format(orderBenefitResponse.getTotalBenefitAmount().getAmount()));
        System.out.println();
    }

    public static void printPriceAfterApplyDiscount(Money money) {
        System.out.println(ORDER_PRICE_AFTER_DISCOUNT_HEADER);
        System.out.format(PRICE_FORMAT, CurrencyFormatter.format(money.getAmount()));
        System.out.println();
    }

    public static void printPresentedBadge(int month, OrderEventBadge orderEventBadge) {
        System.out.format(PRESENT_BADGE_HEADER_FORMAT, month);
        System.out.println(orderEventBadge.getName());
    }
}
