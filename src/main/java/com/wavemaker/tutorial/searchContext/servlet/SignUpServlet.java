package com.wavemaker.tutorial.searchContext.servlet;

import com.wavemaker.tutorial.searchContext.domain.User;
import com.wavemaker.tutorial.searchContext.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by srujant on 15/7/16.
 */
public class SignUpServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        User user = new User(request.getParameter("emailId"),request.getParameter("password"),request.getParameter("username"));
        if (UserService.insertUser(user) != -1) {
            request.getSession();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("./search.html");
            requestDispatcher.forward(request, response);
        }
    }
}
