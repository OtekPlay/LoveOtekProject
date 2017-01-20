package pl.otekplay.loveotek.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String getDate(final long data) {
        final Date time = new Date(data);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");
        return dateFormat.format(time);
    }

}
