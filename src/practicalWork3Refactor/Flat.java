package practicalWork3Refactor;

public class Flat {
    private int area;
    private int rent;
    private Resident resident;
    public int getRent() { return rent; }
    public Resident getResident() { return resident; }
    public void setResident(Resident resident) { this.resident = resident; }
    public Flat(int area) {
        this.area = area;
        this.rent = (area * 20);
    }
}
