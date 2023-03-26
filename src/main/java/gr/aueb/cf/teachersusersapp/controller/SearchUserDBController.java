package gr.aueb.cf.teachersusersapp.controller;

import gr.aueb.cf.teachersusersapp.dao.IUserDBDAO;
import gr.aueb.cf.teachersusersapp.dao.UserDBDAOImpl;
import gr.aueb.cf.teachersusersapp.dao.exceptions.UserDBDAOException;
import gr.aueb.cf.teachersusersapp.dto.UserDBDTO;
import gr.aueb.cf.teachersusersapp.model.UserDB;
import gr.aueb.cf.teachersusersapp.service.IUserDBService;
import gr.aueb.cf.teachersusersapp.service.UserDBServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "SearchUserDBController", value = "/teachersusersapp/users/search")
public class SearchUserDBController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserDBDAO userDAO = new UserDBDAOImpl();
    IUserDBService userService = new UserDBServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/teachersusersapp/static/templates/usersmenu.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        response.setContentType("text/html; charset=UTF-8");
        String username = request.getParameter("username").trim();
        UserDBDTO userDTO = new UserDBDTO();
        userDTO.setUsername(username);
        String message = "";
        try {
            List<UserDB> users = userService.getUsersByUsername(userDTO.getUsername());
            if (users.size() == 0) {
                request.setAttribute("usersNotFound", true);
                request.getRequestDispatcher("/teachersusersapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
            }
            request.setAttribute("users", users);
            request.getRequestDispatcher("/teachersusersapp/static/templates/users.jsp").forward(request, response);
        } catch (UserDBDAOException e) {
            message = e.getMessage();
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("/teachersusersapp/static/templates/usersmenu.jsp").forward(request, response);
        }


    }

}
