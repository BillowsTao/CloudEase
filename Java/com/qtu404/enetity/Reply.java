package com.qtu404.enetity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reply")
public class Reply {
    @Id
    @Column(name = "reply_id")
    private long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "twitter_id")
    private Twitter twitter;


    @Column(name = "reply_content")
    private String replyContent;

    @Column(name = "reply_date")
    private Date replyDate;

    public Reply() {

    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public long getReplyId() {
        return replyId;
    }

    public void setReplyId(long replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

}
