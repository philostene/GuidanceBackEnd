package intra.poleemploi.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTest {
    public static void main(String [] args) throws ParseException {
        String sDate = "31 déc. 2019";
        Date date1 = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE).parse(sDate);
        System.out.println(sDate + "\t" + date1);
        DateFormat dateFormat = DateFormat.getDateInstance(
                DateFormat.SHORT);
        System.out.println(dateFormat.format(date1));
    }
}