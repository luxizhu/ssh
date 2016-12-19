package dao.Impl;

import dao.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by caopeihe on 2016-8-16.
 */
@Repository("userDao")
public class UserDaoImpl<T> implements UserDao<T> {
    //注入sessionfactory
    @Autowired
    private SessionFactory sessionFactory;

    public Serializable save(T o) {
//        Session session = sessionFactory.openSession();
//        return session.save(o);
        return sessionFactory.getCurrentSession().save(o);
    }

    public List selectStatic(String sql) throws SQLException {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return query.list();
    }

    public boolean executeUpdate(String sql){
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return query.executeUpdate()==1?true:false;
    }
}
