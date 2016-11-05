import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;

public class HeaderDays {

    public static void printHeaderDays(DayOfWeek firstDayOfWeek, DayOfWeek...weekendsParam) {

        DayOfWeek headerDay = firstDayOfWeek;

        List<DayOfWeek> weekends = convertWeekendVarArgToList(weekendsParam);

        for (int i = 1; i <= 7; i++) {
            if(weekends.contains(headerDay)){
                printWeekendHeader(headerDay);
            }
            else {
                printCommonDayHeader(headerDay);
            }
            headerDay = headerDay.plus(1);
        }
        System.out.println();
    }

    public static void printWeekendHeader(DayOfWeek dayOfWeek){
        System.out.print("\u001B[31m");
        System.out.printf("%4.10s",dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        System.out.print("\u001B[0m");
    }

    public static void printCommonDayHeader(DayOfWeek dayOfWeek){
        System.out.printf("%4.10s",dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
    }

    public static List<DayOfWeek> convertWeekendVarArgToList(DayOfWeek...weekend){

        List<DayOfWeek> weekends = new ArrayList<>();

        for (DayOfWeek dayOfWeek:weekend){

            weekends.add(dayOfWeek);
        }
        return weekends;}

}

