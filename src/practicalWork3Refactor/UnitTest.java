package practicalWork3Refactor;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitTest {
    @org.junit.jupiter.api.Test
    void registerAndUnregisterResident() {
        Resident resident = new Resident("Mykola");
        FlatHouse flatHouse = new FlatHouse(1,1);
        boolean isRegistered = flatHouse.registerResident(resident,1);
        assertTrue(isRegistered);
        boolean isUnregistered = flatHouse.unregisterResident(1);
        assertTrue(isUnregistered);
    }
    @ParameterizedTest
    @MethodSource ("generateRentData")
    void makeRent(FlatHouse flatHouse, ArrayList<Payment> payments) {
        for(int i = 0; i < flatHouse.paymentList.size(); i++) {
            Payment expectedPayment = payments.get(i);
            Payment actualPayment = flatHouse.paymentList.get(i);
            assertEquals(expectedPayment.getRent(),actualPayment.getRent());
        }
    }
    static Stream<Arguments> generateRentData() {
        ArrayList<Payment> payments = new ArrayList<Payment>();
        payments.add(new Payment(2000,0,0,0));
        Resident resident = new Resident("Mykola");
        FlatHouse flatHouse = new FlatHouse(new Flat[] {new Flat(10,1)});
        flatHouse.registerResident(resident,1);
        //flatHouse.makeRent(new GregorianCalendar());
        return Stream.of(
                Arguments.of(flatHouse, payments)
        );
    }
    //@org.junit.jupiter.api.Test
    @ParameterizedTest
    @MethodSource ("generateDataPay")
    void pay(int flatArea, Calendar dayPerDay, Payment expectedPayment) {
        Flat[] flats = {new Flat(flatArea,1)};
        FlatHouse flatHouse = new FlatHouse(flats);
        Resident resident = new Resident("Resident");
        flatHouse.registerResident(resident,1);
        //flatHouse.makeRent(dayPerDay);
        Payment actualPayment = resident.paymentList.get(0).pay();
        expectedPayment.setData(actualPayment);
        assertEquals(expectedPayment.getRent(),actualPayment.getRent());
        assertEquals(expectedPayment.getDebt(),actualPayment.getDebt());
        assertEquals(expectedPayment.getFine(),actualPayment.getFine());
        assertEquals(expectedPayment.getDifferenceBetweenDays(),actualPayment.getDifferenceBetweenDays());
    }
    static Stream<Arguments> generateDataPay() {
        return Stream.of(
                Arguments.of(10,new GregorianCalendar(2021,0,1),new Payment(0,2000,200,87)),
                Arguments.of(10,new GregorianCalendar(2021,2,10),new Payment(0,2000,0,19))
        );
    }
}