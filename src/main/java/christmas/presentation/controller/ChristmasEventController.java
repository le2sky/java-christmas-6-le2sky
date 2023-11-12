package christmas.presentation.controller;

import static christmas.presentation.exception.ExceptionHandler.handle;

import christmas.application.OrderService;
import christmas.domain.order.Order;
import christmas.presentation.mapper.ObjectMapper;
import christmas.presentation.view.InputView;

public class ChristmasEventController {

    private static final String INVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다.";

    private final OrderService orderService;

    public ChristmasEventController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void run() {
        Order order = handle(this::createOrder, INVALID_ORDER_MESSAGE);
    }

    private Order createOrder() {
        String input = InputView.readOrderMenu();

        return orderService.order(ObjectMapper.mapToOrderRequest(input));
    }
}
