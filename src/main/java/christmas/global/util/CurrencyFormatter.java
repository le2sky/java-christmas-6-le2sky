package christmas.global.util;

import christmas.domain.common.Money;
import java.text.NumberFormat;

public class CurrencyFormatter {

    private CurrencyFormatter() {
    }

    public static String format(long number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();

        return numberFormat.format(number) + Money.CURRENCY;
    }
}
