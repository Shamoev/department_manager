package com.aimprosoft.controller;

import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;
import com.aimprosoft.validation.EmployeeOval;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddModEmplServlet extends AbstractInitialServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String url = "/employees";
        String message = "";
        HttpSession session = req.getSession();
        Department department = (Department) session.getAttribute("department");
        Integer departmentId = 0;
        if (department != null) {
            departmentId = department.getDepId();
        }
        Employee employeeToModify = (Employee) session.getAttribute("employee");
        Integer employeeToModifyId = null;
        if (employeeToModify != null) {
            employeeToModifyId = employeeToModify.getEmplId();
        }
        EmployeeOval employeeOval = getEmployeeOval(req);
        Validator validator = new Validator();
        List<ConstraintViolation> validate = validator.validate(employeeOval);
        if (validate.size() == 0) {
            Employee employee = new Employee();
            if (employeeToModifyId != null) {
                employee.setEmplId(employeeToModifyId);
            }
            fillEmplFromEmplOval(employeeOval, employee);
            if (!employeeService.isEmplEmailTaken(employee)) {
                if (employee.getEmplId() != 0) {
                    employeeService.modifyEmployee(employee);
                } else {
                    employeeService.addEmployeeToDep(employee, departmentId);
                }
            } else {
                message = "Email is already taken";
                req.setAttribute("employee", employeeOval);
                url = "/JSP/addmodempl.jsp";
            }
        } else {
            message = getViolationMessages(validate);
            req.setAttribute("employee", employeeOval);
            url = "/JSP/addmodempl.jsp";
        }
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    private void fillEmplFromEmplOval(EmployeeOval employeeOval, Employee employee) {
        employee.setSurname(employeeOval.getSurname());
        employee.setEmail(employeeOval.getEmail());
        LocalDate localDate = LocalDate.parse(employeeOval.getDateOfBirth(),
                DateTimeFormatter.ISO_LOCAL_DATE);
        employee.setDateOfBirth(localDate);
        employee.setSalary(new BigDecimal(employeeOval.getSalary()));
    }

    private EmployeeOval getEmployeeOval(HttpServletRequest req) {
        return new EmployeeOval(req.getParameter("surname"),
                req.getParameter("email"),
                req.getParameter("dateOfBirth"),
                req.getParameter("salary"));
    }
}