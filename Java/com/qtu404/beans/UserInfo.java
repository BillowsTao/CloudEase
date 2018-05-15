package com.qtu404.beans;

import com.qtu404.enetity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息类
 *
 * @author wildhunt_unique
 */
public class UserInfo {
    private long userId;      //Id值
    private String userName = "";    //昵称
    private String userAccount = ""; //账户名
    private String userPhone = "";   //手机号
    private String userEmail = "";   //邮箱
    private String userPassword = "";    //密码
    private String personalInfo = "";//个人简介
    private String avator = "";     //头像图片路径
    private int follwNum = 0;//粉丝数量
    private int starNum = 0;//关注数量
    private int twitterNum = 0;//推文数量

    public UserInfo() {

    }

    public static UserInfo createUserInfo(User user) {
        UserInfo userInfo = new UserInfo();

        userInfo.setUserAccount(user.getUserNo());
        userInfo.setPersonalInfo(user.getUserInfo());
        userInfo.setUserEmail(user.getUserEmail());
        userInfo.setUserId(user.getUserId());
        userInfo.setUserName(user.getUserName());
        userInfo.setUserPassword(user.getUserPassword());
        userInfo.setUserPhone(user.getUserPhone());
        userInfo.setAvator(user.getUserHead());
        userInfo.setTwitterNum(user.getTwitters().size());
        userInfo.setFollwNum(user.getFollows().size());
        userInfo.setStarNum(user.getStars().size());

        return userInfo;
    }

    public static ArrayList<UserInfo> createUserInfo(List<User> users) {
        ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>(0);

        for (User user : users) {
            UserInfo userInfo = UserInfo.createUserInfo(user);
            userInfos.add(userInfo);
        }

        return userInfos;
    }

        public static User initUserByInfo(UserInfo userInfo) {
        User user = new User();

        user.setUserEmail(userInfo.getUserEmail());
        user.setUserName(userInfo.getUserName());
        user.setUserPassword(userInfo.getUserPassword());
        user.setUserPhone(userInfo.getUserPhone());
        user.setUserNo(userInfo.getUserAccount());
        user.setUserInfo(userInfo.getPersonalInfo());

        return user;
    }

    public int getFollwNum() {
        return follwNum;
    }

    public void setFollwNum(int follwNum) {
        this.follwNum = follwNum;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public int getTwitterNum() {
        return twitterNum;
    }

    public void setTwitterNum(int twitterNum) {
        this.twitterNum = twitterNum;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }
}
