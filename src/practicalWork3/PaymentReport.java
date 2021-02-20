package practicalWork3;

import java.util.Comparator;

public class PaymentReport{
    public int rent;
    public int debt;
    public int fine;
    public int index;
    public String name;
    public PaymentReport(int rent, int debt, int fine, int index, String name) {
        this.rent = rent;
        this.debt = debt;
        this.fine = fine;
        this.index = index;
        this.name = name;
    }
    public void showReport() {
        System.out.println(rent+" , "+debt+" , "+fine+" , "+index+" : "+name);
    }
}
class PaymentReportRentComparator implements Comparator<PaymentReport> {
    @Override
    public int compare(PaymentReport o1, PaymentReport o2) {
        return Integer.compare(o1.rent, o2.rent);
    }
}
class PaymentReportDebtComparator implements Comparator<PaymentReport> {
    @Override
    public int compare(PaymentReport o1, PaymentReport o2) {
        return Integer.compare(o1.debt, o2.debt);
    }
}
class PaymentReportFineComparator implements Comparator<PaymentReport> {
    @Override
    public int compare(PaymentReport o1, PaymentReport o2) {
        return Integer.compare(o1.fine, o2.fine);
    }
}
class PaymentReportIndexComparator implements Comparator<PaymentReport> {
    @Override
    public int compare(PaymentReport o1, PaymentReport o2) {
        return Integer.compare(o1.index, o2.index);
    }
}
class PaymentReportNameComparator implements Comparator<PaymentReport> {
    @Override
    public int compare(PaymentReport o1, PaymentReport o2) {
        return o1.name.compareTo(o2.name);
    }
}