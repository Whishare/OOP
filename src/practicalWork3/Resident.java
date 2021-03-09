package practicalWork3;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Resident {
    public String name;
    public Flat flat;
    public PaymentReport makeReport() {
        Calendar curDate = new GregorianCalendar();
        //curDate.add(Calendar.DAY_OF_YEAR,21);
        int differenceBetweenDays = curDate.get(Calendar.DAY_OF_YEAR) - flat.time.payDate.get(Calendar.DAY_OF_YEAR);
        System.out.println(curDate.getTime());
        System.out.println('\n');
        System.out.println(flat.time.payDate.getTime());
        System.out.println('\n');
        System.out.println(differenceBetweenDays);
        if (differenceBetweenDays > 1 && differenceBetweenDays <= 30) {
            flat.curDebt += flat.curRent;
            flat.curRent = 0;
        } else if (differenceBetweenDays > 30) {
            flat.curDebt += flat.curRent;
            flat.curFine += flat.curDebt/10;
            flat.curRent = 0;
        }
        PaymentReport pr = new PaymentReport(flat.curRent, flat.curDebt, flat.curFine, flat.index, name);
        flat.curRent = 0;
        flat.curDebt = 0;
        flat.curFine = 0;
        flat.time.confirmRent();
        return pr;
    }
    public Resident(String name) { this.name = name; }
}
