package practicalWork3Refactor;

import practicalWork3Refactor.databases.DAO_Flat;

public class Flat {
    private int area;
    private int rent;
    private int index;
    private DAO_Flat dao_flat;
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
        dao_flat.update(this);
    }
    public void delete() {
        dao_flat.delete(index);
    }
    public void update() {
        dao_flat.update(this);
    }
    public Flat(int area, int index) {
        this.index = index;
        this.area = area;
        this.rent = (area * 200);
        dao_flat = new DAO_Flat();
        dao_flat.create(this);

    }
    public Flat(int area) {
        this.index = -1;
        this.area = area;
        this.rent = (area * 200);
    }
}
