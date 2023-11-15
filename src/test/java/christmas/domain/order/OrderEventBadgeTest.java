package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.common.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderEventBadgeTest {

    @DisplayName("주어진 가격에 따라 배지를 전달한다.")
    @CsvSource(
            value = {"0:없음", "-4999:없음", "-5000:별", "-9999:별", "-10000:트리", "-19999:트리", "-20000:산타"},
            delimiter = ':'
    )
    @ParameterizedTest
    void from(int source, String expected) {
        OrderEventBadge result = OrderEventBadge.from(Money.from(source));

        assertThat(result.getName()).isEqualTo(expected);
    }
}
