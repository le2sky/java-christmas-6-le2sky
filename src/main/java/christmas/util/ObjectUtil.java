package christmas.util;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class ObjectUtil {

    public static <T> void requireIncludeNonNull(List<T> obj, String message) throws IllegalArgumentException {
        obj.forEach(element -> requireNonNull(element, message));
    }

    public static void requireNonNull(Object obj, String message) throws IllegalArgumentException {
        if (Objects.isNull(obj)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <X extends RuntimeException> void requireNonNull(Object obj, Supplier<X> exceptionSupplier) throws X {
        if (Objects.isNull(obj)) {
            throw exceptionSupplier.get();
        }
    }
}
