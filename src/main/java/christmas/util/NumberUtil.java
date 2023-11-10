package christmas.util;

public class NumberUtil {

    private static final int POSITIVE_STANDARD = 0;

    public static void requirePositiveNumber(int number, String message) throws IllegalArgumentException {
        if (number <= POSITIVE_STANDARD) {
            throw new IllegalArgumentException(message);
        }
    }
}
