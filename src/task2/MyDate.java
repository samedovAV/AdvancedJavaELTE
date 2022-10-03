package task2;

import enums.Month;
import enums.Weekday;

import java.util.Calendar;

public class MyDate {
    int year;
    Month month;
    int day;

    public MyDate(int year, Month month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    Weekday getWeekday() {
        Weekday res = null;
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.day, this.month.ordinal(), this.day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        res = Weekday.values()[dayOfWeek];
        /*for (enums.Weekday weekday : enums.Weekday.values()) {
            if (dayOfWeek == weekday.ordinal()) {
                res = weekday;
            }
        }*/
        return res;
    }

    // boolean isThisDay(task2.MyDate date) {}

    public static void main(String[] args) {
        Weekday answer = new MyDate(2022, Month.SEP, 22).getWeekday();
        System.out.println(answer.toString());
    }
}
