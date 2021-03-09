package practicalWork3Refactor;

import java.util.ArrayList;

public class Resident {
    private String name;
    public ArrayList<Payment> paymentList;
    public String getName() {
        return name;
    }
    public Resident(String name) {
        this.name = name;
        paymentList = new ArrayList<Payment>();
    }
}
