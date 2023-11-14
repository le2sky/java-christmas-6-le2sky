package christmas.presentation.controller;

import static christmas.presentation.exception.ExceptionHandler.handle;

import christmas.application.OrderQueryService;
import christmas.application.OrderService;
import christmas.domain.common.Date;
import christmas.domain.order.Order;
import christmas.presentation.mapper.ObjectMapper;
import christmas.presentation.view.InputView;
import christmas.presentation.view.OutputView;

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
        Date orderDate = handle(this::createDate, INVALID_DATE_MESSAGE);
        Order order = handle(this::createOrder, INVALID_ORDER_MESSAGE);

        printOrderResult(order, orderDate);
    }

    private Date createDate() {
        String input = InputView.readOrderDate(Date.BASE_MONTH);

        return ObjectMapper.mapToDate(input);
    }

    private Order createOrder() {
        String input = InputView.readOrderMenu();

        return orderService.order(ObjectMapper.mapToOrderRequest(input));
    }

    private void printOrderResult(Order order, Date orderDate) {
        OutputView.printResultHeader(Date.BASE_MONTH, orderDate.getDayOfMonth());
        OutputView.printOrderItem(orderQueryService.queryOrderResult(order));
        // TODO : orderQueryService.queryTotalPrice로 이동
        OutputView.printPriceBeforeApplyDiscount(order.calculateTotalPrice());
        // TODO : printOrderResult 시작부에 Event 객체를 생성하고, queryPresentMenu(event)로 결과 받아오도록 개선
        OutputView.printPresentedMenu(orderQueryService.queryPresentMenu(order, orderDate));
    }
}
