package com.iris.network.estateLock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一返回前端对象 <BR>
 * 类 名：ResponseVO <BR>
 * 创建人: sea <BR>
 * Email: PANGHAICANG034@pingan.com.cn <BR>
 * 时 间：2020-11-2 21:42 <BR>
 *
 * @version 1.0.0
 */
@ApiModel("返回结果")
public class LockUserListResponseVO<T> implements Serializable {
    private static final long serialVersionUID = 550318176777827993L;

    @ApiModelProperty("结果编码（0：Failed；1：Success；6XX：Custom Error")
    /**
     * 0	接口请求失败	接口请求失败
     * 1	接口请求失败	接口请求失败
     * 696	服务器系统错误	服务器系统错误
     * 1005	签名校验失败	签名校验失败
     * 1006	非法用户	非法用户
     * 1007	非法操作	非法操作
     */
    private Integer result;

    @ApiModelProperty("返回状态描述信息")
    private String message;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    private T rows;

    private Integer total;

    public LockUserListResponseVO() {
    }

    public LockUserListResponseVO(Integer result) {
        this.result = result;
        this.message = LockMsgCodeConstant.getMsgByCode(result);
    }

    public LockUserListResponseVO(Integer result, T body) {
        this.result = result;
        this.message = LockMsgCodeConstant.getMsgByCode(result);
        this.rows = body;
    }

    public LockUserListResponseVO(Integer result, T body, String message) {
        this.result = result;
        this.message = message;
        this.rows = body;
    }

    public boolean isSuccess() {
        return LockMsgCodeConstant.SUCCESS_CODE.equals(result);
    }

//    /**
//     * <BR>
//     * 方法名：buildResponseVO <BR>
//     * 创建人: sea <BR>
//     * Email: PANGHAICANG034@pingan.com.cn <BR>
//     * 时 间：2020-11-16 10:28 <BR>
//     *
//     * @param result:
//     * @param message:
//     * @return com.ipark.common.response.ResponseVO <BR>
//     * @since 1.0.0
//     */
//    private static LockResponseVO buildResponseVO(Integer result, String message) {
//        return buildResponseVO(result, message, null);
//    }
//
//    /**
//     * <BR>
//     * 方法名：buildResponseVO <BR>
//     * 创建人: sea <BR>
//     * Email: PANGHAICANG034@pingan.com.cn <BR>
//     * 时 间：2020-11-16 10:29 <BR>
//     *
//     * @param result:
//     * @param message:
//     * @param data:
//     * @return com.ipark.common.response.ResponseVO <BR>
//     * @since 1.0.0
//     */
//    private static <T> LockResponseVO buildResponseVO(Integer result, String message, T data) {
//        LockResponseVO<T> responseVO = new LockResponseVO<>();
//        responseVO.setResult(result);
//        responseVO.setMessage(message);
//        responseVO.setData(data);
//        return responseVO;
//    }
}
