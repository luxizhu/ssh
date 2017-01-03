package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.sql.*;

/**
 * Created by caopeihe on 2016-8-18.
 */
public class BaseDao {
    public static final String url = "jdbc:mysql://127.0.0.1/myweb";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "root";

    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;

    @Resource
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public BaseDao(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public BaseDao(){}

    public Connection getConn(){
        try{
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(Connection conn,PreparedStatement stmt, ResultSet rs) {
        try {
            if(conn!=null){
                conn.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
