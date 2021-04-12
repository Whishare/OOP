package practicalWork3Refactor.databases;

import practicalWork3Refactor.Flat;
import practicalWork3Refactor.Resident;
import practicalWork3Refactor.databases.DAO;
import practicalWork3Refactor.databases.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class DAO_Flat implements DAO<Flat> {
    @Override
    public Flat create(Flat entity) {
        //INSERT INTO Flat (area, residentIndex) VALUES (?, ?);
        try {
            String sql = "INSERT INTO Flat (area, residentName) VALUES (?, ?);";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            statement.setInt(1,entity.getArea());
            try {
                statement.setString(2, entity.getResident().getName());
            }
            catch(NullPointerException ex) {
                statement.setNull(2,0);
            }
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            entity.setIndex(key);
            System.out.println(entity.getIndex());
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return entity;
    }

    @Override
    public boolean update(Flat entity) {
        //UPDATE Flat SET area = 'area', residentIndex = 'residentIndex' WHERE "index" = 'index'
        boolean result = false;
        try {
            String sql = "UPDATE Flat SET area = ?, residentName = ? WHERE \"index\" = ?;";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            statement.setInt(1,entity.getArea());
            try {
                statement.setString(2, entity.getResident().getName());
            }
            catch(NullPointerException ex) {
                statement.setNull(2,0);
            }
            statement.setInt(3,entity.getIndex());
            result = statement.execute();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        //DELETE FROM Flat WHERE "index" = 'index'
        boolean result = false;
        try {
            String sql = "DELETE FROM Flat WHERE \"index\" = ?";
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
    public List<Flat> find(Flat entity) {
        //SELECT * FROM Flat Where index = x
        List<Flat> result = new ArrayList<Flat>();
        try {
            String sql = "SELECT * FROM Flat Where \"index\" = ?";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            statement.setInt(1,entity.getIndex());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int index = resultSet.getInt(1);
                int area = resultSet.getInt(2);
                String name = resultSet.getString(3);
                Flat tempFlat = new Flat(area);
                tempFlat.setResident(new Resident(name));
                tempFlat.setIndex(index);
                result.add(tempFlat);
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Flat> findAll() {
        //SELECT * FROM Flat
        List<Flat> result = new ArrayList<Flat>();
        try {
            String sql = "SELECT * FROM Flat";
            Statement statement = DatabaseConnection.getInstance().connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int index = resultSet.getInt(1);
                int area = resultSet.getInt(2);
                String name = resultSet.getString(3);
                Flat tempFlat = new Flat(area);
                tempFlat.setResident(new Resident(name));
                tempFlat.setIndex(index);
                result.add(tempFlat);
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        DAO_Flat dao = new DAO_Flat();
        List<Flat> flats = dao.findAll();
        List<Flat> flatsInAll = dao.find(flats.get(0));
        flatsInAll.get(0).setResident(new Resident("Mykola"));
        flatsInAll.get(0).setArea(78);
        dao.update(flatsInAll.get(0));
    }
}
