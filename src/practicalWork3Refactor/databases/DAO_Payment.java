package practicalWork3Refactor.databases;

import practicalWork3Refactor.Flat;
import practicalWork3Refactor.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DAO_Payment implements DAO<Payment> {

    @Override
    public Payment create(Payment entity) {
        //INSERT INTO Payment (flatIndex, paid, rent, debt, fine, perDay, day) VALUES ('flatIndex','paid','rent','debt','fine','perDay','day');
        try {
            String sql = "INSERT INTO Payment (flatIndex, paid, rent, debt, fine, perDay, day) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            statement.setInt(1,entity.getFlat().getNumber());
            statement.setBoolean(2,entity.getPaid());
            statement.setInt(3,entity.getRent());
            statement.setInt(4,entity.getDebt());
            statement.setInt(5,entity.getFine());
            statement.setDate(6,new java.sql.Date(entity.getPaymentPerDay().getTimeInMillis()));
            statement.setDate(7,new java.sql.Date(entity.getPaymentDay().getTimeInMillis()));
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            entity.id = (key);
            System.out.println(entity.getIndex());
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return entity;
    }

    @Override
    public boolean update(Payment entity) {
        //UPDATE Payment SET flatIndex = 'flatIndex',paid = 'paid',rent = 'rent',debt = 'debt',fine = 'fine',perDay = 'perDay',day = 'day' WHERE id = 'id'
        boolean result = false;
        try {
            String sql = "UPDATE Payment SET flatIndex = ?,paid = ?,rent = ?,debt = ?,fine = ?,perDay = ?,day = ? WHERE id = ?;";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            statement.setInt(1,entity.getFlat().getNumber());
            statement.setBoolean(2,entity.getPaid());
            statement.setInt(3,entity.getRent());
            statement.setInt(4,entity.getDebt());
            statement.setInt(5,entity.getFine());
            statement.setDate(6,new java.sql.Date(entity.getPaymentPerDay().getTimeInMillis()));
            statement.setDate(7,new java.sql.Date(entity.getPaymentDay().getTimeInMillis()));
            statement.setInt(8,entity.id);
            result = statement.execute();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        //DELETE FROM Payment WHERE id = 'id'
        boolean result = false;
        try {
            String sql = "DELETE FROM Payment WHERE id = ?;";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            statement.setInt(1,id);
            result = statement.execute();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Payment> find(Payment entity) {
        //SELECT * FROM Payment WHERE id = ;
        List<Payment> result = new ArrayList<Payment>();
        try {
            String sql = "SELECT * FROM Payment WHERE id = ?";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            statement.setInt(1,entity.id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                int flatIndex = resultSet.getInt(2);
                int rent = resultSet.getInt(3);
                int debt = resultSet.getInt(4);
                int fine = resultSet.getInt(5);
                Date perDay =resultSet.getDate(6);
                Date day = resultSet.getDate(7);
                Calendar calPerDay = new GregorianCalendar();
                calPerDay.setTime(perDay);
                Calendar calDay = new GregorianCalendar();
                calDay.setTime(day);
                Payment temp = new Payment(new Flat(0,flatIndex),true,rent,debt,fine,calPerDay,calDay);
                temp.id = id;
                result.add(temp);
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Payment> findAll() {
        //SELECT * FROM Payment WHERE id = ;
        List<Payment> result = new ArrayList<Payment>();
        try {
            String sql = "SELECT * FROM Payment";
            Statement statement = DatabaseConnection.getInstance().connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                int flatIndex = resultSet.getInt(2);
                int rent = resultSet.getInt(3);
                int debt = resultSet.getInt(4);
                int fine = resultSet.getInt(5);
                Date perDay =resultSet.getDate(6);
                Date day = resultSet.getDate(7);
                Calendar calPerDay = new GregorianCalendar();
                calPerDay.setTime(perDay);
                Calendar calDay = new GregorianCalendar();
                calDay.setTime(day);
                Payment temp = new Payment(new Flat(0,flatIndex),true,rent,debt,fine,calPerDay,calDay);
                temp.id = id;
                result.add(temp);
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        DAO_Payment dao = new DAO_Payment();
/*        List<Flat> flats = dao.findAll();
        List<Flat> flatsInAll = dao.find(flats.get(0));
        flatsInAll.get(0).setResident(new Resident("Mykola"));
        flatsInAll.get(0).setArea(78);
        dao.update(flatsInAll.get(0));*/
        Payment payment = new Payment(new Flat(25,2),false,1200,200,0, new GregorianCalendar(2021,Calendar.MARCH,1), new GregorianCalendar(2021,Calendar.APRIL,6));
        //dao.create(payment);
        List<Payment> payments = dao.findAll();
        List<Payment> payments1 = dao.find(payments.get(2));
        payments1.get(0).getFlat().setNumber(3);
        payments1.get(0).paymentRent = 2554;
        payments1.get(0).paymentDebt = 322;
        payments1.get(0).paymentFine = 0;
        payments1.get(0).paymentPerDay = new GregorianCalendar(2021,Calendar.FEBRUARY,1);
        payments1.get(0).paymentDay = new GregorianCalendar();
        payments1.get(0).paid = false;
        dao.update(payments1.get(0));
        System.out.println(payments1.get(0).toStringg());
    }
}
