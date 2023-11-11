package christmas.infrastructure.persistence.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;

class SimpleMenuRepositoryTest {

    private final SimpleMenuRepository simpleMenuRepository = new SimpleMenuRepository();

    @DisplayName("모든 메뉴를 불러온다.")
    @Test
    void findAll() {
        Menus result = simpleMenuRepository.findAll();

        Menus menus = Menus.from(List.of(
                Menu.of("크리스마스파스타", 25_000),
                Menu.of("초코케이크", 15_000),
                Menu.of("티본스테이크", 55_000)));
        assertThat(result.containsAll(menus)).isTrue();
    }

    @DisplayName("특정 메뉴를 불러온다.")
    @Test
    void findByName() {
        Menu result = simpleMenuRepository.findByName("아이스크림");

        assertThat(result).isEqualTo(Menu.of("아이스크림", 5_000));
    }

    @DisplayName("존재하지 않는 메뉴를 불러오면, 예외를 발생한다.")
    @Test
    void findByUnknownName() {
        assertThatThrownBy(() -> simpleMenuRepository.findByName("존재하지 않는 메뉴"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 메뉴입니다.");
    }
}
