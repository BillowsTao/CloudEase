package com.qtu404.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.beans.ReplyInfo;
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

@WebServlet(name = "AddServlet", urlPatterns = "/page/add.do")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        ObjectMapper mapper = new ObjectMapper();
        String stateCode = "error";
        String type = request.getParameter("type");
        UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");

        String twitterId = "";
        switch (type) {
            case "star":
                String starNo = request.getParameter("star");
                stateCode = UserService.addStar(loginUser.getUserAccount(), starNo);
                break;
            case "like":
                twitterId = request.getParameter("like");
                stateCode = TwitterService.addLike(Integer.parseInt(twitterId), loginUser.getUserId());
                break;

            case "reply":
                twitterId = request.getParameter("twitterId");
                String replyContent = request.getParameter("replyContent");
                ReplyInfo replyInfo  = TwitterService.addReply(loginUser.getUserId(),Integer.parseInt(twitterId),replyContent);
                stateCode = mapper.writeValueAsString(replyInfo);;
                break;
        }

        response.getWriter().write(stateCode);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
