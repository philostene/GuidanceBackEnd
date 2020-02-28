package intra.poleemploi.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTest {
    public static void main(String [] args) throws ParseException {
        String sDate = "31 déc. 2019";
        Date date1 = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE).parse(sDate);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {

        } // sunday or saturday
        else {}  //day of week

        System.out.println(sDate + "\t" + date1);
        DateFormat dateFormat = DateFormat.getDateInstance(
                DateFormat.SHORT);
        System.out.println(dateFormat.format(date1));
    }
}