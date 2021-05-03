/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whishare.flathouse_application;

import java.util.List;

/**
 *
 * @author nicol
 */
public class FlatHouse {
    public static List<Flat> flats;
    public static List<Payment> payments;
    public static List<Resident> residents;
    
    public static FlathouseGUI instance;
    
    private static DAO_Flat flat = new DAO_Flat();
    private static DAO_Payment payment = new DAO_Payment();
    
    public static void refresh() {
        flats = flat.findAll();
        payments = payment.findAll();
    }
    
    public static void register(int flatID, int residentID) {
        for(Flat f : flats) {
            if (f.ID == flatID) {
                f.resident = new Resident(residentID);
                flat.update(f);
            }
        }
        refresh();
        instance.refresh();
    }
    
    public static void unregister(int flatID) {
        for(Flat f : flats) {
            if (f.ID == flatID) {
                f.resident = null;
                flat.update(f);
            }
        }
        refresh();
        instance.refresh();
    }
    
    public static void pay(int paymentID) {
        for(Payment p : payments) {
            if (p.ID == paymentID) {
                p.pay();
                payment.update(p);
            }
        }
        refresh();
        instance.refresh();
    }
    
    public static void rent() {
        refresh();
        for(Flat f : flats) {
            if (f.resident != null) {
                Payment p = new Payment(1,f.ID,false,f.rent,instance.getDateOfRent());
                payments.add(p);
                payment.create(p);
            }
        }
        refresh();
        instance.refresh();
    }
}
