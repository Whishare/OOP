/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whishare.flathouse_application;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 *
 * @author nicol
 */
public class Payment {
    public int ID;
    public int flatID;
    public boolean paid;
    public int rent;
    public int debt;
    public int fine;
    public LocalDate forDay;
    public LocalDate payDay;

    public Payment(int ID, int flatID, boolean paid, int rent, LocalDate forDay) {
        this.ID = ID;
        this.flatID = flatID;
        this.paid = paid;
        this.rent = rent;
        this.debt = 0;
        this.fine = 0;
        this.forDay = forDay;
    }

    public Payment(int ID, int flatID, boolean paid, int rent, int debt, int fine, LocalDate forDay, LocalDate payDay) {
        this.ID = ID;
        this.flatID = flatID;
        this.paid = paid;
        this.rent = rent;
        this.debt = debt;
        this.fine = fine;
        this.forDay = forDay;
        this.payDay = payDay;
    }
    
    
    public void pay() {
        long differenceBetweenDays;
        if (!paid) {
            this.payDay = LocalDate.now();
            differenceBetweenDays = Math.abs(DAYS.between(payDay, forDay));
            System.out.println(differenceBetweenDays);
            if (differenceBetweenDays > 1) {
                debt = rent;
                rent = 0;
                if (differenceBetweenDays > 30)
                    fine = (int)debt/10;
            }
            paid = true;
        }
    }
    
    public void refreshData() {
        long differenceBetweenDays;
        if (!paid) {
            this.payDay = LocalDate.now();
            differenceBetweenDays = Math.abs(DAYS.between(payDay, forDay));
            System.out.println(differenceBetweenDays);
            if (differenceBetweenDays > 1) {
                debt = rent;
                //rent = 0;
                if (differenceBetweenDays > 30)
                    fine = (int)debt/10;
            }
        }
    }
}
