package practicalWork3Refactor;

import practicalWork3Refactor.databases.DAO_Flat;

public class Flat {
    private int area;
    private int rent;
    private int number;
    private DAO_Flat dao_flat;
    private Resident resident;
    public int getArea() {return area;}
    public int getNumber() { return number; }
    public void setNumber(int value) {this.number = value;}
    public int getRent() { return rent; }
    public Resident getResident() { dao_flat.update(this); return resident; }
    public void setResident(Resident resident) { dao_flat.update(this); this.resident = resident; }
    public void setArea(int area) {
        this.area = area;
        this.rent = area * 200;
        dao_flat.update(this);
    }
    public void delete() {
        dao_flat.delete(number);
    }
    public void update() {
        dao_flat.update(this);
    }
    public Flat(int area, int index) {
        this.number = index;
        this.area = area;
        this.rent = (area * 200);
        dao_flat = new DAO_Flat();
        dao_flat.create(this);

    }
    public Flat(int area) {
        this.number = -1;
        this.area = area;
        this.rent = (area * 200);
    }
}
