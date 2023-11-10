package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_DATE;

import java.util.Arrays;

public enum Day {
    MON(4),
    TUE(5),
    WED(6),
    THU(0),
    FRI(1),
    SAT(2),
    SUN(3);

    private Integer dateMod;

    Day(Integer dateMod) {
        this.dateMod = dateMod;
    }

    public static Day getDay(int date) {
        checkDateRange(date);
        return Arrays.stream(Day.values())
                .filter(day -> (date % 7) == day.dateMod)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DATE.getMessage()));
    }

    private static void checkDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public boolean isWeekend() {
        return this == FRI || this == SAT;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public static boolean isSpecialDay(int date) {
        return getDay(date) == SUN || date == 25;
    }

    public static boolean isDdayPeriod(int date) {
        return date >= 1 && date <= 25;
    }
}
