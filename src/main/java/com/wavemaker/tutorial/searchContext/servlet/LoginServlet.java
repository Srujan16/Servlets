package com.wavemaker.tutorial.searchContext.servlet;

import com.wavemaker.tutorial.searchContext.domain.User;
import com.wavemaker.tutorial.searchContext.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by srujant on 15/7/16.
 */
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        String password = request.getParameter("password");
        User user = UserService.getUser(request.getParameter("emailId"));
        if (user == null) {
            response.sendRedirect("./index.html");
        } else if (user.getPassword().equals(password)) {
            request.getSession();
            dispatchRequest(request, response, "./search.html", "forward");
        } else {
            printWriter.println("Invalid user details");
            dispatchRequest(request, response, "./login.html", "include");
        }
    }


    private void dispatchRequest(HttpServletRequest request, HttpServletResponse response, String dispatchPage, String dispatchType) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(dispatchPage);
        if ("include".equals(dispatchType)) {
            requestDispatcher.include(request, response);
        } else if ("forward".equals(dispatchType)) {
            requestDispatcher.forward(request, response);
        } else {
            throw new RuntimeException("invalid request dipatchType");
        }
    }


}
