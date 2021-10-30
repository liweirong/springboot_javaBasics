package com.iris.rcp.util;


import lombok.Data;

import java.io.Serializable;

/**
 * 封装好的的请求参数
 */
@Data
public class MyRpcRequest implements Serializable {

    private static final long serialVersionUID = -1328294104816361382L;
    private String className;

    private String methodName;

    private Object[] args;

    private Class[] type;

}
