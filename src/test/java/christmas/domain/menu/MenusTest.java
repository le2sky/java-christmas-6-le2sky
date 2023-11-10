package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("메뉴 목록이 주어지면, 해당 메뉴들을 포함하는지 확인할 수 있다.")
    @Test
    void containAll() {
        Menus menus = Menus.from(List.of(
                Menu.of("까르보나라", 1_000),
                Menu.of("불닭볶음면", 2_000),
                Menu.of("육개장사발면", 3_000)));
        Menus other = Menus.from(List.of(
                Menu.of("까르보나라", 1_000),
                Menu.of("불닭볶음면", 2_000)));

        boolean result = menus.containsAll(other);

        assertThat(result).isTrue();
    }

    @DisplayName("알 수 없는 메뉴 목록이 주어지면, 포함 여부를 알 수 없다.")
    @Test
    void checkOtherMenusNonNull() {
        Menus menus = Menus.from(List.of(
                Menu.of("까르보나라", 1_000),
                Menu.of("불닭볶음면", 2_000),
                Menu.of("육개장사발면", 3_000)));

        assertThatThrownBy(() -> menus.containsAll(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알 수 없는 메뉴 목록입니다.");
    }
}
