package christmas.domain.discount.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DateDiscountPolicy;
import christmas.domain.discount.DiscountResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasDDayDiscountPolicyTest {

    @DisplayName("25일 이후에는 할인이 적용되지 않는다.")
    @Test
    void checkDateRange() {
        DateDiscountPolicy christmasDDayDiscountPolicy = new ChristmasDDayDiscountPolicy();

        boolean result = christmasDDayDiscountPolicy.isDiscountable(Date.from(26));

        assertThat(result).isFalse();
    }

    @DisplayName("기본 할인 금액은 1,000원이며, 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가한다.(최대 3,400)")
    @CsvSource(value = {"1:-1000", "25:-3400"}, delimiter = ':')
    @ParameterizedTest
    void discount(int source, int expectedAmount) {
        DateDiscountPolicy christmasDDayDiscountPolicy = new ChristmasDDayDiscountPolicy();

        DiscountResult result = christmasDDayDiscountPolicy.discount(Date.from(source));

        assertThat(result).isEqualTo(new DiscountResult("크리스마스 디데이 할인", Money.from(expectedAmount)));
    }
}
