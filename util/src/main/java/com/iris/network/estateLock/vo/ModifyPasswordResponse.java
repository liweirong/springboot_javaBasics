package com.iris.network.estateLock.vo;

import lombok.Data;

/**
 * @author LIWEIRONG172
 * @version 1.0
 * @date 2021-3-30
 * @describtion
 */
@Data
public class ModifyPasswordResponse {

    private String id;
    private String username;
    private Integer usertype;
    private Integer userId;
    private String intelligentLockId;
    private Integer userattribute;
    private String temporarypassword;
    private Long starttime;
    private Long endtime;
    private Integer usetimes;
    private Integer enable;
    private Long createtime;
    private String taskId;
    private Integer status;
}
