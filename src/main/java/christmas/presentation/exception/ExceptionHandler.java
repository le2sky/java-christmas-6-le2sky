package christmas.presentation.exception;

import christmas.presentation.view.ExceptionView;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class ExceptionHandler {

    private ExceptionHandler() {
    }

    public static <T> T handle(Supplier<T> supplier, String message) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return handleExpected(supplier, message);
        }
    }

    private static <T> T handleExpected(Supplier<T> supplier, String message) {
        ExceptionView.printRetryMessage(message);

        return handle(supplier, message);
    }
}
