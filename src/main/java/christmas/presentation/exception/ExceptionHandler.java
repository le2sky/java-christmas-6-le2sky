package christmas.presentation.exception;

import christmas.presentation.view.ExceptionView;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class ExceptionHandler {

    private static final String UNEXPECTED_MESSAGE = "예기치 못한 에러가 발생했습니다.";

    private ExceptionHandler() {

    }

    public static <T> T handle(Supplier<T> supplier, String message) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NoSuchElementException e) {
            return handleExpected(supplier, message);
        } catch (Exception e) {
            return handleUnExpected(supplier, message);
        }
    }

    private static <T> T handleExpected(Supplier<T> supplier, String message) {
        ExceptionView.printRetryMessage(message);

        return handle(supplier, message);
    }

    private static <T> T handleUnExpected(Supplier<T> supplier, String message) {
        ExceptionView.printRetryMessage(UNEXPECTED_MESSAGE);

        return handle(supplier, message);
    }
}
