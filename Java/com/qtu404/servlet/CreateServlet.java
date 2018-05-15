package com.qtu404.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.beans.TwitterInfo;
import com.qtu404.beans.UserInfo;
import com.qtu404.service.TwitterService;
import com.qtu404.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateServlet", urlPatterns = "/page/create.do")
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String rootPath = request.getRealPath("/");
        UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");
        String type = request.getParameter("type");
        String stateCode = "error";
        ObjectMapper mapper = new ObjectMapper();

        switch (type) {
            case "twitter"://创建推文
                String twitterContent = request.getParameter("twitterContent");
                String imgUrl = request.getParameter("imgUrl");
                TwitterInfo twitterInfo = new TwitterInfo();
                twitterInfo.setTwitterContent(twitterContent);
                twitterInfo.setTwitterImgUrl(imgUrl);

                TwitterInfo twitterInfoNew = null;
                twitterInfoNew = TwitterService.createTwitter(loginUser.getUserId(), twitterInfo, rootPath);
                stateCode = mapper.writeValueAsString(twitterInfoNew);
                break;

            case "user":
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(request.getParameter("userName"));
                userInfo.setUserEmail(request.getParameter("userEmail"));
                userInfo.setUserPhone(request.getParameter("userPhone"));
                userInfo.setUserPassword(request.getParameter("password"));
                userInfo.setUserAccount(request.getParameter("userNo"));

                stateCode = UserService.createUser(userInfo, rootPath);
                break;
        }
        response.getWriter().write(stateCode);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
