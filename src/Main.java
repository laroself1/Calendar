import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by employee on 11/3/16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String monthToPrintStr = scanner.nextLine();


        DayOfWeek dayToStartPrintCalendarFrom = DayOfWeek.MONDAY;

        Calendar.printCalendar(monthToPrintStr, dayToStartPrintCalendarFrom,DayOfWeek.SUNDAY,DayOfWeek.THURSDAY);



    }
}