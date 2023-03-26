package gr.aueb.cf.teachersusersapp.controller;

import gr.aueb.cf.teachersusersapp.authentication.AuthenticationProvider;
import gr.aueb.cf.teachersusersapp.dao.ITeacherDAO;
import gr.aueb.cf.teachersusersapp.dao.TeacherDAOImpl;
import gr.aueb.cf.teachersusersapp.dto.UserDTO;
import gr.aueb.cf.teachersusersapp.model.User;
import gr.aueb.cf.teachersusersapp.service.ITeacherService;
import gr.aueb.cf.teachersusersapp.service.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(email);
		userDTO.setPassword(password);

		User user = AuthenticationProvider.authenticate(userDTO);
		if (user == null)  response.sendRedirect(request.getContextPath() + "/login");

		// Retrieve the current session, and if one doesn't exist
		// create it.
		HttpSession session = request.getSession(true);

		// Append to session object for personalization
		assert user != null;
		session.setAttribute("username", userDTO.getUsername());


		// Default session timeout is 30 min
		session.setMaxInactiveInterval(60 * 15); // 15 minutes

		// Create cookie with session ID
		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		System.out.println("Login gave: " + session.getId());
		cookie.setMaxAge(session.getMaxInactiveInterval());
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath() + "/teachersusersapp/menu");
	}
}
