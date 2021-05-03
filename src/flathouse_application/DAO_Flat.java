/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whishare.flathouse_application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicol
 */
public class DAO_Flat {
    public List<Flat> findAll() {
        List<Flat> result = new ArrayList<Flat>();
        try {
            String sql = "SELECT * FROM Flat";
            Statement statement = DatabaseConnection.getInstance().connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int ID = resultSet.getInt(1);
                int area = resultSet.getInt(2);
                int rent = resultSet.getInt(3);
                int residentID = resultSet.getInt(4);
                Flat f;
                if (residentID != 0) {
                    Resident r = new Resident(residentID);
                    f = new Flat(ID,area,rent,r.ID);
                } else {
                    f = new Flat(ID,area,rent);
                }
                result.add(f);
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }
    public void update(Flat entity) {
        boolean result = false;
        try {
            String sql = "UPDATE Flat SET ResidentID = ? WHERE \"ID\" = ?;";
            PreparedStatement statement = DatabaseConnection.getInstance().connection.prepareStatement(sql);
            if (entity.resident != null)
                statement.setInt(1,entity.resident.ID);
            else 
                statement.setNull(1, 0);
            statement.setInt(2,entity.ID);
            result = statement.execute();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
