package practicalWork3Refactor;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.geom.GeneralPath;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitTest {

/*    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }*/

    //@org.junit.jupiter.api.Test
    @ParameterizedTest
    @MethodSource ("generateDataPay")
    void pay(int flatArea, Calendar dayPerDay, Payment expectedPayment) {
        Flat[] flats = {new Flat(flatArea,1)};
        FlatHouse flatHouse = new FlatHouse(flats);
        Resident resident = new Resident("Resident");
        flatHouse.registerResident(resident,1);
        flatHouse.makeRent(dayPerDay);
        Payment actualPayment = resident.paymentList.get(0).pay();
        expectedPayment.setData(actualPayment);
        assertEquals(expectedPayment.getRent(),actualPayment.getRent());
        assertEquals(expectedPayment.getDebt(),actualPayment.getDebt());
        assertEquals(expectedPayment.getFine(),actualPayment.getFine());
        assertEquals(expectedPayment.getDifferenceBetweenDays(),actualPayment.getDifferenceBetweenDays());
    }
    static Stream<Arguments> generateDataPay() {
        return Stream.of(
                Arguments.of(10,new GregorianCalendar(2021,0,1),new Payment(0,2000,200,68)),
                Arguments.of(10,new GregorianCalendar(2021,2,10),new Payment(2000,0,0,0))
        );
    }
}