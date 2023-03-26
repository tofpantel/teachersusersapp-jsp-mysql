package gr.aueb.cf.teachersusersapp.controller;

import gr.aueb.cf.teachersusersapp.dao.IUserDBDAO;
import gr.aueb.cf.teachersusersapp.dao.UserDBDAOImpl;
import gr.aueb.cf.teachersusersapp.dao.exceptions.UserDBDAOException;
import gr.aueb.cf.teachersusersapp.dto.UserDBDTO;
import gr.aueb.cf.teachersusersapp.service.IUserDBService;
import gr.aueb.cf.teachersusersapp.service.UserDBServiceImpl;
import gr.aueb.cf.teachersusersapp.service.exceptions.UserDBNotFoundException;
import gr.aueb.cf.teachersusersapp.validation.ValidatorUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teachersusersapp/users/update")
public class UpdateUserDBController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final IUserDBDAO userDAO = new UserDBDAOImpl();
    private final IUserDBService userServ = new UserDBServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/teachersusersapp/static/templates/userupdate.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDBDTO newUserDTO = new UserDBDTO();
        newUserDTO.setId(id);
        newUserDTO.setUsername(username);
        newUserDTO.setPassword(password);
        request.setAttribute("insertedUserDB", newUserDTO);

        try {
            String error = ValidatorUser.validate(newUserDTO);
            if (!error.equals("")) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("/teachersusersapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
            }
            userServ.updateUser(newUserDTO);
            request.setAttribute("user", newUserDTO);
            request.getRequestDispatcher("/teachersusersapp/static/templates/userupdated.jsp")
                    .forward(request, response);
        } catch (UserDBDAOException | UserDBNotFoundException e) {
            String message = e.getMessage();
            request.setAttribute("isError", true);
            request.setAttribute("user", newUserDTO );
            request.getRequestDispatcher("/teachersusersapp/static/templates/userupdated.jsp")
                    .forward(request, response);

        }
    }

}
