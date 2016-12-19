package com;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caopeihe on 2016-3-31.
 */
public class UserTest {
    private UserService userService;

    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"
                ,"classpath:spring-hibernate.xml","classpath:spring-mvc.xml"});
        userService = (UserService) context.getBean("userService");
    }

    @Test
    public void addUser(){
            List<String> list = new ArrayList<String>();
            try{
                String sqls = "select * from tb_counts";
                list = userService.selectStatic(sqls);
            }catch(SQLException e){

            }
//        TbUser tbUser = new TbUser();
//        tbUser.setId(1);
//        tbUser.setName("cph");
//        System.out.println(userService.insertUser(tbUser));
//        User user = new User();
//        user.setNickname("你好");
//        user.setState(2);
//        System.out.println(userService.insertUser(user));
    }
}
