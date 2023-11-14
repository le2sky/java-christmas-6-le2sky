package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.common.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateDiscountPolicyTest {

    @DisplayName("할인할 수 없다면, 할인 결과를 불러올 수 없다.")
    @Test
    void canNotDiscount() {
        DateDiscountPolicy dateDiscountPolicy = dateDiscountPolicy();

        assertThatThrownBy(() -> dateDiscountPolicy.discount(Date.from(2)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("할인을 적용할 수 없습니다.");
    }

    @DisplayName("알 수 없는 날짜로 할인 결과를 불러올 수 없다.")
    @Test
    void checkDateNonNull() {
        DateDiscountPolicy dateDiscountPolicy = dateDiscountPolicy();

        assertThatThrownBy(() -> dateDiscountPolicy.discount(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 날짜입니다.");
    }

    @DisplayName("알 수 없는 날짜로 할인 결과를 불러올 수 없다.")
    @Test
    void checkDateNonNullForIsDiscountable() {
        DateDiscountPolicy dateDiscountPolicy = dateDiscountPolicy();

        assertThatThrownBy(() -> dateDiscountPolicy.isDiscountable(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 날짜입니다.");
    }

    private DateDiscountPolicy dateDiscountPolicy() {

        return new DateDiscountPolicy() {

            @Override
            public boolean isSatisfied(Date date) {
                return false;
            }

            @Override
            protected DiscountResult bulidDiscountResult(Date date) {
                return null;
            }
        };
    }
}
