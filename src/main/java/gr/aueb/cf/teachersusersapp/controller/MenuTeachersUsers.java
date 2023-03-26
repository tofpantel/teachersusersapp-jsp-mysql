package gr.aueb.cf.teachersusersapp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teachersusersapp/menu")
public class MenuTeachersUsers extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html; charset=UTF-8");
        request.getRequestDispatcher("/teachersusersapp/teachersusersmenu.jsp").forward(request, response);

    }

}
