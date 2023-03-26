package gr.aueb.cf.teachersusersapp.controller;

import gr.aueb.cf.teachersusersapp.dao.ITeacherDAO;
import gr.aueb.cf.teachersusersapp.dao.TeacherDAOImpl;
import gr.aueb.cf.teachersusersapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersusersapp.dto.TeacherDTO;
import gr.aueb.cf.teachersusersapp.service.ITeacherService;
import gr.aueb.cf.teachersusersapp.service.TeacherServiceImpl;
import gr.aueb.cf.teachersusersapp.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/teachersusersapp/insert")
public class InsertTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setAttribute("error", "");
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setFirstname(firstname);
		teacherDTO.setLastname(lastname);
		request.setAttribute("insertedTeacher", teacherDTO);
		try {
			String error = Validator.validate(teacherDTO);
			if (!error.equals("")) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/teachersusersapp/static/templates/teachersmenu.jsp")
						.forward(request, response);
			}
			teacherServ.insertTeacher(teacherDTO);
			request.getRequestDispatcher("/teachersusersapp/static/templates/teacherinserted.jsp")
					.forward(request, response);
		} catch (TeacherDAOException e) {
			//e.printStackTrace();
			request.setAttribute("sqlError", true);
			request.getRequestDispatcher("/teachersusersapp/static/templates/teachersmenu.jsp")
					.forward(request, response);
		}
	}
}

