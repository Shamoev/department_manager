package com.aimprosoft.controller;

import com.aimprosoft.model.Department;
import com.aimprosoft.validation.DepartmentOval;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddModDepServlet extends AbstractInitialServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String url = "/departments";
        String message = "";
        HttpSession session = req.getSession();
        Department departmentToModify = (Department) session.getAttribute("department");
        Integer departmentToModifyId = null;
        if (departmentToModify != null) {
            departmentToModifyId = departmentToModify.getDepId();
        }
        DepartmentOval departmentOval = getDepartmentOval(req);
        Validator validator = new Validator();
        List<ConstraintViolation> validate = validator.validate(departmentOval);
        if (validate.size() == 0) {
            Department department = new Department();
            if (departmentToModifyId != null) {
                department.setDepId(departmentToModifyId);
            }
            fillDepFromDepOval(departmentOval, department);
            if (!departmentService.isDepNameTaken(department)) {
                if (department.getDepId() != 0) {
                    departmentService.modifyDepartment(department);
                } else {
                    departmentService.addDepartment(department);
                }
            } else {
                message = "Department name is already taken";
                req.setAttribute("department", departmentOval);
                url = "/JSP/addmoddep.jsp";
            }
        } else {
            message = getViolationMessages(validate);
            req.setAttribute("department", departmentOval);
            url = "/JSP/addmoddep.jsp";
        }
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    private void fillDepFromDepOval(DepartmentOval departmentOval, Department department) {
        department.setDepNumber(Integer.parseInt(departmentOval.getDepNumber()));
        department.setDepName(departmentOval.getDepName());
    }

    private DepartmentOval getDepartmentOval(HttpServletRequest req) {
        return new DepartmentOval(req.getParameter("depNumber"),
                    req.getParameter("depName"));
    }
}
