package christmas.domain.discount.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DiscountResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekdayDiscountPolicyTest {

    @DisplayName("평일(일요일~목요일)에는 2,023원 할인 받을 수 있다.")
    @ValueSource(ints = {24, 25, 26, 27, 28})
    @ParameterizedTest
    void discount(int source) {
        WeekdayDiscountPolicy weekdayDiscountPolicy = new WeekdayDiscountPolicy();

        DiscountResult result = weekdayDiscountPolicy.discount(Date.from(source));

        assertThat(result).isEqualTo(new DiscountResult("평일 할인", Money.from(-2_023)));
    }
}
