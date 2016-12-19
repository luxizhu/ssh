package controller;

import dao.BaseDao;
import dao.Filter_Flux;
import filter.Filter_flux;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by caopeihe on 2016-8-17.
 */
@Controller
@RequestMapping("/stat")
public class Stat_Flux extends BaseDao{

    public List<String> read_Flux() {
        Filter_Flux ff = new Filter_Flux();
        List<String> list = new ArrayList<String>();
        Connection conn = getConn();
        PreparedStatement stmt = null;
        try {
            String sqls = "select * from tb_counts";
            stmt = conn.prepareStatement(sqls);
            ResultSet rs =  stmt.executeQuery();
            rs.next();
            String counts = rs.getString("counts");
            String last_ip = rs.getString("last_ip");
            String last_date = rs.getString("last_data");
            String str_pic = Stat_count(counts);
            list.add(last_ip);
            list.add(last_date);
            list.add(str_pic);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String Stat_count(String counts) {
        String zeno = "";
        int length = counts.length();
        int i = 0;
        while (i < 10 - length) {
            zeno += "<img src='images\\0.jpg'/>";
            i++;
        }
        String count = "";
        int ii = 0;
        while (ii < length) {
            count += "<img src='images\\" + counts.charAt(ii) + ".jpg'/>";
            ii++;
        }
        String backstr = zeno + count;
        return backstr;
    }

    public void write_Flux(String ip,String dateTime){
        Filter_Flux ff = new Filter_Flux();
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try{
            date = sf.parse(dateTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        String sql = "insert into tb_counts (counts,last_ip,last_data) values (" +
                +0+","+ip+","+date+")";
        ff.executeUpdate(sql);
    }
}
