package christmas.domain;

import java.util.Objects;

class MenuName {

    private static final String UNKNOWN_NAME_MESSAGE = "알 수 없는 이름입니다.";
    private static final String INVALID_NAME_LENGTH_MESSAGE = "메뉴의 이름은 공백을 제외한, 최소 1글자 이상이어야 합니다.";

    private final String name;

    private MenuName(String name) {
        this.name = name;
    }

    public static MenuName from(String name) {
        validate(name);

        return new MenuName(name);
    }

    private static void validate(String name) {
        checkNameNonNull(name);
        checkNameEmpty(name);
    }

    private static void checkNameNonNull(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException(UNKNOWN_NAME_MESSAGE);
        }
    }

    private static void checkNameEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE);
        }
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
