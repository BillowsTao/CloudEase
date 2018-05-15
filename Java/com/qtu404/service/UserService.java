package com.qtu404.service;

import com.qtu404.common.Config;
import com.qtu404.common.FileManage;
import com.qtu404.common.ImageDeal;
import com.qtu404.dao.UserDao;
import com.qtu404.beans.UserInfo;
import com.qtu404.enetity.HibernateUtil;
import com.qtu404.enetity.Twitter;
import com.qtu404.enetity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*

                        _ooOoo_
                       o8888888o
                       88" . "88
                       (| -_- |)
                        O\ = /O
                    ____/`---'\____
                  .   ' \\| |// `.
                   / \\||| : |||// \
                 / _||||| -:- |||||- \
                   | | \\\ - /// | |
                 | \_| ''\---/'' | |
                  \ .-\__ `-` ___/-. /
               ___`. .' /--.--\ `. . __
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

                领导每天新想法，天天改，日日忙。
                   相顾无言，惟有泪千行。
               每晚灯火阑珊处，夜难寐，加班狂。
 */


/**
 * 账户服务
 *
 * @author wildhunt_unique
 */
public class UserService {


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


    public static ArrayList<UserInfo> findStarInfo(long userId) {
        ArrayList<UserInfo> stars = new ArrayList<>(0);
        User loginUser = null;
        Set<User> stars_Set = null;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            loginUser = session.get(User.class, userId);
            stars_Set = loginUser.getStars();
            for (User starEach : stars_Set) {
                UserInfo userInfo = UserInfo.createUserInfo(starEach);
                stars.add(userInfo);
            }

            transaction.commit();

        } catch (Exception e) {

            e.printStackTrace();
            transaction.rollback();
        }

        return stars;
    }

    /**
     * 修改头像
     *
     * @param userId   修改图片的用户id
     * @param imgStr   图片的base64编码
     * @param rootPath 项目所在绝对物理路径
     * @return 状态码 success_08
     */
    public static String modifyAvator(long userId, String imgStr, String rootPath) {
        String stateCode = "error";
        String imgPath = FileManage.getAvatorPath(rootPath, userId);
        ImageDeal.GenerateImage(imgStr, imgPath);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            User user = session.get(User.class, userId);
            user.setUserHead(FileManage.getAvatorUrl(userId));

            transaction.commit();
            stateCode = "success_08";
        } catch (Exception e) {
            stateCode = "error_08";
            e.printStackTrace();
            transaction.rollback();
        }

        return stateCode;
    }

    /**
     * 邮箱是否被使用过
     *
     * @return ture 没有被使用过 false被使用过
     */
    public static boolean iSsameEmail(String email) {
        boolean flag = false;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        User user = null;
        try {
            transaction.begin();

            user = UserDao.getUserByEmail(session, email);
            if (user != null) {
                flag = false;
            } else flag = true;

            transaction.commit();

        } catch (Exception e) {
            flag = true;
            e.printStackTrace();
            transaction.rollback();
        }

        return flag;
    }

    /**
     * 用户名是否被使用过
     *
     * @return ture 没有被使用过 false被使用过
     */
    public static boolean iSsameNo(String userNo) {
        boolean flag = false;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        User user = null;
        try {
            transaction.begin();

            user = UserDao.getUserByNo(session, userNo);
            if (user != null) {
                flag = false;
            } else flag = true;

            transaction.commit();

        } catch (Exception e) {
            flag = true;
            e.printStackTrace();
            transaction.rollback();
        }

        return flag;
    }

    /**
     * 取关
     *
     * @param followNo 粉丝的账户号
     * @param starNo   被关注者的账户号
     * @return 状态码
     */
    public static String removeStar(String followNo, String starNo) {
        String stateCode = "error";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        User follow = null;
        User star = null;

        try {
            transaction.begin();

            follow = UserDao.getUserByNo(session, followNo);
            star = UserDao.getUserByNo(session, starNo);
            Set<User> stars = follow.getStars();

            for (User user : stars) {
                if (user.getUserId() == star.getUserId()) {
                    stars.remove(user);
                }
            }

            transaction.commit();
            stateCode = "success_03";
        } catch (Exception e) {
            stateCode = "error_03";
            e.printStackTrace();
            transaction.rollback();
        }

        return stateCode;
    }

    /**
     * 让一个用户关注另一个用户
     *
     * @param followNo 粉丝的账户号
     * @param starNo   被关注者的账户号
     * @return 状态码
     */
    public static String addStar(String followNo, String starNo) {
        String stateCode = "error";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        User follow = null;
        User star = null;

        try {
            transaction.begin();

            follow = UserDao.getUserByNo(session, followNo);
            star = UserDao.getUserByNo(session, starNo);

            follow.getStars().add(star);

            transaction.commit();
            stateCode = "success_02";
        } catch (Exception e) {
            stateCode = "error_02";
            e.printStackTrace();
            transaction.rollback();
        }

        return stateCode;
    }

    /**
     * 登陆账户
     *
     * @param userEmail    邮箱
     * @param userPassword 密码
     * @return 用户信息类 为空就是没有找到
     * @author wildhunt_unique
     */
    public static UserInfo userLogin(String userEmail, String userPassword) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        UserInfo userInfo = null;
        User user = null;

        try {
            transaction.begin();

            user = UserDao.getUserByLogin(session, userEmail, userPassword);
            userInfo = UserInfo.createUserInfo(user);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return userInfo;
    }

    /**
     * 找到所有用户信息
     *
     * @author wildhunt_unique
     */
    public static ArrayList<UserInfo> findAllUser() {
        ArrayList<UserInfo> userInfos = null;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();


        List<User> users = null;

        try {
            transaction.begin();

            users = UserDao.findAllUser(session);
            userInfos = UserInfo.createUserInfo(users);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return userInfos;
    }

    /**
     * 通过账户名得到一个用户
     *
     * @param userNo 账户名
     * @return 对应的账户信息
     */
    public static UserInfo getUserByNo(String userNo) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        UserInfo userInfo = null;
        User user = null;

        try {
            transaction.begin();

            user = UserDao.getUserByNo(session, userNo);
            userInfo = UserInfo.createUserInfo(user);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return userInfo;
    }

    /**
     * 创建一个新的用户
     *
     * @param userInfo 一个用户信息类
     * @author wildhunt_unique
     */
    public static String createUser(UserInfo userInfo, String rootPath) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        String stateCode = "error";

        User user = null;

        try {
            transaction.begin();

            user = UserInfo.initUserByInfo(userInfo);
            user.setUserId(UserDao.getMaxId(session) + 1);
            user.setUserHead(Config.defaultAvatorUrl);
            session.persist(user);

            transaction.commit();
            FileManage.createUserAvatorFIle(rootPath, user.getUserId());
            stateCode = "success_01";
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            stateCode = "error_01";
        }

        return stateCode;
    }
}
