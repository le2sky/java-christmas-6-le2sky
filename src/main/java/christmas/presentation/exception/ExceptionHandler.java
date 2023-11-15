package christmas.presentation.exception;

import camp.nextstep.edu.missionutils.Console;
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
            ExceptionView.printRetryMessage(message);
            Console.readLine();
            return handle(supplier, message);
        }
    }
}
