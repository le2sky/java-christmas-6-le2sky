package christmas.domain.menu;

import static christmas.util.ObjectUtil.requireNonNull;
import static christmas.util.StringUtil.requireNotBlank;

import java.util.Objects;

class MenuName {

    private static final String UNKNOWN_NAME_MESSAGE = "알 수 없는 이름입니다.";
    private static final String INVALID_NAME_LENGTH_MESSAGE = "메뉴의 이름은 공백을 제외한, 최소 1글자 이상이어야 합니다.";

    private final String name;

    private MenuName(String name) {
        this.name = name;
    }

    public static MenuName from(String name) {
        requireNonNull(name, UNKNOWN_NAME_MESSAGE);
        requireNotBlank(name, INVALID_NAME_LENGTH_MESSAGE);

        return new MenuName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuName menuName = (MenuName) o;
        return Objects.equals(name, menuName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
