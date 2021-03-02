package practicalWork3Refactor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Payment {
    private boolean paid;
    private Flat flat;
    private int paymentRent;
    private int paymentDebt;
    private int paymentFine;
    private Calendar paymentPerDay;
    private Calendar paymentDay;
    public void pay() {
        if (!paid) {
            this.paymentDay = new GregorianCalendar();
            Date payDay = paymentDay.getTime();
            Date payPerDay = paymentPerDay.getTime();
            int differenceBetweenDays = payDay.getDay() - payPerDay.getDay();
            if (differenceBetweenDays > 0) {
                paymentDebt = paymentRent;
                paymentRent = 0;
                if (differenceBetweenDays > 30) {
                    paymentFine = paymentDebt / 10;
                }
            } else {
                paymentRent = 0;
            }
            paid = true;
        }
    }
    @Override
    public String toString() {
        return flat.getIndex()+","+flat.getResident().getName()+","+paymentRent+","+paymentDebt+","+paymentFine+","+paymentPerDay.get(Calendar.DATE)+","
                +paymentDay.get(Calendar.DAY_OF_YEAR)+","+paid;
    }
    public Payment(Flat flat, Calendar paymentPerDay) {
        this.flat = flat;
        this.paid = false;
        this.paymentRent = flat.getRent();
        this.paymentDebt = 0;
        this.paymentFine = 0;
        this.paymentDay = paymentPerDay;
        this.paymentPerDay = paymentPerDay;
    }
}
