package christmas.domain.discount.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DiscountResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekendDiscountPolicyTest {

    @DisplayName("주말(금요일,토요일)에는 2,023원 할인 받을 수 있다.")
    @ValueSource(ints = {29, 30})
    @ParameterizedTest
    void discount(int source) {
        WeekendDiscountPolicy weekendDiscountPolicy = new WeekendDiscountPolicy();

        DiscountResult result = weekendDiscountPolicy.discount(Date.from(source));

        assertThat(result).isEqualTo(new DiscountResult("주말 할인", Money.from(-2_023)));
    }
}
