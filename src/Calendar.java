import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;


/**
 * Created by employee on 11/3/16.
 */

public class Calendar {

    static LocalDate dateToday = LocalDate.now();
    static String defaultPrintParameter = "%4d";

    public static void printCalendar(String monthToPrintInput, DayOfWeek weekToStartFrom, DayOfWeek... weekend) {

        Month monthToPrint = getInputMonth(monthToPrintInput);
        List<Day> daysToPrint = Day.createDays(monthToPrint);

        HeaderDays.printHeaderDays(weekToStartFrom, weekend);
        printDays(daysToPrint, weekToStartFrom, weekend);
    }

    public static Month getInputMonth(String month) {
        if (month.isEmpty()) {
            Month monthToPrint = dateToday.getMonth();
            return monthToPrint;
        }
        return Month.valueOf(month);
    }

    public static void printDays(List<Day> daysToPrint, DayOfWeek firstDayOfWeek, DayOfWeek... weekend) {
        for (Day day : daysToPrint) {

            if (Day.isFirstDayOfMonth(day)) {
                Day.printFirstDayOfMonth(day, firstDayOfWeek);
                continue;
            }

            if (Day.isToday(day)) {
                Day.printToday(day, defaultPrintParameter, firstDayOfWeek);
                continue;
            }

            if (Day.isWeekend(day, weekend)) {
                Day.printWeekend(day, defaultPrintParameter, firstDayOfWeek);
                continue;
            }

            Day.printCommonDay(day, defaultPrintParameter, firstDayOfWeek);
        }
    }


}