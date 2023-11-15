package christmas.presentation.controller;

import static christmas.presentation.exception.ExceptionHandler.handle;

import christmas.application.OrderQueryService;
import christmas.application.OrderService;
import christmas.domain.common.Date;
import christmas.domain.order.Order;
import christmas.presentation.mapper.ObjectMapper;
import christmas.presentation.view.InputView;
import christmas.presentation.view.OutputView;
import java.util.function.Supplier;

public class ChristmasEventController {

    private static final String INVALID_DATE_MESSAGE = "유효하지 않은 날짜입니다.";
    private static final String INVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다.";

    private final OrderService orderService;
    private final OrderQueryService orderQueryService;

    public ChristmasEventController(OrderService orderService, OrderQueryService orderQueryService) {
        this.orderService = orderService;
        this.orderQueryService = orderQueryService;
    }

    public void run() {
        Date orderDate = handle(createDate(), INVALID_DATE_MESSAGE);
        Order order = handle(createOrder(), INVALID_ORDER_MESSAGE);
        printOrderResult(order, orderDate);
    }

    private Supplier<Date> createDate() {
        return () -> (ObjectMapper.mapToDate(InputView.readOrderDate(Date.BASE_MONTH)));
    }

    private Supplier<Order> createOrder() {
        return () -> (orderService.order(ObjectMapper.mapToOrderRequest(InputView.readOrderMenu())));
    }

    private void printOrderResult(Order order, Date orderDate) {
        OutputView.printResultHeader(Date.BASE_MONTH, orderDate.getDayOfMonth());
        OutputView.printOrderItem(orderQueryService.queryOrderResult(order));
        OutputView.printPriceBeforeApplyDiscount(orderQueryService.queryTotalPrice(order));
        OutputView.printPresentedMenu(orderQueryService.queryPresentMenu(order), OrderQueryService.PRESENT_MENU_COUNT);
        OutputView.printOrderBenefits(orderQueryService.queryOrderBenefits(order, orderDate));
        OutputView.printPriceAfterApplyDiscount(orderQueryService.queryDiscountedTotalPrice(order, orderDate));
        OutputView.printPresentedBadge(Date.BASE_MONTH, orderQueryService.queryPresentBadge(order, orderDate));
    }
}
