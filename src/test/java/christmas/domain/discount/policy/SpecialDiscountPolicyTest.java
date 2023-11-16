package christmas.domain.discount.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DateDiscountPolicy;
import christmas.domain.discount.DiscountResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountPolicyTest {

    @DisplayName("이벤트 달력에 별이 있는 경우, 총주문 금액에서 1,000원을 할인한다.")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @ParameterizedTest
    void discount(int source) {
        DateDiscountPolicy christmasDDayDiscountPolicy = new SpecialDiscountPolicy();

        DiscountResult result = christmasDDayDiscountPolicy.discount(Date.from(source));

        assertThat(result).isEqualTo(new DiscountResult("특별 할인", Money.from(-1_000)));
    }
}
