package com.qtu404.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.beans.TwitterInfo;
import com.qtu404.beans.UserInfo;
import com.qtu404.common.SortTwitterById;
import com.qtu404.enetity.User;
import com.qtu404.service.TwitterService;
import com.qtu404.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "FindTwitterServlet", urlPatterns = "/page/findTwitter.do")
public class FindTwitterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        String rows_String = request.getParameter("rows");
        String type = request.getParameter("type");
        String lastId = request.getParameter("lastId");
        UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");
        int rows_int = 6;
        String jsonStr = "";
        if (rows_String != null && !rows_String.equals("")) {
            rows_int = Integer.parseInt(rows_String);
        }
        ArrayList<TwitterInfo> twitterInfos = null;

        switch (type) {
            case "homeTwitter":
                twitterInfos = TwitterService.findHomeTwitter(loginUser.getUserId());
                Collections.sort(twitterInfos, new SortTwitterById());
                twitterInfos = pageTwitterInfo(twitterInfos, lastId, rows_int);
                jsonStr = mapper.writeValueAsString(twitterInfos);
                break;

            case "ByUserNo":
                String userNo = request.getParameter("userNo");
                twitterInfos = TwitterService.findTwitterByUserNo(userNo);
                Collections.sort(twitterInfos, new SortTwitterById());
                jsonStr = mapper.writeValueAsString(twitterInfos);
                break;
        }

        out.write(jsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 对推文进行分页
     *
     * @param twitterInfos  完整的推文信息
     * @param lastId_String 最后一个推文id
     * @param rows          一次发多少
     */
    private ArrayList<TwitterInfo> pageTwitterInfo(ArrayList<TwitterInfo> twitterInfos, String lastId_String, int rows) {
        ArrayList<TwitterInfo> rs = new ArrayList<>(0);
        long lastId = 0;
        if (lastId_String == null || lastId_String.equals("")) {//第一次加载
            for (int i = 0; i < rows; i++) {
                if (i < twitterInfos.size()) {//不能越界
                    rs.add(twitterInfos.get(i));
                } else {
                    break;
                }
            }
        } else {//不是第一次
            try {//是否合法
                lastId = Integer.parseInt(lastId_String);
            } catch (Exception e) {//不合法
                return rs;
            }
            for (int i = 0; i < twitterInfos.size(); i++) {//找最后一个Id所在的记录
                if (lastId == twitterInfos.get(i).getTwitterId()) {
                    for (int j = i + 1, count = 0; count < rows; j++, count++) {//最后一个推文后面继续来几个
                        if (j < twitterInfos.size()) {//不能越界
                            rs.add(twitterInfos.get(j));
                        } else {
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return rs;
    }
}
