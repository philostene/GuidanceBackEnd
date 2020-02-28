package intra.poleemploi.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WeekDay {

    public Boolean isWeekDay(String sDate) throws ParseException {
        Date dateConverted = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE).parse(sDate);
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateConverted);
        if ((calendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (calendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
            return false;
        } // sunday or saturday
        else {
            return true;
        }  //day of week
    }
}