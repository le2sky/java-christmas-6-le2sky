package christmas.presentation.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.presentation.mapper.constants.OrderRequestMappingRule;

public class InputView {

    private static final String READ_ORDER_MENU_FORMAT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. %s)%n";

    private InputView() {
    }

    public static String readOrderMenu() {
        System.out.format(READ_ORDER_MENU_FORMAT, OrderRequestMappingRule.createSample());

        return Console.readLine().trim();
    }
}
