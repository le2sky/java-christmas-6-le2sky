package christmas.global.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateUtilTest {

    @DisplayName("주말인지 확인할 수 있다.")
    @Test
    void isWeekend() {
        boolean result = DateUtil.isWeekend(2023, 12, 30);

        assertThat(result).isTrue();
    }

    @DisplayName("평일인지 확인할 수 있다.")
    @Test
    void isWeekDay() {
        boolean result = DateUtil.isWeekday(2023, 12, 28);

        assertThat(result).isTrue();
    }
}
