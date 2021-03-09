package practicalWork3Refactor;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        Resident resident = new Resident("Mykola");
        FlatHouse flatHouse = new FlatHouse(1,1);
        flatHouse.registerResident(resident,1);
        flatHouse.makeRent(new GregorianCalendar(2021, Calendar.FEBRUARY,1));
        resident.paymentList.get(0).pay();
        flatHouse.showPaidList(new PaymentSort());
    }
}
