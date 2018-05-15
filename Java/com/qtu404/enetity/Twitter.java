package com.qtu404.enetity;

import com.sun.org.apache.regexp.internal.RE;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "twitter")
public class Twitter {
    @Id
    @Column(name = "twitter_id")
    private long twitterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "twitter_content")
    private String twitterContent;

    @Column(name = "twitter_date")
    private Date twitterDate;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "likeTwitters")
    private Set<User> likeUsers = new HashSet<User>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "twitter")
    private Set<Reply> replies = new HashSet<Reply>(0);

    @Column(name = "twitter_url")
    private String twitterImgUrl;

    public Set<Reply> getReplies() {
        return replies;
    }

    public String getTwitterImgUrl() {
        return twitterImgUrl;
    }

    public void setTwitterImgUrl(String twitterImgUrl) {
        this.twitterImgUrl = twitterImgUrl;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public Set<User> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(Set<User> likeUsers) {
        this.likeUsers = likeUsers;
    }

    public long getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(long twitterId) {
        this.twitterId = twitterId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTwitterContent() {
        return twitterContent;
    }

    public void setTwitterContent(String twitterContent) {
        this.twitterContent = twitterContent;
    }

    public Date getTwitterDate() {
        return twitterDate;
    }

    public void setTwitterDate(Date twitterDate) {
        this.twitterDate = twitterDate;
    }
}
