package com.aimprosoft.dao;

import com.aimprosoft.connector.ConnectionPool;
import com.aimprosoft.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDaoImpl implements GenericDao<Employee> {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int create(Employee employee) {
        String query = "INSERT INTO employees (surname, email, dateOfBirth, salary) VALUES (?, ?, ?, ?)";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getSurname());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setObject(3, employee.getDateOfBirth());
            preparedStatement.setBigDecimal(4, employee.getSalary());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error while saving " + employee + " to DB. " + e);
        }
        return 0;
    }

    @Override
    public Employee read(int id) {
        String query = "SELECT * FROM employees WHERE empl_id = ?";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Employee(id,
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getObject("dateOfBirth", LocalDate.class),
                        resultSet.getBigDecimal("salary"));
            }
        } catch (Exception e) {
            System.out.println("Error while reading employee from the DB. " + e);
        }
        return null;
    }

    @Override
    public void update(Employee employee) {
        String query = "UPDATE employees SET surname = ?, email = ?, dateOfBirth = ?, salary = ? WHERE empl_id = ?";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getSurname());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setObject(3, employee.getDateOfBirth());
            preparedStatement.setBigDecimal(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getEmplId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while updating " + employee + " in the DB. " + e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM employees WHERE empl_id = ?";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while removing employee from the DB. " + e);
        }
    }

    @Override
    public List<Employee> listOfAll() {
        throw new UnsupportedOperationException();
    }
}
