package com.iris.rpc;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest  implements Serializable {

    private static final long serialVersionUID = 6983951120836944097L;
    private String className;

    private String methodName;

    private Object[] args;

    private Class[] type;

}
