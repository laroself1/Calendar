import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * Created by employee on 11/3/16.
 */
public class Day {

    static LocalDate dateToday = LocalDate.now();
    static int currentYear = dateToday.getYear();
    static int currentDay = dateToday.getDayOfYear();

    private int printValue;
    private int dayOfYearValue;
    private DayOfWeek dayOfWeek;

    public int getPrintValue() {

        return printValue;
    }

    public void setPrintValue(int printValue) {
        this.printValue = printValue;
    }

    public int getDayOfYearValue() {
        return dayOfYearValue;
    }

    public void setDayOfYearValue(int dayOfYearValue) {
        this.dayOfYearValue = dayOfYearValue;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public static List<Day> createDays(Month month) {

        List<Day> result = new ArrayList<>();

        LocalDate localDateToPrint = LocalDate.of(currentYear, month, 1);
        LocalDate lastDayOfMonth = localDateToPrint.with(lastDayOfMonth());
        int monthToPrintLength = lastDayOfMonth.getDayOfMonth();

        for (int i = 1; i <= monthToPrintLength; i++) {

            Day day = new Day();

            day.setDayOfYearValue(localDateToPrint.getDayOfYear());
            day.setDayOfWeek(localDateToPrint.getDayOfWeek());
            day.setPrintValue(localDateToPrint.getDayOfMonth());

            result.add(day);

            localDateToPrint = localDateToPrint.plusDays(1);
        }
        return result;
    }

    public static boolean isWeekend(Day day, DayOfWeek... weekends) {
        boolean result = false;
        for (DayOfWeek dayOfWeek : weekends) {
            if (day.getDayOfWeek().equals(dayOfWeek)) {
                result = true;
            }
        }

        return result;
    }

    public static boolean isToday(Day day) {
        boolean result = false;
        if (day.getDayOfYearValue() == currentDay) {
            result = true;
        }
        return result;
    }

    public static boolean isFirstDayOfMonth(Day day) {
        boolean result = false;
        if (day.getPrintValue() == 1) {
            result = true;
        }
        return result;
    }

    public static void printWeekend(Day day, String parameter,DayOfWeek firstDayOfWeek) {

        System.out.print("\u001B[31m");
        System.out.printf(parameter, day.getPrintValue());
        System.out.print("\u001B[0m");
        isLastDayOfWeek(day,firstDayOfWeek);
    }

    public static void printToday(Day day, String parameter, DayOfWeek firstDayOfWeek) {

        System.out.print("\u001B[32m");
        System.out.printf(parameter, day.getPrintValue());
        System.out.print("\u001B[0m");
        isLastDayOfWeek(day,firstDayOfWeek);
    }

    public static void printCommonDay(Day day, String parameter, DayOfWeek firstDayOfWeek){
        System.out.printf(parameter, day.getPrintValue());
        isLastDayOfWeek(day,firstDayOfWeek);
    }

    public static void isLastDayOfWeek(Day day, DayOfWeek firstDayOfWeek){

        DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6);

        if(day.getDayOfWeek().equals(lastDayOfWeek)){
            System.out.println();

        }
    }
    public static String getPrintGapParameter(Day day, DayOfWeek firstDayOfWeekParameter){
        int paramForSpaces=4;
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        DayOfWeek firstDayOfWeek = firstDayOfWeekParameter;

        do{
            if(dayOfWeek.equals(firstDayOfWeek)){
                break;
            }
            paramForSpaces+=4;

            firstDayOfWeek=firstDayOfWeek.plus(1);

        }while (!dayOfWeek.equals(firstDayOfWeek));

        return  "%"+paramForSpaces+"d";
    }

    public static void printFirstDayOfMonth(Day day, DayOfWeek firstDayOfWeek) {

        String parameter = getPrintGapParameter(day,firstDayOfWeek);

        int switchValue = 0;

        if (isToday(day)) {
            switchValue = 2;
        }
        if (isWeekend(day)) {
            switchValue = 3;
        }
        if (isToday(day) & isWeekend(day)) {
            switchValue = 1;
        }

        switch (switchValue) {
            case 0:
                printCommonDay(day,parameter,firstDayOfWeek);
                break;
            case 1:
                printToday(day,parameter,firstDayOfWeek);
                break;
            case 2:
                printToday(day,parameter,firstDayOfWeek);
                break;
            case 3:
                printWeekend(day,parameter,firstDayOfWeek);
                break;
        }
    }
}