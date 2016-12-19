package dao.Impl;

import dao.AlbumDao;
import entity.AlbumForm;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by caopeihe on 2016-9-7.
 */
@Repository("album")
public class AlbumDaoImpl implements AlbumDao{
    @Autowired
    private SessionFactory sessionFactory;

    public List<AlbumForm> getAlbumList(){
        String sql = "select * from tb_albumtype order by typename";
        List<AlbumForm> list = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
        return list;
    }
}
