package christmas.presentation.controller;

import static christmas.presentation.exception.ExceptionHandler.handle;

import christmas.application.OrderQueryService;
import christmas.application.OrderService;
import christmas.domain.order.Order;
import christmas.presentation.mapper.ObjectMapper;
import christmas.presentation.view.InputView;
import christmas.presentation.view.OutputView;

public class ChristmasEventConsoleController {

    private static final String INVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다.";

    private final OrderService orderService;
    private final OrderQueryService orderQueryService;

    public ChristmasEventConsoleController(OrderService orderService, OrderQueryService orderQueryService) {
        this.orderService = orderService;
        this.orderQueryService = orderQueryService;
    }

    public void run() {
        Order order = handle(this::createOrder, INVALID_ORDER_MESSAGE);

        printOrderResult(order);
    }

    private Order createOrder() {
        String input = InputView.readOrderMenu();

        return orderService.order(ObjectMapper.mapToOrderRequest(input));
    }

    private void printOrderResult(Order order) {
        OutputView.printResultHeader();
        OutputView.printOrderResult(orderQueryService.queryOrderResult(order));
        OutputView.printPriceBeforeApplyDiscount(order.calculateTotalPrice());
    }
}
