package christmas.presentation.view;

public class ExceptionView {

    private static final String RETRY_FORMAT = "[ERROR] %s 다시 입력해 주세요.%n";

    public static void printRetryMessage(String message) {
        System.out.format(RETRY_FORMAT, message);
    }
}
