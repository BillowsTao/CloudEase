package com.qtu404.beans;

import com.qtu404.common.Config;
import com.qtu404.enetity.Reply;

public class ReplyInfo {
    private long replyId;
    private String userNo;
    private String userAvatorUrl;
    private String userName;
    private String replyDate;
    private String replyContent;

    public ReplyInfo(Reply reply){
        this.replyContent = reply.getReplyContent();
        this.replyDate = Config.df.format(reply.getReplyDate());
        this.userNo = reply.getUser().getUserNo();
        this.replyId = reply.getReplyId();
        this.userAvatorUrl = reply.getUser().getUserHead();
        this.userName = reply.getUser().getUserName();
    }

    public ReplyInfo(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getReplyId() {
        return replyId;
    }

    public void setReplyId(long replyId) {
        this.replyId = replyId;
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

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
