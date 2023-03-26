package gr.aueb.cf.teachersusersapp.controller;



import gr.aueb.cf.teachersusersapp.dao.IUserDBDAO;
import gr.aueb.cf.teachersusersapp.dao.UserDBDAOImpl;
import gr.aueb.cf.teachersusersapp.dao.exceptions.UserDBDAOException;
import gr.aueb.cf.teachersusersapp.dto.UserDBDTO;
import gr.aueb.cf.teachersusersapp.service.IUserDBService;
import gr.aueb.cf.teachersusersapp.service.UserDBServiceImpl;
import gr.aueb.cf.teachersusersapp.service.exceptions.UserDBNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teachersusersapp/users/delete")
public class DeleteUserDBController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    IUserDBDAO userDAO = new UserDBDAOImpl();
    IUserDBService userServ = new UserDBServiceImpl(userDAO);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id").trim());
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        UserDBDTO userDTO = new UserDBDTO();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        try {
            userServ.deleteUser(id);
            request.setAttribute("user", userDTO);
            request.getRequestDispatcher("/teachersusersapp/static/templates/userdeleted.jsp")
                    .forward(request, response);
        } catch (UserDBNotFoundException | UserDBDAOException e) {
            request.setAttribute("deleteAPIError", true);
            request.getRequestDispatcher("/teachersusersapp/static/templates/users.jsp")
                    .forward(request, response);
        }
    }


}
