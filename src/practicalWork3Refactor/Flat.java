package practicalWork3Refactor;

public class Flat {
    private int area;
    private int rent;
    private int index;
    private Resident resident;
    public int getArea() {return area;}
    public int getIndex() { return index; }
    public void setIndex(int value) {this.index = value;}
    public int getRent() { return rent; }
    public Resident getResident() { return resident; }
    public void setResident(Resident resident) { this.resident = resident; }
    public void setArea(int area) {
        this.area = area;
        this.rent = area * 200;
    }
    public Flat(int area, int index) {
        this.index = index;
        this.area = area;
        this.rent = (area * 200);
    }
    public Flat(int area) {
        this.index = -1;
        this.area = area;
        this.rent = (area * 200);
    }
}
