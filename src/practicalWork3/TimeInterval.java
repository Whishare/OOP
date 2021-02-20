package practicalWork3;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeInterval {
    Calendar registrationDate;
    Calendar payDate;
    public TimeInterval() {
        registrationDate = new GregorianCalendar();
        payDate = new GregorianCalendar();
        payDate.set(Calendar.DAY_OF_MONTH,1);
        if (registrationDate.get(Calendar.DAY_OF_MONTH) != 1) {
            payDate.add(Calendar.MONTH, 1);
        }
    }
    public void confirmRent() {
        payDate.add(Calendar.MONTH,1);
    }
}
