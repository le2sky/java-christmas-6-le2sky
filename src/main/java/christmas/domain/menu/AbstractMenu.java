package christmas.domain.menu;

import christmas.domain.common.Money;
import java.util.Objects;

public abstract class AbstractMenu implements Menu {

    protected final MenuName name;
    protected final Money price;

    protected AbstractMenu(MenuName name, Money price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public Money calculatePriceWith(int quantity) {
        return price.multiply(quantity);
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMenu abstractMenu = (AbstractMenu) o;
        return Objects.equals(name, abstractMenu.name) && Objects.equals(price, abstractMenu.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
