package com.qtu404.servlet;

import com.qtu404.beans.UserInfo;
import com.qtu404.common.ImageDeal;
import com.qtu404.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyServlet", urlPatterns = "/page/modify.do")
public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String returnString = "error";
        String type = request.getParameter("type");
        UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");

        switch (type) {
            case "avator":
                String base64 = request.getParameter("imgStr");
                String[] base64_str = base64.split(",");
                returnString = UserService.modifyAvator(loginUser.getUserId(),base64_str[1],request.getRealPath("/"));
                break;
        }

        response.getWriter().write(returnString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
