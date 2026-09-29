package com.smartpantrymanager.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PantryItem {
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    private long id;
    public String name;
    public double quantity;
    public String unit;
    public String expiry; // dd/MM/yyyy, or "" when there is no expiry date

    public PantryItem(long id, String name, double quantity, String unit, String expiry) {
        this.setId(id);
        this.name = name;
        this.quantity = quantity;
        this.unit = unit == null ? "" : unit;
        this.expiry = expiry == null ? "" : expiry;
    }

    /** True if the text is a real calendar date in dd/MM/yyyy. */
    public static boolean isValidDate(String s) {
        try {
            SimpleDateFormat f = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            f.setLenient(false);
            f.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /** Whole days from today until expiry (negative = already expired), or null if no date. */
    public Long daysUntilExpiry() {
        if (expiry.isEmpty()) return null;
        try {
            SimpleDateFormat f = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            f.setLenient(false);
            Date d = f.parse(expiry);
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);
            return Math.round((d.getTime() - today.getTimeInMillis()) / 86400000.0);
        } catch (ParseException e) {
            return null;
        }
    }

    public boolean isExpiringSoon() {
        Long d = daysUntilExpiry();
        return d != null && d <= 3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
