package practicalWork3Refactor;

import practicalWork3Refactor.databases.DAO_Payment;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class Payment {
    private DAO_Payment dao_payment = new DAO_Payment();
    public int id;
    public int flatIndex;
    public boolean paid;
    private Flat flat;
    public int paymentRent;
    public int paymentDebt;
    public int paymentFine;
    public Calendar paymentPerDay;
    public Calendar paymentDay;
    private int differenceBetweenDays;
    public boolean getPaid() {
        return paid;
    }
    public int getDifferenceBetweenDays() {
        return differenceBetweenDays;
    }
    public int getRent() {
        return paymentRent;
    }
    public Flat getFlat() {
        return flat;
    }
    public int getDebt() {
        return paymentDebt;
    }
    public int getFine() {
        return paymentFine;
    }
    public Calendar getPaymentDay() {
        return  paymentDay;
    }
    public Calendar getPaymentPerDay() {return paymentPerDay;}
    public String getName() {
        return flat.getResident().getName();
    }
    public int getIndex() {
        return flat.getNumber();
    }
    public void setData(Payment e) {
        this.flat = e.getFlat();
        this.paymentDay = e.getPaymentDay();
        this.paymentPerDay = e.getPaymentDay();
    }

    public Payment pay() {
        if (!paid) {
            this.paymentDay = new GregorianCalendar();
            Date payDay = paymentDay.getTime();
            Date payPerDay = paymentPerDay.getTime();
            differenceBetweenDays = (int)( (payDay.getTime() - payPerDay.getTime()) / (1000 * 60 * 60 * 24));
            if (differenceBetweenDays > 1) {
                paymentDebt = paymentRent;
                paymentRent = 0;
                if (differenceBetweenDays > 30) {
                    paymentFine = paymentDebt / 10;
                }
            }
            paid = true;
        }
        dao_payment.update(this);
        return this;
    }
    @Override
    public String toString() {
        if (paid) {
            return flat.getNumber() + "," + flat.getResident().getName() + "," + paymentRent + "," + paymentDebt + "," + paymentFine + "," + paymentPerDay.getTime() + ","
                    + paymentDay.getTime() + "," + paid + "," + differenceBetweenDays;
        }
        return flat.getNumber()+","+flat.getResident().getName()+","+paymentRent+","+paymentDebt+","+paymentFine+","+paymentPerDay.getTime()+","
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
        dao_payment.create(this);
    }
    public Payment(int paymentRent, int paymentDebt, int paymentFine, int differenceBetweenDays) {
        this.paymentRent = paymentRent;
        this.paymentDebt = paymentDebt;
        this.paymentFine = paymentFine;
        this.differenceBetweenDays = differenceBetweenDays;
        dao_payment.create(this);
    }
    public Payment(Flat flat,boolean paid, int paymentRent, int paymentDebt, int paymentFine, Calendar paymentPerDay, Calendar paymentDay) {
        this.paid = paid;
        this.flat = flat;
        this.paymentRent = paymentRent;
        this.paymentDebt = paymentDebt;
        this.paymentFine = paymentFine;
        this.paymentDay = paymentDay;
        this.paymentPerDay = paymentPerDay;
        dao_payment.create(this);
    }
    public String toStringg() {
        return flat.getNumber()+","+paymentRent+","+paymentDebt+","+paymentFine+"\n";
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
