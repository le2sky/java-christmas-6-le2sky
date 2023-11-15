package christmas.presentation.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.presentation.mapper.constants.OrderRequestMappingRule;

public class InputView {

    private static final String READ_ORDER_DATE_FORMAT =
            "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.%n%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)%n";
    private static final String READ_ORDER_MENU_FORMAT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. %s)%n";

    private InputView() {
    }

    public static String readOrderMenu() {
        System.out.format(READ_ORDER_MENU_FORMAT, OrderRequestMappingRule.createSample());

        return Console.readLine();
    }

    public static String readOrderDate(int month) {
        System.out.format(READ_ORDER_DATE_FORMAT, month, month);

        return Console.readLine();
    }
}
