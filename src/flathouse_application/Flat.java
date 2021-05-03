/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whishare.flathouse_application;

/**
 *
 * @author nicol
 */
public class Flat {
    public int ID;
    public int area;
    public int rent;
    public Resident resident;

    public Flat(int ID, int area, int rent, int residentID) {
        this.ID = ID;
        this.area = area;
        this.rent = rent;
        this.resident = new Resident(residentID);
    }
    
    public Flat(int ID, int area, int rent) {
        this.ID = ID;
        this.area = area;
        this.rent = rent;
        this.resident = null;
    }
}
