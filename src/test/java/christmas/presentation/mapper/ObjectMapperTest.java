package christmas.presentation.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.application.request.OrderLineItemRequest;
import christmas.application.request.OrderRequest;
import christmas.domain.common.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;

class ObjectMapperTest {

    @DisplayName("주어진 문자열로 주문 요청 객체를 매핑할 수 있다.")
    @Test
    void mapToOrderRequest() {
        OrderRequest result = ObjectMapper.mapToOrderRequest("가지볶음-3,콩나물국밥-2");

        OrderRequest expected = new OrderRequest(List.of(
                new OrderLineItemRequest("가지볶음", 3),
                new OrderLineItemRequest("콩나물국밥", 2)));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("주어진 문자열을 날짜로 만들 수 있다.")
    @Test
    void mapToInt() {
        Date result = ObjectMapper.mapToDate("2");

        assertThat(result.getDayOfMonth()).isEqualTo(2);
    }

    @DisplayName("알 수 없는 문자열이 주어진 경우, 주문 요청 객체를 매핑할 수 없다.")
    @Test
    void checkInputNonNull() {
        assertThatThrownBy(() -> ObjectMapper.mapToOrderRequest(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 문자열을 입력해주세요.");
    }

    @DisplayName("알 수 없는 문자열이 주어진 경우, 날짜로 매핑할 수 없다.")
    @Test
    void checkInputNonNullForNumber() {
        assertThatThrownBy(() -> ObjectMapper.mapToDate(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 문자열을 입력해주세요.");
    }

    @DisplayName("요청 포맷 예외 - index out of bounds exception")
    @ValueSource(strings = {"가지볶음-2,가지볶음-", "가지볶음3,", " "})
    @ParameterizedTest
    void checkIndexOutOfBoundsException(String source) {
        assertThatThrownBy(() -> ObjectMapper.mapToOrderRequest(source))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("숫자 포맷 예외 - number format exception")
    @ValueSource(strings = {"가지볶음-a,가지볶음-3", "가지볶음3-v-q", "가-지,사-과,배-메론"})
    @ParameterizedTest
    void checkNumberFormatException(String source) {
        assertThatThrownBy(() -> ObjectMapper.mapToOrderRequest(source))
                .isInstanceOf(NumberFormatException.class);
    }
}
