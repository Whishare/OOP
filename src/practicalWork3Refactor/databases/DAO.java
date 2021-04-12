package practicalWork3Refactor.databases;

import practicalWork3Refactor.Flat;
import practicalWork3Refactor.Payment;
import practicalWork3Refactor.Resident;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface DAO<T>{
    T create(T entity);
    boolean update(T entity);
    boolean delete(int id);
    List<T> find(T entity);
    List<T> findAll();
}
