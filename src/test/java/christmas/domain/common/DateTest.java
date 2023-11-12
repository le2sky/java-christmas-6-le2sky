package christmas.domain.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Date date = Date.from(1);

        assertThat(date.getDayOfMonth()).isEqualTo(1);
    }

    @DisplayName("날짜는 최소 1 이상이어야 한다.")
    @Test
    void checkMinRange() {
        assertThatThrownBy(() -> Date.from(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("날짜는 1과 31 사이의 수를 입력해주세요.");
    }

    @DisplayName("날짜는 31 이하여야 한다.")
    @Test
    void checkMaxRange() {
        assertThatThrownBy(() -> Date.from(32))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("날짜는 1과 31 사이의 수를 입력해주세요.");
    }
}
