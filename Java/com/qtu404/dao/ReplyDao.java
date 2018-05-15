package com.qtu404.dao;

import com.qtu404.enetity.Reply;
import org.hibernate.Query;
import org.hibernate.Session;

public class ReplyDao {
    public static long getMaxId(Session session) {
        Number maxId = null;

        String hql = "select max(r.replyId) from " + Reply.class.getName() + " r";
        Query<Number> query = session.createQuery(hql);
        maxId = query.getSingleResult();
        if (maxId == null) {
            maxId = 100000L;
        }

        return maxId.longValue();
    }
}
