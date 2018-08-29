package com.aimprosoft.dao;

import com.aimprosoft.connector.ConnectionPool;
import com.aimprosoft.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements GenericDao<Department> {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int create(Department department) {
        String query = "INSERT INTO departments (depNumber, depName) VALUES (?, ?)";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, department.getDepNumber());
            preparedStatement.setString(2, department.getDepName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error while saving " + department + " to DB. " + e);
        }
        return 0;
    }

    @Override
    public Department read(int id) {
        String query = "SELECT * FROM departments WHERE dep_id = ?";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Department(id,
                        resultSet.getInt("depNumber"),
                        resultSet.getString("depName"));
            }
        } catch (Exception e) {
            System.out.println("Error while reading department from the DB. " + e);
        }
        return null;
    }

    @Override
    public void update(Department department) {
        String query = "UPDATE departments SET depNumber = ?, depName = ? WHERE dep_id = ?";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, department.getDepNumber());
            preparedStatement.setString(2, department.getDepName());
            preparedStatement.setInt(3, department.getDepId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while updating " + department + " in the DB. " + e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM departments WHERE dep_id = ?";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while removing department from the DB. " + e);
        }
    }

    @Override
    public List<Department> listOfAll() {
        String query = "SELECT * FROM departments";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Department> result = new ArrayList<>();
            while (resultSet.next()) {
                Department department = new Department(resultSet.getInt("dep_id"),
                        resultSet.getInt("depNumber"),
                        resultSet.getString("depName"));
                result.add(department);
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error while reading departments from the DB. " + e);
        }
        return null;
    }
}
