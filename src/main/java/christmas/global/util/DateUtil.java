package christmas.global.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtil {

    private DateUtil() {
    }

    public static boolean isWeekend(int year, int month, int dayOfMonth) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return isWeekend(dayOfWeek);
    }

    public static boolean isWeekday(int year, int month, int dayOfMonth) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return !isWeekend(dayOfWeek);
    }

    private static boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
