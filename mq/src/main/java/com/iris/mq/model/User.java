package com.iris.mq.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author iris
 * @date 2019/8/20
 */
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -4495472493196332071L;

    public User(String userId, String userName, String passWord){
        setUserId(userId);
        setUserName(userName);
        setPassWord(passWord);
    }
    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String passWord;

}
