package practicalWork3Refactor;

import java.util.Calendar;
import java.util.Comparator;
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
    private int differenceBetweenDayss;
    public boolean getPaid() {
        return paid;
    }
    public int getRent() {
        return paymentRent;
    }
    public Calendar getPaymentDay() {
        return  paymentDay;
    }
    public String getName() {
        return flat.getResident().getName();
    }
    public int getIndex() {
        return flat.getIndex();
    }
    public void pay() {
        if (!paid) {
            this.paymentDay = new GregorianCalendar();
            Date payDay = paymentDay.getTime();
            Date payPerDay = paymentPerDay.getTime();
            long differenceBetweenDays = payDay.getTime()/86400000L - payPerDay.getTime()/86400000L;
            differenceBetweenDayss = (int)differenceBetweenDays;
            if (paymentPerDay.get(Calendar.YEAR) != paymentDay.get(Calendar.YEAR)) {
                int differenceBetweenYears = paymentPerDay.get(Calendar.YEAR) - paymentDay.get(Calendar.YEAR);
                //System.out.println("\n"+differenceBetweenDays+"\n");
                differenceBetweenDays += differenceBetweenYears * 365;
            }
            if (differenceBetweenDays > 1) {
                paymentDebt = paymentRent;
                paymentRent = 0;
                if (differenceBetweenDays > 30) {
                    paymentFine = paymentDebt / 10;
                }
            }
            paid = true;
        }
    }
    @Override
    public String toString() {
        if (paid) {
            return flat.getIndex() + "," + flat.getResident().getName() + "," + paymentRent + "," + paymentDebt + "," + paymentFine + "," + paymentPerDay.getTime() + ","
                    + paymentDay.getTime() + "," + paid + "," + differenceBetweenDayss;
        }
        return flat.getIndex()+","+flat.getResident().getName()+","+paymentRent+","+paymentDebt+","+paymentFine+","+paymentPerDay.getTime()+","
                +","+paid;
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
class PaymentSort implements Comparator<Payment>{
    @Override
    public int compare(Payment o1, Payment o2) {
        return Integer.compare(o1.getIndex(), o2.getIndex());
    }
}
class PaymentRent extends PaymentSort implements Comparator<Payment> {
    @Override
    public int compare(Payment o1, Payment o2) {
        return Integer.compare(o1.getRent(),o2.getRent());
    }
}
class PaymentDay extends PaymentSort implements Comparator<Payment> {
    @Override
    public int compare(Payment o1, Payment o2) {
        return o1.getPaymentDay().compareTo(o2.getPaymentDay());
    }
}
class PaymentName extends PaymentSort implements Comparator<Payment> {
    @Override
    public int compare(Payment o1, Payment o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
