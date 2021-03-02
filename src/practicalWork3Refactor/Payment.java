package practicalWork3Refactor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Payment {
    private Flat flat;
    private boolean paid;
    private int paymentRent;
    private int paymentDebt;
    private int paymentFine;
    private Calendar paymentPerDay;
    private Calendar paymentDay;
    public void pay() {
        this.paymentDay = new GregorianCalendar();
        Date payDay = paymentDay.getTime();
        Date payPerDay = paymentPerDay.getTime();
        int differenceBetweenDays = payDay.getDay()-payPerDay.getDay();
        if (differenceBetweenDays > 0) {
            paymentDebt = paymentRent;
            paymentRent = 0;
            if (differenceBetweenDays > 30) {
                paymentFine = paymentDebt/10;
            }
        } else {
            paymentRent = 0;
        }
        paid = true;
    }
    @Override
    public String toString() {
        return "";
    }
    public Payment(Flat flat, Calendar paymentPerDay) {
        this.flat = flat;
        this.paid = false;
        this.paymentRent = flat.getRent();
        this.paymentDebt = 0;
        this.paymentFine = 0;
        this.paymentDay = null;
        this.paymentPerDay = paymentPerDay;
    }
}
