package practicalWork3Refactor;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        Resident resident = new Resident("Mykola");
        FlatHouse flatHouse = new FlatHouse(1,1);
        flatHouse.registerResident(resident,flatHouse.flats[0]);
        flatHouse.paymentList.add(new Payment(flatHouse.flats[0], new GregorianCalendar()));
        System.out.println(flatHouse.paymentList.get(0).toString());
    }
}
