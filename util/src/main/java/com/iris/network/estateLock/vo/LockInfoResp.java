package com.iris.network.estateLock.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author iris
 * @date 2023/2/16
 */
@Getter
@Setter
public class LockInfoResp implements Serializable {
    private static final long serialVersionUID = 2510256865864085587L;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;

    private String username;

    private String password;

    private String status;

    public LockInfoResp(String id, Integer userId, String username, String password, String status) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "{userId:'" + userId + '\'' +
                        ",id:'" + id + '\'' +
                        ",username:'" + username + '\'' +
                        ",password:'" + password + '\'' +
                        ",status:'" + status + '\'' +
                        '}';
    }
}
