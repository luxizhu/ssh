package dao.Impl;

import dao.BaseDao;
import dao.SongDao;
import entity.Song;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by caopeihe on 2016-12-30.
 */
@Component
public class SongDaoImpl extends BaseDao implements SongDao {

    public void saveSong(Song song){
        getSession().save(song);
        System.out.println("+++++++++++++++++++++++save song+++++++++++++++++++++++");
    }
}
