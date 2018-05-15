package com.qtu404.dao;

import com.qtu404.enetity.Twitter;
import org.hibernate.Query;
import org.hibernate.Session;

public class TwitterDao {

    /**
     * 找推特的最大Id值
     * @author wildhunt_unique
     * */
    public static long getMaxId(Session session) {
        Number maxId = null;

        String hql = "select max(t.twitterId) from " + Twitter.class.getName() + " t";
        Query<Number> query = session.createQuery(hql);
        maxId = query.getSingleResult();

        if (maxId == null){
            maxId = 100000L;
        }

        return maxId.longValue();
    }
}
