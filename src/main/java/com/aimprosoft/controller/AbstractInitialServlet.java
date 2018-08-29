package com.aimprosoft.controller;

import com.aimprosoft.service.DepartmentService;
import com.aimprosoft.service.EmployeeService;
import net.sf.oval.ConstraintViolation;

import javax.servlet.http.HttpServlet;
import java.util.List;

public abstract class AbstractInitialServlet extends HttpServlet {
    final static DepartmentService departmentService = new DepartmentService();
    final static EmployeeService employeeService = new EmployeeService();

    public String getViolationMessages(List<ConstraintViolation> validate) {
        String result = "";
        for (ConstraintViolation constraintViolation : validate) {
            result = String.format("%s %s <br>", result, constraintViolation.getMessage());
        }
        return result;
    }
}
