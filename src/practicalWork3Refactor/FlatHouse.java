package practicalWork3Refactor;

public class FlatHouse {
    public Flat[] flats;
    public void registerResident(Resident resident, Flat flat) {
        flat.setResident(resident);
    }
    public void unregisterResident(Flat flat) {
        flat.setResident(null);
    }
    public FlatHouse(int floors, int flats) {
        this.flats = new Flat[floors*flats];
        for(int i = 0; i < floors*flats; i++) {
            this.flats[i] = new Flat(50);
        }
    }
}
