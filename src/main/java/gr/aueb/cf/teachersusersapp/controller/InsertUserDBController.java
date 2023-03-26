package gr.aueb.cf.teachersusersapp.controller;



import gr.aueb.cf.teachersusersapp.dao.IUserDBDAO;
import gr.aueb.cf.teachersusersapp.dao.UserDBDAOImpl;
import gr.aueb.cf.teachersusersapp.dao.exceptions.UserDBDAOException;
import gr.aueb.cf.teachersusersapp.dto.UserDBDTO;
import gr.aueb.cf.teachersusersapp.service.IUserDBService;
import gr.aueb.cf.teachersusersapp.service.UserDBServiceImpl;
import gr.aueb.cf.teachersusersapp.validation.ValidatorUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teachersusersapp/users/insert")
public class InsertUserDBController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final IUserDBDAO userDAO = new UserDBDAOImpl();
    private final IUserDBService userServ = new UserDBServiceImpl(userDAO);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("error", "");
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        UserDBDTO userDTO = new UserDBDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);

        request.setAttribute("insertedUserDB", userDTO);
        try{
            String error = ValidatorUser.validate(userDTO);
            if (!error.equals("")){
                request.setAttribute("error", error);
                request.getRequestDispatcher("/teachersusersapp/static/templates/usersmenu.jsp")
                .forward(request, response);
            }
            userServ.insertUser(userDTO);
            request.getRequestDispatcher("/teachersusersapp/static/templates/userinserted.jsp")
                    .forward(request, response);
        } catch (UserDBDAOException e){
//            e.printStackTrace();
            request.setAttribute("sqlError", true);
            request.getRequestDispatcher("/teachersusersapp/static/templates/usersmenu.jsp")
                    .forward(request, response);
        }
    }
}
