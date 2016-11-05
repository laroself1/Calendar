import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by employee on 11/2/16.
 */

public class Tests {
    public static ByteArrayOutputStream outCont = new ByteArrayOutputStream();
    public static String defaultPrintParameter = "%3d";
    public static String red = "\u001B[31m";
    public static String green = "\u001B[32m";

    @Before
    public void setDefStream() {
        System.setOut(new PrintStream(outCont));
    }

    @After
    public void cleanUpStream() {
        System.setOut(null);
    }

    @Test
    public void assertWeekendPrintInRed() {

        Day weekend = new Day();
        weekend.setDayOfWeek(DayOfWeek.SATURDAY);
        Day.printWeekend(weekend,defaultPrintParameter, DayOfWeek.THURSDAY);

        assertThat(outCont.toString(),startsWith(red));
    }

    @Test
    public void assertTodayPrintInGreen() {

        LocalDate ld = LocalDate.now();
        Day today = new Day();
        today.setDayOfWeek(ld.getDayOfWeek());
        today.setPrintValue(ld.getDayOfMonth());

        Day.printToday(today,defaultPrintParameter, DayOfWeek.MONDAY);

        assertThat(outCont.toString(),startsWith(green));
    }

    @Test
    public void assertHeaderDaysOfWeekCorrectPrint(){

        HeaderDays.printHeaderDays(DayOfWeek.TUESDAY, DayOfWeek.SUNDAY);

        assertThat(outCont.toString(), startsWith(" Tue"));

    }


    @Test
    public void checkIfNumberOfDaysInMonthNotBiggerThan31AndNotLessThan28(){

        List<Day> daysInMonth =  Day.createDays(Month.DECEMBER);

        assertTrue(daysInMonth.size()>=28 && daysInMonth.size()<=31);
    }



}

