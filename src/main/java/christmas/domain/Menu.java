package christmas.domain;

import christmas.domain.common.Money;

import java.util.Objects;

class Menu {

    private final MenuName name;
    private final Money price;

    private Menu(MenuName name, Money price) {
        this.name = name;
        this.price = price;
    }

    public static Menu of(String name, int price) {
        return new Menu(MenuName.from(name), Money.from(price));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(name, menu.name) && Objects.equals(price, menu.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
