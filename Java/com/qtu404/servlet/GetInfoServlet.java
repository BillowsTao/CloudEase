package com.qtu404.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.beans.TwitterInfo;
import com.qtu404.beans.UserInfo;
import com.qtu404.common.ImageDeal;
import com.qtu404.enetity.User;
import com.qtu404.service.TwitterService;
import com.qtu404.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetInfoServlet", urlPatterns = "/page/getInfo.do")
public class GetInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();

        String returnString = "error";
        String type = request.getParameter("type");
        UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");
        String userNo = null;
        String email = null;

        switch (type) {
            case "isExistNo":
                userNo = request.getParameter("userNo");
                if (UserService.iSsameNo(userNo)) {
                    returnString = "notExist";
                } else returnString = "Existed";
                break;

            case "isExistEmail":
                email = request.getParameter("email");
                if (UserService.iSsameEmail(email)) {
                    returnString = "notExist";
                } else returnString = "Existed";
                break;

            case "loginUser":
                returnString = mapper.writeValueAsString(loginUser);
                break;

            case "user":
                userNo = request.getParameter("userNo");
                UserInfo userInfo = UserService.getUserByNo(userNo);
                returnString = mapper.writeValueAsString(userInfo);
                break;

            case "twitter":
                String twitterId = request.getParameter("twitterId");
                TwitterInfo twitterInfo = TwitterService.getTwitterInfo(Integer.parseInt(twitterId));
                returnString = mapper.writeValueAsString(twitterInfo);
                break;

            case "stars":
                ArrayList<UserInfo> stars = UserService.findStarInfo(loginUser.getUserId());
                returnString = mapper.writeValueAsString(stars);
                break;
        }

        response.getWriter().write(returnString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
