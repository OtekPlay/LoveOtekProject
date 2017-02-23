package pl.otekplay.loveotek.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeUtil {
    public static String getDate(final long data) {
        final Date time = new Date(data);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");
        return dateFormat.format(time);
    }

    public static long parseString(String string) {
        List<String> list = new ArrayList<>();
        String c;
        int goBack = 0;
        for (int i = 0; i < string.length(); i++) {
            c = String.valueOf(string.charAt(i));
            if (c.matches("[a-zA-Z]")) {
                list.add(string.substring(goBack, i + 1));
                goBack = i + 1;
            }
        }
        long amount;
        long total = 0;
        char ch;
        for (String st : list) {
            ch = st.charAt(st.length() - 1);
            if (st.length() != 1 && String.valueOf(ch).matches("[M,w,d,h,m,s]")) {
                amount = Math.abs(Integer.parseInt(st.substring(0, st.length() - 1)));
                switch (ch) {
                    case 's':
                        total += (amount * 1000);
                        break;
                    case 'm':
                        total += (amount * 1000 * 60);
                        break;
                    case 'h':
                        total += (amount * 1000 * 3600);
                        break;
                    case 'd':
                        total += (amount * 1000 * 3600 * 24);
                        break;
                    case 'w':
                        total += (amount * 1000 * 3600 * 24 * 7);
                        break;
                }
            }
        }

        if (total == 0) return -1;

        return total;
    }

    public static String getPrettyDate(long data) {
        Date time = new Date(data);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM ");
        return dateFormat.format(time);
    }

    public static String parseLong(long milliseconds, boolean abbreviate) {
        List<String> units = new ArrayList<>();
        long amount;
        amount = milliseconds / (7 * 24 * 60 * 60 * 1000);
        units.add(amount + "w");
        amount = milliseconds / (24 * 60 * 60 * 1000) % 7;
        units.add(amount + "d");
        amount = milliseconds / (60 * 60 * 1000) % 24;
        units.add(amount + "h");
        amount = milliseconds / (60 * 1000) % 60;
        units.add(amount + "m");
        amount = milliseconds / 1000 % 60;
        units.add(amount + "s");
        String[] array = new String[5];
        char end;
        for (String s : units) {
            end = s.charAt(s.length() - 1);
            switch (end) {
                case 'w':
                    array[0] = s;
                case 'd':
                    array[1] = s;
                case 'h':
                    array[2] = s;
                case 'm':
                    array[3] = s;
                case 's':
                    array[4] = s;
            }
        }
        units.clear();
        for (String s : array)
            if (!s.startsWith("0")) units.add(s);
        StringBuilder sb = new StringBuilder();
        String word, count, and;
        char c;
        for (String s : units) {
            if (!abbreviate) {
                c = s.charAt(s.length() - 1);
                count = s.substring(0, s.length() - 1);
                switch (c) {
                    case 'w':
                        word = "tygodni" + (count.equals("1") ? "" : "s");
                        break;
                    case 'd':
                        word = "dni" + (count.equals("1") ? "" : "s");
                        break;
                    case 'h':
                        word = "godzin" + (count.equals("1") ? "" : "s");
                        break;
                    case 'm':
                        word = "minut" + (count.equals("1") ? "" : "s");
                        break;
                    default:
                        word = "sekund" + (count.equals("1") ? "" : "s");
                        break;
                }
                and = s.equals(units.get(units.size() - 1)) ? "" : s.equals(units.get(units.size() - 2)) ? " i " : ", ";
                sb.append(count).append(" ").append(word).append(and);
            } else {
                sb.append(s);
                if (!s.equals(units.get(units.size() - 1)))
                    sb.append("-");
            }
        }
        return sb.toString().trim().length() == 0 ? null : sb.toString().trim();

    }


}
