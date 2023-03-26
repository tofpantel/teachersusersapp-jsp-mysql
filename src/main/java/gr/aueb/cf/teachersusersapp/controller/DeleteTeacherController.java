package gr.aueb.cf.teachersusersapp.controller;

import gr.aueb.cf.teachersusersapp.dao.ITeacherDAO;
import gr.aueb.cf.teachersusersapp.dao.TeacherDAOImpl;
import gr.aueb.cf.teachersusersapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersusersapp.dto.TeacherDTO;
import gr.aueb.cf.teachersusersapp.service.ITeacherService;
import gr.aueb.cf.teachersusersapp.service.TeacherServiceImpl;
import gr.aueb.cf.teachersusersapp.service.exceptions.TeacherNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/teachersusersapp/delete")
public class DeleteTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ITeacherDAO teacherDAO = new TeacherDAOImpl();

	ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id").trim());	
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(id);
		teacherDTO.setFirstname(firstname);
		teacherDTO.setLastname(lastname);
		try {
			teacherServ.deleteTeacher(id);
			request.setAttribute("teacher", teacherDTO);
			request.getRequestDispatcher("/teachersusersapp/static/templates/teacherdeleted.jsp")
					.forward(request, response);
		} catch (TeacherNotFoundException | TeacherDAOException e) {
			request.setAttribute("deleteAPIError", true);
			request.getRequestDispatcher("/teachersusersapp/static/templates/teachers.jsp")
					.forward(request, response);
		}
	}
}

