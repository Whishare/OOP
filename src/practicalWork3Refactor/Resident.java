package practicalWork3Refactor;

import java.util.ArrayList;

public class Resident {
    private static int realIndex = 0;
    private String name;
    public int index;
    public ArrayList<Payment> paymentList;
    public String getName() {
        return name;
    }
    public Resident(String name) {
        this.index = realIndex++;
        this.name = name;
        paymentList = new ArrayList<Payment>();
    }
    public void pay() {
        for(Payment p : paymentList) {
            p.pay();
        }
    }
}
