package gr.aueb.cf.teachersusersapp.controller;

import gr.aueb.cf.teachersusersapp.dao.ITeacherDAO;
import gr.aueb.cf.teachersusersapp.dao.TeacherDAOImpl;
import gr.aueb.cf.teachersusersapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersusersapp.dto.TeacherDTO;
import gr.aueb.cf.teachersusersapp.service.ITeacherService;
import gr.aueb.cf.teachersusersapp.service.TeacherServiceImpl;
import gr.aueb.cf.teachersusersapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.teachersusersapp.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teachersusersapp/update")
public class UpdateTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/teachersusersapp/static/templates/teacherupdate.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		TeacherDTO newTeacherDTO = new TeacherDTO();
		newTeacherDTO.setId(id);
		newTeacherDTO.setFirstname(firstname);
		newTeacherDTO.setLastname(lastname);
		request.setAttribute("insertedTeacher", newTeacherDTO);
		
		try {
			String error = Validator.validate(newTeacherDTO);
			if (!error.equals("")) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/teachersusersapp/static/templates/teachersmenu.jsp")
						.forward(request, response);
			}
			teacherServ.updateTeacher(newTeacherDTO);
			request.setAttribute("teacher", newTeacherDTO);
			request.getRequestDispatcher("/teachersusersapp/static/templates/teacherupdated.jsp")
					.forward(request, response);
		} catch (TeacherNotFoundException | TeacherDAOException e) {
			String message = e.getMessage();
			request.setAttribute("isError", true);
			request.setAttribute("teacher", newTeacherDTO);
			request.getRequestDispatcher("/teachersusersapp/static/templates//teacherupdated.jsp")
					.forward(request, response);

		}
	}
}
