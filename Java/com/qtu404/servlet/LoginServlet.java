package com.qtu404.servlet;

import com.qtu404.beans.UserInfo;
import com.qtu404.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        String stateCode = "error";
        UserInfo userInfo = UserService.userLogin(userEmail,userPassword);

        if (userInfo!=null){
            request.getSession().setAttribute("loginUser",userInfo);
            stateCode = "success_05";
        }else {
            stateCode = "error_05";
        }

        response.getWriter().write(stateCode);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
