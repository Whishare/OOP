/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whishare.flathouse_application;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicol
 */
public class DAO_Payment {
    
    public void create(Payment entity) {
        try {
            String sql = "INSERT INTO Payment (FlatID, Paid, Rent, Debt, Fine, ForDay, PayDay) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            statement.setInt(1,entity.flatID);
            statement.setBoolean(2,entity.paid);
            statement.setInt(3,entity.rent);
            statement.setInt(4,entity.debt);
            statement.setInt(5,entity.fine);
            statement.setDate(6,Date.valueOf(entity.forDay));
            statement.setDate(7,null);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            entity.ID = key;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public boolean update(Payment entity) {
        boolean result = false;
        try {
            String sql = "UPDATE Payment SET FlatID = ?,Paid = ?,Rent = ?,Debt = ?,Fine = ?,PayDay = ? WHERE ID = ?";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            statement.setInt(1,entity.flatID);
            statement.setBoolean(2,entity.paid);
            statement.setInt(3,entity.rent);
            statement.setInt(4,entity.debt);
            statement.setInt(5,entity.fine);
            try {
            statement.setDate(6,Date.valueOf(entity.payDay));
            }
            catch (NullPointerException ex) {
                statement.setDate(6,null);
            }
            statement.setInt(7,entity.ID);
            result = statement.execute();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public List<Payment> findAll() {
        List<Payment> result = new ArrayList<Payment>();
        try {
            String sql = "SELECT * FROM Payment";
            Statement statement = DatabaseConnection.getInstance().connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                int flatID = resultSet.getInt(2);
                boolean paid = resultSet.getBoolean(3);
                int rent = resultSet.getInt(4);
                int debt = resultSet.getInt(5);
                int fine = resultSet.getInt(6);
                Date sqlForDay =resultSet.getDate(7);
                Date sqlPayDay = resultSet.getDate(8);
                LocalDate forDay = sqlForDay.toLocalDate();
                LocalDate payDay;
                try {
                    payDay = sqlPayDay.toLocalDate();
                }
                catch (NullPointerException ex) {
                    payDay = null;
                }
                Payment p = new Payment(id,flatID,paid,rent,debt,fine,forDay,payDay);
                result.add(p);
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }
}
