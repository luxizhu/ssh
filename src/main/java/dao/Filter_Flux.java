package dao;

import org.hibernate.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by caopeihe on 2016-8-18.
 */
public class Filter_Flux extends BaseDao{
    public ResultSet selectStatic(String sql) {
        Connection conn = getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(conn,stmt,null);
        }
        return rs;
    }

    public boolean executeUpdate(String sql){
        Connection conn = getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean flag = true;
        try{
            stmt = conn.prepareStatement(sql);
           int i = stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(conn,stmt,null);
        }
        return true;
    }
}
