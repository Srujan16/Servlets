package com.wavemaker.tutorial.searchContext.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by srujant on 19/7/16.
 */
public class Dispatcher  extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if("signUp".equals(action)){
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("./signUp.html");
            requestDispatcher.forward(request,response);
        }else{
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("login");
            requestDispatcher.forward(request,response);
        }
    }

}
