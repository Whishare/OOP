package practicalWork3Refactor;

import practicalWork3Refactor.databases.DAO_Flat;
import practicalWork3Refactor.databases.DAO_Payment;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Resident resident = new Resident("Mykola");
        FlatHouse flatHouse = new FlatHouse(1,2);
        flatHouse.registerResident(resident,1);
        flatHouse.makeRent(2021,1);
        flatHouse.getFlat(1).getResident().pay();
        flatHouse.getFlat(2).setArea(88);
    }
}
