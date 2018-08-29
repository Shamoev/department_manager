package com.aimprosoft.controller;

import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EmplsServlet extends AbstractInitialServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        if (action == null) {
            action = "default";
        }
        String employeeIdString = req.getParameter("emplIdRadio");
        Integer employeeId = null;
        if (employeeIdString != null) {
            employeeId = Integer.parseInt(employeeIdString);
        }
        String url = "/JSP/employees.jsp";
        String message = "";

        HttpSession session = req.getSession();
        Department department = (Department) session.getAttribute("department");
        Integer depId = department.getDepId();
        switch (action) {
            case "add":
                session.removeAttribute("employee");
                url = "/JSP/addmodempl.jsp";
                break;
            case "modify":
                if (employeeId != null) {
                    session.setAttribute("employee", employeeService.getEmployeeById(employeeId));
                    url = "/JSP/addmodempl.jsp";
                } else {
                    message = "Select employee to modify";
                }
                break;
            case "remove":
                if (employeeId != null) {
                    employeeService.removeEmployee(employeeId);
                    message = "";
                } else {
                    message = "Select employee to remove";
                }
                break;
        }
        if ("/JSP/employees.jsp".equals(url)) {
            putEmplListToReq(req, depId);
        }
        req.setAttribute("message", message);
        req.getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    private void putEmplListToReq(HttpServletRequest req, Integer depId) {
        List<Employee> employees = employeeService.getAllEmplsInDep(depId);
        req.setAttribute("employees", employees);
    }
}
