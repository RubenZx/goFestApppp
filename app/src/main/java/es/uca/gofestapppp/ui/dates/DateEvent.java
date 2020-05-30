package es.uca.gofestapppp.ui.dates;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateEvent {
    private Date date;
    private String event;

    public DateEvent(int day, int month, int year, String event) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        date = cal.getTime();
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    @NotNull
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return formatter.format(date);
    }

    public boolean validateDate() {
        Date today = new Date();
        return today.before(date);
    }

    public int getDifDays() {
        Date today = new Date();
        return (int)((date.getTime() - today.getTime())/86400000);
    }


}
