package server.util;

import server.helpers.StringHelper;

public class Time {

    /**
     * Returns a String date in a human readable format
     *
     * @param time Long
     * @return String
     */
    public static String unixToString(long time) {
        int s, m, h, d, o, y;
        String[] builder = new String[6];

        s = (int) Math.floor((double) (time / 1000)) % 60;
        m = (int) Math.floor((double) (time / 1000 / 60)) % 60;
        h = (int) Math.floor((double) (time / 1000 / 60 / 60)) % 24;
        d = (int) Math.floor((double) (time / 1000 / 60 / 60 / 24)) % 30;
        o = (int) Math.floor((double) (time / 1000 / 60 / 60 / 24 / 30)) % 12;
        y = (int) Math.floor((double) (time / 1000 / 60 / 60 / 24 / 30 / 12));

        if (y != 0) {
            builder[0] = y + " " + StringHelper.pluralize(y, "year");
        }

        if (o != 0) {
            builder[1] = o + " " + StringHelper.pluralize(o, "month");
        }

        if (d != 0) {
            builder[2] = d + " " + StringHelper.pluralize(d, "day");
        }

        if (h != 0) {
            builder[3] = h + " " + StringHelper.pluralize(h, "hour");
        }

        if (m != 0) {
            builder[4] = m + " " + StringHelper.pluralize(m, "minute");
        }

        if (s != 0) {
            builder[5] = s + " " + StringHelper.pluralize(s, "second");
        }

        return StringHelper.combine(builder, ", ");
    }
}
