package Test;

import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;
import com.qtu404.dao.UserDao;
import com.qtu404.enetity.HibernateUtil;
import com.qtu404.enetity.Twitter;
import com.qtu404.enetity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Set;

public class DaoTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            long id = 100003L;
            long userId = 100001L;
            User user = session.get(User.class,userId);
            Twitter twitter = session.get(Twitter.class,id);

            Set<Twitter> twitters = user.getLikeTwitters();

            for (Twitter twitterEach: twitters){
                System.out.println(twitterEach.getTwitterId());
            }

            user.getLikeTwitters().add(twitter);

            System.out.println("after add");

           twitters = user.getLikeTwitters();

            for (Twitter twitterEach: twitters){
                System.out.println(twitterEach.getTwitterId());
            }


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

    }
}
