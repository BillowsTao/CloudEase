package com.qtu404.beans;

import com.qtu404.common.Config;
import com.qtu404.common.SortReById;
import com.qtu404.enetity.Reply;
import com.qtu404.enetity.Twitter;
import com.qtu404.enetity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class TwitterInfo {
    private String userName;
    private String userNo;
    private String userAvatorUrl;
    private long twitterId;
    private String twitterOwner;
    private String twitterContent;
    private String twitterDate;
    private String twitterImgUrl;
    private int likeNum = 0;//喜欢的数量
    private int replyNum = 0;//回复的数量
    private int transpondNum = 0;//转发的数量
    private ArrayList<ReplyInfo> replyInfos = new ArrayList<>(0);
    private String isLike = "false";
    private String likeUser = "";//喜欢的人

    public static Twitter initTwitterByInfo(TwitterInfo twitterInfo) {
        Twitter twitter = new Twitter();
        twitter.setTwitterImgUrl(twitterInfo.twitterImgUrl);
        twitter.setTwitterContent(twitterInfo.getTwitterContent());
        twitter.setTwitterDate(new Date());

        return twitter;
    }


    /**
     * @param twitter 推文实体
     * @return 得到简要信息  不包括回复
     */
    public static TwitterInfo createInfo(Twitter twitter) {
        TwitterInfo twitterInfo = new TwitterInfo();

        twitterInfo.setUserName(twitter.getOwner().getUserName());
        twitterInfo.setUserNo(twitter.getOwner().getUserNo());
        twitterInfo.setUserAvatorUrl(twitter.getOwner().getUserHead());
        twitterInfo.setTwitterContent(twitter.getTwitterContent());
        twitterInfo.setTwitterId(twitter.getTwitterId());
        twitterInfo.setTwitterOwner(twitter.getOwner().getUserNo());
        twitterInfo.setTwitterDate(Config.df.format(twitter.getTwitterDate()));
        twitterInfo.setReplyNum(twitter.getReplies().size());
        twitterInfo.setTwitterImgUrl(twitter.getTwitterImgUrl());
        twitterInfo.setLikeNum(twitter.getLikeUsers().size());

        return twitterInfo;
    }

    /**
     * 得到推文打开后的信息
     *
     * @param twitter
     * @return 推文信息类
     */
    public static TwitterInfo createDetailInfo(Twitter twitter) {
        TwitterInfo twitterInfo = createInfo(twitter);

        Set<Reply> replies = twitter.getReplies();

        for (Reply reply : replies) {
            ReplyInfo replyInfo = new ReplyInfo(reply);
            twitterInfo.getReplyInfos().add(replyInfo);
        }

        Set<User> likeUser = twitter.getLikeUsers();

        if (twitterInfo.getLikeNum() > 0) {
            for (User user : likeUser) {
                twitterInfo.setLikeUser(twitterInfo.getLikeUser() + user.getUserName() + ",");
            }
            twitterInfo.setLikeUser(twitterInfo.getLikeUser() + "等" + twitterInfo.getLikeNum() + "人喜欢此动态");
        }
        Collections.sort(twitterInfo.getReplyInfos(), new SortReById());
        return twitterInfo;
    }

    public TwitterInfo() {
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public String getTwitterImgUrl() {
        return twitterImgUrl;
    }

    public void setTwitterImgUrl(String twitterImgUrl) {
        this.twitterImgUrl = twitterImgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserAvatorUrl() {
        return userAvatorUrl;
    }

    public void setUserAvatorUrl(String userAvatorUrl) {
        this.userAvatorUrl = userAvatorUrl;
    }

    public ArrayList<ReplyInfo> getReplyInfos() {
        return replyInfos;
    }

    public void setReplyInfos(ArrayList<ReplyInfo> replyInfos) {
        this.replyInfos = replyInfos;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getTranspondNum() {
        return transpondNum;
    }

    public void setTranspondNum(int transpondNum) {
        this.transpondNum = transpondNum;
    }

    public long getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(long twitterId) {
        this.twitterId = twitterId;
    }

    public String getTwitterOwner() {
        return twitterOwner;
    }

    public void setTwitterOwner(String twitterOwner) {
        this.twitterOwner = twitterOwner;
    }

    public String getTwitterContent() {
        return twitterContent;
    }

    public void setTwitterContent(String twitterContent) {
        this.twitterContent = twitterContent;
    }

    public String getTwitterDate() {
        return twitterDate;
    }

    public void setTwitterDate(String twitterDate) {
        this.twitterDate = twitterDate;
    }

    public String getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(String likeUser) {
        this.likeUser = likeUser;
    }
}
