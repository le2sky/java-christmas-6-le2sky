package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DiscountResult;
import christmas.domain.discount.policy.ChristmasDDayDiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import christmas.domain.menu.Menu;
import christmas.domain.menu.specific.BeverageMenu;
import christmas.domain.menu.specific.DessertMenu;
import christmas.domain.menu.specific.MainMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

class OrderTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Order order = Order.of(List.of(
                OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 2),
                OrderLineItem.of(MainMenu.of("불닭까르보나라", 1_000), 2)
        ), Collections.emptyList());
    }

    @DisplayName("할인 이전 총 주문 금액을 계산할 수 있다.")
    @Test
    void calculateTotalPrice() {
        Order order = Order.of(List.of(
                OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 2),
                OrderLineItem.of(MainMenu.of("불닭까르보나라", 1_000), 2)
        ), Collections.emptyList());

        Money result = order.calculateTotalPrice();

        assertThat(result).isEqualTo(Money.from(4_000));
    }


    @DisplayName("할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개를 증정한다.")
    @Test
    void presentMenu() {
        Order order = Order.of(List.of(
                OrderLineItem.of(MainMenu.of("까르보나라", 100_000), 2)
        ), Collections.emptyList());

        Menu result = order.present(BeverageMenu.of("샴페인", 2_000));

        assertThat(result.getName()).isEqualTo("샴페인");
    }

    @DisplayName("할인 전 총주문 금액이 12만 원보다 낮다면, 아무것도 증정하지 않는다.")
    @Test
    void presentEmptyMenu() {
        Order order = Order.of(List.of(
                OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 2),
                OrderLineItem.of(MainMenu.of("불닭까르보나라", 1_000), 2)
        ), Collections.emptyList());

        Menu result = order.present(BeverageMenu.of("샴페인", 2_000));

        assertThat(result.getName()).isEmpty();
    }

    @DisplayName("모든 할인 적용 결과를 조회할 수 있다.")
    @Test
    void calculateDiscountBenefit() {
        Order order = Order.of(List.of(
                OrderLineItem.of(DessertMenu.of("붕어싸만코", 10_000), 2),
                OrderLineItem.of(DessertMenu.of("치즈케이크", 10_000), 2)
        ), List.of(new ChristmasDDayDiscountPolicy()));

        List<DiscountResult> result = order.calculateDiscountBenefit(Date.from(25));

        assertThat(result)
                .containsExactly(
                        new DiscountResult("크리스마스 디데이 할인", Money.from(-3_400)),
                        new DiscountResult("평일 할인", Money.from(-8_092))
                );
    }

    @DisplayName("총주문 금액 10,000원 이상인 경우에만 혜택을 적용할 수 있다.")
    @Test
    void checkDiscountBenefitCondition() {
        Order order = Order.of(List.of(
                OrderLineItem.of(MainMenu.of("까르보나라", 1_000), 9)
        ), List.of(new ChristmasDDayDiscountPolicy()));

        List<DiscountResult> result = order.calculateDiscountBenefit(Date.from(25));

        assertThat(result).isEmpty();
    }

    @DisplayName("주문의 할인 이후 예상 결제 금액을 조회할 수 있다.")
    @Test
    void queryDiscountedTotalPrice() {
        Order order = Order.of(List.of(
                        OrderLineItem.of(MainMenu.of("티본스테이크", 55_000), 1),
                        OrderLineItem.of(MainMenu.of("바비큐립", 54_000), 1),
                        OrderLineItem.of(DessertMenu.of("초코케이크", 15_000), 2),
                        OrderLineItem.of(BeverageMenu.of("제로콜라", 3_000), 1)),
                List.of(new ChristmasDDayDiscountPolicy(), new SpecialDiscountPolicy()));

        Money result = order.calculateDiscountedTotalPrice(Date.from(3));

        assertThat(result).isEqualTo(Money.from(135_754));
    }
}
