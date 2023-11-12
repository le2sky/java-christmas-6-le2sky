package christmas.domain.common;

public class Date {

    public static final int BASE_MONTH = 12;
    private static final int DAY_OF_MONTH_MIN_RANGE = 1;
    private static final int DAY_OF_MONTH_MAX_RANGE = 31;
    private static final String INVALID_DAY_OF_MONTH_RANGE_MESSAGE =
            "날짜는 " + DAY_OF_MONTH_MIN_RANGE + "과 " + DAY_OF_MONTH_MAX_RANGE + " 사이의 수를 입력해주세요.";

    private final int dayOfMonth;

    private Date(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public static Date from(int dayOfMonth) {
        checkDayOfMonthRange(dayOfMonth);

        return new Date(dayOfMonth);
    }

    private static void checkDayOfMonthRange(int dayOfMonth) {
        checkDayOfMonthMinRange(dayOfMonth);
        checkDayOfMonthMaxRange(dayOfMonth);
    }

    private static void checkDayOfMonthMinRange(int dayOfMonth) {
        if (dayOfMonth < DAY_OF_MONTH_MIN_RANGE) {
            throw new IllegalArgumentException(INVALID_DAY_OF_MONTH_RANGE_MESSAGE);
        }
    }

    private static void checkDayOfMonthMaxRange(int dayOfMonth) {
        if (dayOfMonth > DAY_OF_MONTH_MAX_RANGE) {
            throw new IllegalArgumentException(INVALID_DAY_OF_MONTH_RANGE_MESSAGE);
        }
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }
}
