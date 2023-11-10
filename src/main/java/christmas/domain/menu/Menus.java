package christmas.domain.menu;

import static christmas.util.ObjectUtil.requireIncludeNonNull;
import static christmas.util.ObjectUtil.requireNonNull;

import java.util.List;
import java.util.Objects;

class Menus {

    private static final String UNKNOWN_MENUS_MESSAGE = "알 수 없는 메뉴 목록입니다.";
    private static final String UNKNOWN_MENU_MESSAGE = "알 수 없는 메뉴가 포함되어 있습니다.";

    private final List<Menu> menus;

    private Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public static Menus from(List<Menu> menus) {
        requireNonNull(menus, UNKNOWN_MENUS_MESSAGE);
        requireIncludeNonNull(menus, UNKNOWN_MENU_MESSAGE);

        return new Menus(menus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menus menus1 = (Menus) o;
        return Objects.equals(menus, menus1.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menus);
    }
}
