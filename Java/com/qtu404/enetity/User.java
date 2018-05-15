package com.qtu404.enetity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "user_no")})
public class User {
    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_no")
    private String userNo;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_info")
    private String userInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Twitter> twitters = new HashSet<Twitter>(0);//自己的推文

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "follow", joinColumns = @JoinColumn(name = "user_id_follow"), inverseJoinColumns = @JoinColumn(name = "user_id_star"))
    private Set<User> stars = new HashSet<User>(0);//自己的粉丝

    @ManyToMany(mappedBy = "stars", cascade = CascadeType.ALL)
    private Set<User> follows = new HashSet<User>(0);//自己的关注

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "like_user"),
            inverseJoinColumns = @JoinColumn(name = "like_twitter")
    )
    private Set<Twitter> likeTwitters = new HashSet<Twitter>(0);

    @Column(name = "user_head")
    private String userHead;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Reply> replies = new HashSet<>(0);

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Set<Twitter> getLikeTwitters() {
        return likeTwitters;
    }

    public void setLikeTwitters(Set<Twitter> likeTwitters) {
        this.likeTwitters = likeTwitters;
    }


    public Set<User> getStars() {
        return stars;
    }

    public void setStars(Set<User> stars) {
        this.stars = stars;
    }

    public Set<User> getFollows() {
        return follows;
    }

    public void setFollows(Set<User> follows) {
        this.follows = follows;
    }

    public Set<Twitter> getTwitters() {
        return twitters;
    }

    public void setTwitters(Set<Twitter> twitters) {
        this.twitters = twitters;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public User() {

    }
}
