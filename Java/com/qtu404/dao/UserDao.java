package com.qtu404.dao;

import com.qtu404.enetity.User;
import org.hibernate.Session;

import org.hibernate.Query;

import java.util.List;

public class UserDao {
    /**
     * 通过账户名找到一个用户实体
     *
     * @param userNo 用户的唯一标识
     * @return 一个用户实体
     * @author wildhunt_unique
     */
    public static User getUserByNo(Session session, String userNo) {
        User user = null;

        String hql = "select u from " + User.class.getName() + " u where u.userNo = :userNo";
        Query<User> query = session.createQuery(hql);
        query.setParameter("userNo", userNo);
        user = query.getSingleResult();

        return user;
    }

    /**
     * 通过邮箱找到一个用户实体
     *
     * @param Email 用户的邮箱
     * @return 一个用户实体
     * @author wildhunt_unique
     */
    public static User getUserByEmail(Session session, String Email) {
        User user = null;

        String hql = "select u from " + User.class.getName() + " u where u.userEmail = :userEmail";
        Query<User> query = session.createQuery(hql);
        query.setParameter("userEmail", Email);
        user = query.getSingleResult();

        return user;
    }

    /**
     * 通过邮箱和密码匹配用户
     *
     * @author wildhunt_unique
     */
    public static User getUserByLogin(Session session, String userEamil, String userPassword) {
        User user = null;

        String hql = "select u from " + User.class.getName() + " u where u.userPassword = :userPassword and u.userEmail = :userEmail";
        Query<User> query = session.createQuery(hql);
        query.setParameter("userPassword", userPassword);
        query.setParameter("userEmail", userEamil);
        user = query.getSingleResult();

        return user;
    }

    /**
     * 找到所有用户
     *
     * @author wildhunt_unique
     */
    public static List<User> findAllUser(Session session) {
        List<User> users = null;

        String hql = "select u from " + User.class.getName() + " u";
        Query<User> query = session.createQuery(hql);
        users = query.getResultList();

        return users;
    }

    /**
     * 找到当前用户的最大Id值
     *
     * @author wildhunt_unique
     */
    public static long getMaxId(Session session) {
        Number maxId = null;

        String hql = "select max(u.userId) from " + User.class.getName() + " u";
        Query<Number> query = session.createQuery(hql);
        maxId = query.getSingleResult();
        if (maxId == null){
            maxId = 100000L;
        }

        return maxId.longValue();
    }
}
