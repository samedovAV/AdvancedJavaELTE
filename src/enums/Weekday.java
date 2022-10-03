package enums;

import java.util.function.Function;
import java.util.function.Supplier;

public enum Weekday {
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT,
    SUN;

    public Function<Integer, Weekday> oneA = (n) -> {
        int index = Weekday.valueOf(this.toString()).ordinal();
        int newIndex = index + n;
        return Weekday.values()[newIndex % 7];
    };

    public Function<Weekday, Boolean> oneB = (day) -> day.ordinal() > this.ordinal();

    final Weekday currentWeekday = this;
    public final Supplier<Weekday> nextWeekday = new Supplier<>() {
        Weekday current = currentWeekday;

        @Override
        public Weekday get() {
            current = current.oneA.apply(1);
            return current;
        }
    };
}
