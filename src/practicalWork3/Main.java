package practicalWork3;

public class Main {

    public static void main(String[] args) {
        Flat[] flats = {new Flat(44), new Flat(52), new Flat(36)};
        FlatHouse flatHouse = new FlatHouse(flats);
        Resident resident1 = new Resident("Mykola");
        Resident resident2 = new Resident("Anton");
        Resident resident3 = new Resident("John");
        flatHouse.flats[0].register(resident1);
        flatHouse.flats[1].register(resident2);
        flatHouse.flats[2].register(resident3);
        flatHouse.creditRent();
        flatHouse.reports.add(resident1.makeReport());
        flatHouse.reports.add(resident2.makeReport());
        flatHouse.reports.add(resident3.makeReport());
        PaymentReportRentComparator comp1 = new PaymentReportRentComparator();
        PaymentReportNameComparator comp2 = new PaymentReportNameComparator();
        PaymentReportIndexComparator comp3 = new PaymentReportIndexComparator();
        flatHouse.showReports();
        flatHouse.reports.sort(comp1);
        System.out.println('\n');
        flatHouse.showReports();
        flatHouse.reports.sort(comp2);
        System.out.println('\n');
        flatHouse.showReports();
        flatHouse.reports.sort(comp3);
        System.out.println('\n');
        flatHouse.showReports();
    }
}
