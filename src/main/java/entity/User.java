package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by caopeihe on 2016-8-16.
 */
@SuppressWarnings("serial")
@Entity
@Table(name="user_t")
public class User implements java.io.Serializable{
        private Integer id;
        private String username;
        private String password;
        private Integer age;
        @Column(name="password",nullable=false)
        public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String code;
    @Transient
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public User()
    {
    }
    public User(Integer id, String username,String password ,Integer age) {
        super();
        this.id = id;
        this.age = age;
        this.username = username;
        this.password = password;
    }
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Column(name = "name", unique = false, nullable = false)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
