package com.iris.network.estateLock.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author iris
 * @date 2023/2/15
 */
@ToString
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 7283724938313423770L;

    @Getter
    @Setter
    private Integer result;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private T data;
}
