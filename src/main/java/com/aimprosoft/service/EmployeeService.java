package com.aimprosoft.service;

import com.aimprosoft.dao.DepEmplManager;
import com.aimprosoft.dao.EmployeeDaoImpl;
import com.aimprosoft.dao.GenericDao;
import com.aimprosoft.model.Employee;

import java.util.List;

public class EmployeeService {

    private static final GenericDao<Employee> employeeDao = new EmployeeDaoImpl();

    private static final DepEmplManager depEmplManager = new DepEmplManager();

    public EmployeeService() {
    }

    public List<Employee> getAllEmplsInDep(int departmentId) {
        return depEmplManager.getAllEmplsInDep(departmentId);
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeDao.read(employeeId);
    }

    public boolean isEmplEmailTaken(Employee employee) {
        return depEmplManager.isEmplEmailTaken(employee);
    }

    public void modifyEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    public void addEmployeeToDep(Employee employee, int departmentId) {
        depEmplManager.addEmplToDep(employee, departmentId);
    }

    public void removeEmployee(int employeeId) {
        employeeDao.delete(employeeId);
    }
}
