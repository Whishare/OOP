package practicalWork3;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Flat {
    private int area;
    private int rent;
    public int index;
    public int curRent;
    public int curDebt;
    public int curFine;
    public Resident owner;
    public TimeInterval time;
    public int getRent() {
        return rent;
    }
    public void register(Resident owner) {
        this.owner = owner;
        this.owner.flat = this;
        time = new TimeInterval();
    }
    public void unregister() {
        this.owner = null;
        time = null;
    }
    public void creditRent() {
        Calendar date = new GregorianCalendar();
        if (owner != null && date.get(Calendar.DAY_OF_MONTH) == 1) {
            curRent += rent;
            //time.confirmRent();
        }
    }
    public Flat(int area) {
        this.area = area;
        rent = area*200;
    }
}
