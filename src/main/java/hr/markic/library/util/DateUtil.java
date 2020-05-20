package hr.markic.library.util;

import hr.markic.library.dto.microblink.BlinkIDDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtil {

    public final static String CHECK_DATE_FORMAT = "yyMMdd";

    private final Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static Date getDateFromDayMonthYear(int year, int month, int day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse( year + "-" + month + "-" + day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static LocalDate getLocalDateFromDayMonthYear(int year, int month, int day){
        return LocalDate.of(year, month, day);
    }

    public static String getDateInFormat(BlinkIDDate dateB, String format){
        Date date = getDateFromDayMonthYear(dateB.getYear(), dateB.getMonth(), dateB.getDay());
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


}
