package service;

import dao.SongDao;
import entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caopeihe on 2016-12-30.
 */
@Service
public class SongService {
    @Autowired
    private SongDao songDao;

    public void saveSong(Song song){
        songDao.saveSong(song);
    }

}
