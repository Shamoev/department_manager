package com.aimprosoft.controller;

import com.aimprosoft.model.Department;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DepsServlet extends AbstractInitialServlet {
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
        String departmentIdString = req.getParameter("depIdRadio");
        Integer departmentId = null;
        if (departmentIdString != null) {
            departmentId = Integer.parseInt(departmentIdString);
        }
        String url = "/JSP/departments.jsp";
        String message = "";
        HttpSession session = req.getSession(true);
        switch (action) {
            case "add":
                session.removeAttribute("department");
                url = "/JSP/addmoddep.jsp";
                break;
            case "modify":
                if (departmentId != null) {
                    session.setAttribute("department", departmentService.getDepartmentById(departmentId));
                    url = "/JSP/addmoddep.jsp";
                } else {
                    message = "Select department to modify";
                }
                break;
            case "remove":
                if (departmentId != null) {
                    departmentService.removeDepWithEmpls(departmentId);
                    message = "";
                } else {
                    message = "Select department to remove";
                }
                break;
            case "list":
                if (departmentId != null) {
                    session.setAttribute("department", departmentService.getDepartmentById(departmentId));
                    url = "/employees";
                } else {
                    message = "Select department to show a list of its employees";
                }
                break;
        }
        if ("/JSP/departments.jsp".equals(url)) {
            putDepListToReq(req);
        }
        req.setAttribute("message", message);
        req.getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    private void putDepListToReq(HttpServletRequest req) {
        List<Department> departments = departmentService.getAllDepartmentsList();
        req.setAttribute("departments", departments);
    }
}
