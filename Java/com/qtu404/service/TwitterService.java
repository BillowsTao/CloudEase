package com.qtu404.service;

import com.qtu404.beans.ReplyInfo;
import com.qtu404.beans.TwitterInfo;
import com.qtu404.common.FileManage;
import com.qtu404.dao.ReplyDao;
import com.qtu404.dao.TwitterDao;
import com.qtu404.dao.UserDao;
import com.qtu404.enetity.HibernateUtil;
import com.qtu404.enetity.Reply;
import com.qtu404.enetity.Twitter;
import com.qtu404.enetity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/*

                        _ooOoo_
                       o8888888o
                       88" . "88
                       (| -_- |)
                        O\ = /O
                    ____/`---'\____
                     .' \\| |// `.
                   / \\||| : |||// \
                 / _||||| -:- |||||- \
                   | | \\\ - /// | |
                 | \_| ''\---/'' | |
                  \ .-\__ `-` ___/-. /
               ___`. .' /--.--\ `. . ___
            ."" '< `.___\_<|>_/___.' >'"".
           | | : `- \`.;`\ _ /`;.`/ - ` : | |
             \ \ `-. \_ __\ /__ _/ .-` / /
     ======`-.____`-.___\_____/___.-`____.-'======
                        `=---='

     .............................................
              佛祖保佑                永无BUG

                   江城子 . 程序员之歌

               十年生死两茫茫，写程序，到天亮。
                   千行代码，Bug何处藏。
              纵使上线又怎样，朝令改，夕断肠。

                甲方每天新想法，天天改，日日忙。
                   相顾无言，惟有泪千行。
               每晚灯火阑珊处，夜难寐，加班狂。
 */


/**
 * 推文服务类
 *
 * @author wildhunt_unique
 */
public class TwitterService {

    private void TemporalMoudle() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            transaction.commit();

        } catch (Exception e) {

            e.printStackTrace();
            transaction.rollback();

        }
    }

    public static ArrayList<TwitterInfo> findTwitterByUserNo(String userNo) {
        ArrayList<TwitterInfo> twitterInfos = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            User user = UserDao.getUserByNo(session, userNo);
            Set<Twitter> twitters = user.getTwitters();
            for (Twitter twitter : twitters) {
                twitterInfos.add(TwitterInfo.createInfo(twitter));
            }

            transaction.commit();

        } catch (Exception e) {

            e.printStackTrace();
            transaction.rollback();
        }
        return twitterInfos;
    }


    public static String removeTwitter(long userId, long twitterId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        String stateCode = "error";
        try {
            transaction.begin();

            User user = session.get(User.class, userId);
            Twitter twitter = session.get(Twitter.class, userId);
            user.getTwitters().remove(twitter);

            transaction.commit();
            stateCode = "success_10";
        } catch (Exception e) {
            stateCode = "error_10";
            e.printStackTrace();
            transaction.rollback();
        }
        return stateCode;
    }

    /**
     * 得到一个推文的全部信息
     *
     * @param twitterId
     * @return 推文信息类 包括回复内容
     */
    public static TwitterInfo getTwitterInfo(long twitterId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        TwitterInfo twitterInfo = null;
        try {
            transaction.begin();

            Twitter twitter = session.get(Twitter.class, twitterId);
            twitterInfo = TwitterInfo.createDetailInfo(twitter);

            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
            transaction.rollback();
        }
        return twitterInfo;
    }

    /**
     * 增加一个回复
     *
     * @param userId       回复的用户id
     * @param twitterId    回复的推文id
     * @param replyContent 回复的内容
     * @return success_09//error_09
     */
    public static ReplyInfo addReply(long userId, long twitterId, String replyContent) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        String stateCode = "error";
        Reply reply = null;
        User user = null;
        Twitter twitter = null;
        ReplyInfo replyInfo = null;
        try {
            transaction.begin();

            user = session.get(User.class, userId);
            twitter = session.get(Twitter.class, twitterId);

            reply = new Reply();
            reply.setReplyContent(replyContent);
            reply.setReplyDate(new Date());
            reply.setUser(user);
            reply.setTwitter(twitter);
            reply.setReplyId(ReplyDao.getMaxId(session) + 1);
            session.persist(reply);

            transaction.commit();
            replyInfo = new ReplyInfo(reply);
        } catch (Exception e) {
            stateCode = "error_09";
            e.printStackTrace();
            transaction.rollback();
        }
        return replyInfo;
    }

    /**
     * 推文取消赞
     *
     * @param twitterId 被点赞的推文id
     * @param userId    点赞人
     * @return "success_07";//取消点赞成功 "error_07";//取消点赞失败
     */
    public static String removeLike(long twitterId, long userId) {
        String stateCode = "error";
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        User user = null;
        Twitter twitter = null;
        try {
            transaction.begin();

            user = session.get(User.class, userId);
            twitter = session.get(Twitter.class, twitterId);

            user.getLikeTwitters().remove(twitter);

            Set<User> likeUser = twitter.getLikeUsers();
            int likeNum = likeUser.size() - 1;


            stateCode = likeNum + "||";//点赞成功
            String likeUsers = "";
            if (likeNum > 0) {
                for (User userItem : likeUser) {
                    if (userItem.getUserId() == userId) continue;
                    likeUsers = likeUsers + userItem.getUserName() + ",";
                }
                likeUsers = likeUsers + "等" + likeNum + "人喜欢此动态";
            }
            stateCode = stateCode + likeUsers;

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            stateCode = "error_07";//取消点赞失败
        }

        return stateCode;
    }

    /**
     * 为一个推文增加一个赞
     *
     * @param twitterId 被点赞的推文id
     * @param userId    点赞人
     */
    public static String addLike(long twitterId, long userId) {
        String stateCode = "error";
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        User user = null;
        Twitter twitter = null;
        try {
            transaction.begin();

            user = session.get(User.class, userId);
            twitter = session.get(Twitter.class, twitterId);

            user.getLikeTwitters().add(twitter);

            Set<User> likeUser = twitter.getLikeUsers();
            int likeNum = likeUser.size() + 1;


            stateCode = likeNum + "||";//点赞成功
            String likeUsers = "";
            likeUsers = likeUsers + user.getUserName() + ",";
            if (likeNum > 0) {
                for (User userItem : likeUser) {
                    likeUsers = likeUsers + userItem.getUserName() + ",";
                }
                likeUsers = likeUsers + "等" + likeNum + "人喜欢此动态";
            }
            stateCode = stateCode + likeUsers;

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            stateCode = "error_06";//点赞失败
        }

        return stateCode;
    }

    /**
     * 得到一个用户所有应该在主界面显示的推文 包括自己的推文 自己关注人的推文
     */
    public static ArrayList<TwitterInfo> findHomeTwitter(long userId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        ArrayList<TwitterInfo> twitterInfos = new ArrayList<TwitterInfo>();

        try {
            transaction.begin();
            //先找到到自己
            User loginUser = session.get(User.class, userId);
            //再找到所有的关注
            Set<User> stars = loginUser.getStars();

            //自己的推文
            Set<Twitter> loginTwitter_Set = loginUser.getTwitters();
            Set<Twitter> like = loginUser.getLikeTwitters();//自己喜欢的推文

            for (Twitter twitter : loginTwitter_Set) {
                TwitterInfo twitterInfo = TwitterInfo.createDetailInfo(twitter);
                for (Twitter twitterTemp : like) {
                    if (twitter.getTwitterId() == twitterTemp.getTwitterId()) {
                        twitterInfo.setIsLike("true");
                    }
                }
                twitterInfos.add(twitterInfo);
            }

            //关注人的推文
            for (User user : stars) {//先循环每个人
                Set<Twitter> starTwitter_Set = user.getTwitters();
                for (Twitter twitter : starTwitter_Set) {
                    TwitterInfo twitterInfo = TwitterInfo.createDetailInfo(twitter);
                    for (Twitter twitterTemp : like) {
                        if (twitter.getTwitterId() == twitterTemp.getTwitterId()) {
                            twitterInfo.setIsLike("true");
                        }
                    }
                    twitterInfos.add(twitterInfo);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }


        return twitterInfos;
    }

    /**
     * 创建一个新的推文
     *
     * @param twitterInfo 推文信息
     * @param userId      拥有者的id
     */
    public static TwitterInfo createTwitter(long userId, TwitterInfo twitterInfo, String rootPath) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        User user = null;
        Twitter twitter = null;
        TwitterInfo reTwitterInfo = null;
        try {
            transaction.begin();

            user = session.get(User.class, userId);
            twitter = TwitterInfo.initTwitterByInfo(twitterInfo);
            twitter.setOwner(user);
            twitter.setTwitterId(TwitterDao.getMaxId(session) + 1);
            reTwitterInfo = TwitterInfo.createDetailInfo(twitter);
            session.persist(twitter);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }


        return reTwitterInfo;
    }
}
