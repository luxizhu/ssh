package dao;

import org.hibernate.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by caopeihe on 2016-8-16.
 */
public interface UserDao<T>
{
    public Serializable save(T o);

    public List selectStatic(String sql) throws SQLException;

    public boolean executeUpdate(String sql);
}
