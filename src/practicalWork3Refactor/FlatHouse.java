package practicalWork3Refactor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FlatHouse {
    public Flat[] flats;
    public ArrayList<Payment> paymentList;
    public void registerResident(Resident resident, Flat flat) {
        flat.setResident(resident);
    }
    public void unregisterResident(Flat flat) {
        flat.setResident(null);
    }
    public void makeRent(Calendar calendar) {
        for (Flat flat:
             flats) {
            if (flat.getResident() != null) {
                paymentList.add(new Payment(flat, calendar));
            }
        }
    }
    public FlatHouse(int floors, int flats) {
        paymentList = new ArrayList<Payment>();
        this.flats = new Flat[floors*flats];
        for(int i = 0; i < floors*flats; i++) {
            this.flats[i] = new Flat(50, i+1);
        }
    }
}
