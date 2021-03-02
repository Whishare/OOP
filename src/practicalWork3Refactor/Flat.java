package practicalWork3Refactor;

public class Flat {
    private int area;
    private int rent;
    private int index;
    private Resident resident;
    public int getIndex() { return index; }
    public int getRent() { return rent; }
    public Resident getResident() { return resident; }
    public void setResident(Resident resident) { this.resident = resident; }
    public Flat(int area, int index) {
        this.index = index;
        this.area = area;
        this.rent = (area * 20);
    }
}
