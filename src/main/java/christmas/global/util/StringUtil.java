package christmas.global.util;

public class StringUtil {

    private StringUtil() {
    }

    public static void requireNotBlank(String string, String message) throws IllegalArgumentException {
        if (string.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
}
