package com.iris.network.estateLock.vo;

import lombok.Data;

public class LockMsgCodeConstant {

    public  static Integer SUCCESS_CODE=1;
    public  static String SUCCESS_MSG="SUCCESS";


    public static String getMsgByCode(Integer result) {
        return SUCCESS_MSG;
    }
}
