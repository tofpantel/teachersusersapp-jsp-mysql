package gr.aueb.cf.teachersusersapp.controller;


import gr.aueb.cf.teachersusersapp.dao.ITeacherDAO;
import gr.aueb.cf.teachersusersapp.dao.TeacherDAOImpl;
import gr.aueb.cf.teachersusersapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersusersapp.dto.TeacherDTO;
import gr.aueb.cf.teachersusersapp.model.Teacher;
import gr.aueb.cf.teachersusersapp.service.ITeacherService;
import gr.aueb.cf.teachersusersapp.service.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchTeachersController", value = "/teachersusersapp/search")
public class SearchTeachersController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/teachersusersapp/static/templates/teachersmenu.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String lastname = request.getParameter("lastname").trim();
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setLastname(lastname);
        String message = "";
        try {
            List<Teacher> teachers = teacherService.getTeachersByLastname(teacherDTO.getLastname());
            if (teachers.size() == 0) {
                request.setAttribute("teachersNotFound", true);
                request.getRequestDispatcher("/teachersusersapp/static/templates/teachersmenu.jsp")
                        .forward(request, response);
            }
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/teachersusersapp/static/templates/teachers.jsp").forward(request, response);
        } catch (TeacherDAOException e) {
            message = e.getMessage();
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("/teachersusersapp/static/templates/teachersmenu.jsp").forward(request, response);
        }
    }
}
