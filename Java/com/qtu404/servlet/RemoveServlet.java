package com.qtu404.servlet;

import com.qtu404.beans.UserInfo;
import com.qtu404.enetity.Twitter;
import com.qtu404.enetity.User;
import com.qtu404.service.TwitterService;
import com.qtu404.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveServlet", urlPatterns = "/page/remove.do")
public class RemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String stateCode = "error";
        String type = request.getParameter("type");
        UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");

        switch (type) {
            case "star":
                String starNo = request.getParameter("star");
                stateCode = UserService.removeStar(loginUser.getUserAccount(), starNo);
                break;
            case "like":
                String likeId = request.getParameter("like");
                stateCode = TwitterService.removeLike(Integer.parseInt(likeId), loginUser.getUserId());
                break;
            case "twitter":
                String twitterId = request.getParameter("twitterId");
                stateCode = TwitterService.removeTwitter(loginUser.getUserId(), Integer.parseInt(twitterId));
                break;
        }

        response.getWriter().write(stateCode);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
