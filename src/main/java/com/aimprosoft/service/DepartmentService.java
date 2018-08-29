package com.aimprosoft.service;

import com.aimprosoft.dao.DepEmplManager;
import com.aimprosoft.dao.DepartmentDaoImpl;
import com.aimprosoft.dao.GenericDao;
import com.aimprosoft.model.Department;

import java.util.List;

public class DepartmentService {

    private static final GenericDao<Department> departmentDao = new DepartmentDaoImpl();

    private static final DepEmplManager depEmplManager = new DepEmplManager();

    public DepartmentService() {
    }

    public void addDepartment(Department department) {
        departmentDao.create(department);
    }

    public Department getDepartmentById(int departmentId) {
        return departmentDao.read(departmentId);
    }

    public void modifyDepartment(Department department) {
        departmentDao.update(department);
    }

    public void removeDepWithEmpls(int departmentId) {
        DepEmplManager.removeDepWithEmpls(departmentId);
    }

    public List<Department> getAllDepartmentsList() {
        return departmentDao.listOfAll();
    }

    public boolean isDepNameTaken(Department department) {
        return depEmplManager.isDepNameTaken(department);
    }
}
