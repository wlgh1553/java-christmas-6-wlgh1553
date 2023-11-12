package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_DATE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Day {
    MON(4), TUE(5), WED(6), THU(0),
    FRI(1), SAT(2), SUN(3);

    private Integer dateMod;
    private static Set<Integer> specialDays =
            new HashSet<>(Arrays.asList(3, 10, 17, 24, 25, 31));

    Day(Integer dateMod) {
        this.dateMod = dateMod;
    }

    private static Day getDay(int date) {
        checkDateRange(date);
        return Arrays.stream(Day.values())
                .filter(day -> (date % 7) == day.dateMod)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DATE.getMessage()));
    }

    public static int checkDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
        return date;
    }

    public static boolean isWeekend(int date) {
        Day day = getDay(date);
        return day == FRI || day == SAT;
    }

    public static boolean isWeekday(int date) {
        return !isWeekend(date);
    }

    public static Boolean isSpecialDay(int date) {
        checkDateRange(date);
        return specialDays.contains(date);
    }

    public static Boolean isDdayPeriod(int date) {
        checkDateRange(date);
        return date >= 1 && date <= 25;
    }
}
