package com.iris.network.estateLock.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author iris
 * @date 2023/2/15
 */
@Getter
@Setter
public class GetLockUserListResponse implements Serializable {
    private static final long serialVersionUID = 364477278676419814L;
    private String id;
    private String username;// 超级管理员密码02,
    private Integer usertype;// 2
    private Integer userId;//;// 0,
    private String temporaryPassword;//null,
    private String lockId;//c44e01518194bc65a7385b0a9f08998f,
    private String ismanager;//true,
    private Long createtime;//1632911491000,
    private Integer activeminute;//0,
    private Boolean enable;//true,
    private Long starttime;//0,
    private Long endtime;
    private Long usetimes;//;//0,
    private Integer status;//;//0,
    private String taskId;//;//1b1b5832ee9b42c095de8ebf91a040e5,

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

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer userype) {
        this.usertype = userype;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public String getIsmanager() {
        return ismanager;
    }

    public void setIsmanager(String ismanager) {
        this.ismanager = ismanager;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Integer getActiveminute() {
        return activeminute;
    }

    public void setActiveminute(Integer activeminute) {
        this.activeminute = activeminute;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public Long getUsetimes() {
        return usetimes;
    }

    public void setUsetimes(Long usetimes) {
        this.usetimes = usetimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
