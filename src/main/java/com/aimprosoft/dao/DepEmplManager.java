package com.aimprosoft.dao;

import com.aimprosoft.connector.ConnectionPool;
import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepEmplManager {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final GenericDao<Department> departmentDao = new DepartmentDaoImpl();

    private static final GenericDao<Employee> employeeDao = new EmployeeDaoImpl();

    public DepEmplManager() {
    }

    public void addEmplToDep(Employee employee, int departmentId) {
        int employeeId = employeeDao.create(employee);
        if (employeeId != 0) {
            linkEmplWithDepartment(departmentId, employeeId);
        }
    }

    public static void removeDepWithEmpls(int departmentId) {
        removeEmplsOfDep(departmentId);
        departmentDao.delete(departmentId);
    }

    private static void removeEmplsOfDep(int departmentId) {
        String query = "DELETE FROM employees WHERE empl_id IN " +
                "(SELECT empl_id FROM dep_empl WHERE dep_id = ?);";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, departmentId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while removing employees of the department from the DB. " + e);
        }
    }

    private void linkEmplWithDepartment(int departmentId, int employeeId) {
        String query = "INSERT INTO dep_empl (dep_id, empl_id) VALUES (?, ?)";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, departmentId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while linking employee with department. " + e);
        }
    }

    public boolean isDepNameTaken(Department department) {
        String query = "SELECT * FROM departments WHERE depName = ?";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, department.getDepName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int dep_id = resultSet.getInt("dep_id");
                if (dep_id != department.getDepId()) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error while searching departments.depName in the DB. " + e);
        }
        return false;
    }

    public List<Employee> getAllEmplsInDep(int departmentId) {
        String query = "SELECT * FROM employees WHERE empl_id IN " +
                "(SELECT empl_id FROM dep_empl WHERE dep_id = ?);";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> result = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("empl_id"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getObject("dateOfBirth", LocalDate.class),
                        resultSet.getBigDecimal("salary"));
                result.add(employee);
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error while reading employees from the DB. " + e);
        }
        return null;
    }

    public boolean isEmplEmailTaken(Employee employee) {
        String query = "SELECT * FROM employees WHERE email = ?";
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int empl_id = resultSet.getInt("empl_id");
                if (empl_id != employee.getEmplId()) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error while searching employee.email in the DB. " + e);
        }
        return false;
    }
}
