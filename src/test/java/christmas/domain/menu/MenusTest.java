package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.common.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class MenusTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Menus menus = Menus.from(List.of(
                Menu.of("까르보나라", 1_000),
                Menu.of("불닭볶음면", 2_000)));
        Menus other = Menus.from(List.of(
                Menu.of("까르보나라", 1_000),
                Menu.of("불닭볶음면", 2_000)));

        assertThat(menus).isEqualTo(other);
    }

    @DisplayName("알 수 없는 목록으로 메뉴를 생성할 수 없다.")
    @Test
    void checkMenusNonNull() {
        assertThatThrownBy(() -> Menus.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 메뉴 목록입니다.");
    }

    @DisplayName("알 수 없는 메뉴가 포함된 목록으로 메뉴를 생성할 수 없다.")
    @Test
    void checkMenusIncludeNull() {
        List<Menu> menus = new ArrayList<>();
        menus.add(null);
        menus.add(Menu.of("까르보나라", 2_000));

        assertThatThrownBy(() -> Menus.from(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 메뉴가 포함되어 있습니다.");
    }
    
    @DisplayName("할인 전 총주문 금액을 계산할 수 있다.")
    @Test
    void calculateTotalPrice() {
        Menus menus = Menus.from(List.of(
                Menu.of("까르보나라", 1_000),
                Menu.of("불닭볶음면", 2_000)));

        Money result = menus.calculateTotalPrice();

        assertThat(result).isEqualTo(Money.from(3_000));
    }
}
