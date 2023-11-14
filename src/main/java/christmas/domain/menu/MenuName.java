package christmas.domain.menu;

import static christmas.global.util.ObjectUtil.requireNonNull;

import java.util.Objects;

public class MenuName {

    private static final String UNKNOWN_NAME_MESSAGE = "알 수 없는 이름입니다.";

    private final String name;

    private MenuName(String name) {
        this.name = name;
    }

    public static MenuName from(String name) {
        requireNonNull(name, UNKNOWN_NAME_MESSAGE);

        return new MenuName(name);
    }

    public String getName() {
        return name;
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
