package practicalWork3Refactor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class FlatHouse {
    public Flat[] flats;
    public ArrayList<Payment> paymentList;
    public boolean registerResident(Resident resident, int index) {
        try {
            flats[index-1].setResident(resident);
            flats[index-1].update();
            return true;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Flat with this index doesn't exist!");
            return false;
        }
    }
    public Flat getFlat(int index) {
        try {
            return flats[index-1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Flat with this index doesn't exist!");
            return null;
        }
    }
    public void removeFlat(int index) {
        try {
            flats[index-1] = null;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Flat with this index doesn't exist!");
        }
    }
    public boolean unregisterResident(int index) {
        try {
            flats[index - 1].setResident(null);
            flats[index-1].update();
            return true;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Flat with this index doesn't exist!");
            return false;
        }
    }
    public ArrayList<Payment> makeRent(int year, int month) {
        Calendar calendar = new GregorianCalendar(year,month,1);
        for (Flat flat: flats) {
            if (flat.getResident() != null) {
                Payment payment = new Payment(flat, calendar);
                paymentList.add(payment);
                flat.getResident().paymentList.add(payment);
            }
        }
        return paymentList;
    }
    public FlatHouse(int floors, int flats) {
        paymentList = new ArrayList<Payment>();
        this.flats = new Flat[floors*flats];
        for(int i = 0; i < floors*flats; i++) {
            this.flats[i] = new Flat(50, i+1);
        }
    }
    public FlatHouse(Flat[] flats) {
        this.flats = flats;
        paymentList = new ArrayList<Payment>();
    }
    public void showPaidList(PaymentSort obj) {
        paymentList.sort(obj);
        for(Payment payment : paymentList) {
            if (payment.getPaid()) {
                System.out.println(payment.toString());
            }
        }
    }
}
