package service;

import dao.AllDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caopeihe on 2017-1-3.
 */
@Service
public class AllService {

    @Autowired
    private AllDao allDao;

}
