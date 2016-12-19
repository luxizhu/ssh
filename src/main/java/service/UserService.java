package service;

import dao.UserDao;
import entity.User;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by caopeihe on 2016-8-16.
 */
@Service("userService")
@Transactional
public class UserService
{
    //自动注入dao
    @Autowired
    private UserDao<User> userDao;

    public void addUser(User user)
    {
        userDao.save(user);
    }

    public List selectStatic(String sql) throws SQLException{
        return userDao.selectStatic(sql);
    }

    public boolean executeUpdate(String sql){
        return userDao.executeUpdate(sql);
    }

}
