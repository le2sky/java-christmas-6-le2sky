package christmas.domain.menu;

import christmas.domain.common.Date;
import christmas.domain.common.Money;
import christmas.domain.discount.DateDiscountPolicy;
import christmas.domain.discount.DiscountResult;
import java.util.Objects;

public abstract class AbstractMenu implements Menu {

    protected final MenuName name;
    protected final Money price;
    protected final DateDiscountPolicy policy;

    protected AbstractMenu(MenuName name, Money price, DateDiscountPolicy policy) {
        this.name = name;
        this.price = price;
        this.policy = policy;
    }

    @Override
    public Money calculatePriceWith(int quantity) {
        return price.multiply(quantity);
    }

    @Override
    public DiscountResult calculateDiscountBenefitWith(Date date, int quantity) {
        DiscountResult menuDiscountResult = policy.discount(date);

        return new DiscountResult(menuDiscountResult.policyName(), menuDiscountResult.amount().multiply(quantity));
    }

    @Override
    public boolean isDiscountable(Date date) {
        return policy.isDiscountable(date);
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMenu that = (AbstractMenu) o;
        return Objects.equals(name, that.name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
