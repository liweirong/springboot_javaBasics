package com.iris.network.estateLock.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.iris.network.estateLock.AesUtils;
import com.iris.network.estateLock.service.LockService;
import com.iris.network.estateLock.vo.GetLockUserListResponse;
import com.iris.network.estateLock.vo.LockResponseVO;
import com.iris.network.estateLock.vo.LockUserListResponseVO;
import com.iris.network.estateLock.vo.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static com.iris.network.estateLock.HttpClientUtils.post;

/**
 * @author iris
 * @date 2023/2/15
 */
public class LockServiceImpl implements LockService {
    public static String appid = "a22704a7c855471eb782b8ee3bacebc7";
    private static String secret = "9ba1465075e34cdb80d945f5d6f5b38a";
    private static String masterKey = "77a34f924ad54453a3d34c5a14b3c9fa";
    private static String categoryId = "998c6c1be5374bb18b8a967e90b5af52";


    private static String PRD_DOMAIN = "https://open.fcsmartlock.com";
    private static final String MODIFY_URL = "/open/api/wifi_device/modifyPassword/v1";
    // 删除门锁用户
    private static final String DEL_LOCK_USER = "/open/api/wifi_device/delLockUser/v1";
    // 删除门锁用户不下发任务
    private static final String DEL_LOCK_USER_NO_TASK = "/open/api/wifi_device/delLockUserData/v1";
    private static final String ADD_PASSWORD = "/open/api/wifi_device/addPassword/v1";
    // 批量删除，一次最多八个
    private static final String BATCH_DEL_LOCK_USER = "/open/api/wifi_device/batchDeleteUser/v1";
    private static final String GET_LOCK_USER_LIST = "/open/api/device/lockUsers/getLockUserList/v1";

    private static final String charset = "UTF-8";

    @Override
    public LockUserListResponseVO<GetLockUserListResponse> getLockUserList(String deviceUUID) {
        String GET_LOCK_USER_LIS = PRD_DOMAIN + GET_LOCK_USER_LIST + "?appId=" + appid + "&deviceUUID=" + deviceUUID + "&userType=2&rows=100";
        JSONObject userList = post(GET_LOCK_USER_LIS, null, "application/x-www-form-urlencoded", charset, 10000, 10000);
        // https://open.fcsmartlock.com/open/api/device/lockUsers/getLockUserList/v1?appId=a22704a7c855471eb782b8ee3bacebc7&deviceUUID=c44e01518194bc65a7385b0a9f08998f&userType=2&rows=100
//        System.out.println("\ngetLockUserList response: "+userList.toJSONString()+"\n");
        LockUserListResponseVO response = JSONObject.parseObject(userList.toJSONString(), LockUserListResponseVO.class);
        return response;
    }

    @Override
    public LockResponseVO deleteByLockUserId(String deviceUUID, String lockUserId) {
        String GET_LOCK_USER_LIS = PRD_DOMAIN + DEL_LOCK_USER + "?appId=" + appid + "&deviceUUID=" + deviceUUID + "&id=" + lockUserId;
        JSONObject userList = post(GET_LOCK_USER_LIS, null, "application/x-www-form-urlencoded", charset, 10000, 10000);
        LockResponseVO response = JSONObject.parseObject(userList.toJSONString(), LockResponseVO.class);
        System.out.println(DEL_LOCK_USER + " : " + userList.toJSONString());
        return response;
    }

    @Override
    public LockResponseVO deleteSameAdminLockUserId(String deviceUUID, String lockUserId) {
        String GET_LOCK_USER_LIS = PRD_DOMAIN + DEL_LOCK_USER_NO_TASK + "?appId=" + appid + "&deviceUUID=" + deviceUUID + "&id=" + lockUserId;
        JSONObject userList = post(GET_LOCK_USER_LIS, null, "application/x-www-form-urlencoded", charset, 10000, 10000);
        LockResponseVO response = JSONObject.parseObject(userList.toJSONString(), LockResponseVO.class);
        System.out.println(DEL_LOCK_USER + " : " + userList.toJSONString());
        return response;
    }


    @Override
    public LockResponseVO modifyLockPassword(String deviceUUID, String lockUserId, String newPassword) {
        String GET_LOCK_USER_LIS = PRD_DOMAIN + MODIFY_URL + "?appId=" + appid + "&deviceUUID=" + deviceUUID + "&id="
                + lockUserId + "&passwordInfo=" + AesUtils.encrypt(newPassword, deviceUUID);
        JSONObject userList = post(GET_LOCK_USER_LIS, null, "application/x-www-form-urlencoded", charset, 10000, 10000);
        LockResponseVO response = JSONObject.parseObject(userList.toJSONString(), LockResponseVO.class);
        System.out.println(MODIFY_URL + " : " + userList.toJSONString());
        return response;
    }

    @Override
    public LockResponseVO addBasePassword(String deviceUUID, String lockUserId, String password) {
        String GET_LOCK_USER_LIS = PRD_DOMAIN + ADD_PASSWORD + "?appId=" + appid + "&deviceUUID=" + deviceUUID + "&id="
                + lockUserId + "&password=" + AesUtils.encrypt(password, deviceUUID) + "&type=1&userName=管理密码";
        JSONObject userList = post(GET_LOCK_USER_LIS, null, "application/x-www-form-urlencoded", charset, 10000, 10000);
        LockResponseVO response = JSONObject.parseObject(userList.toJSONString(), LockResponseVO.class);
        System.out.println(MODIFY_URL + " : " + userList.toJSONString());
        return response;
    }

    public static void main(String[] args) {
        List<Integer> admin = Lists.newArrayList(1, 2, 3);
        List<Integer> list2 = Lists.newArrayList(1, 2);
        List<Integer> list3 = Lists.newArrayList(3);
        List<Integer> list4 = Lists.newArrayList();

        List<Integer> ones = admin.stream().filter(o -> !list2.contains(o)).collect(Collectors.toList());
        System.out.println("admin中不被adminNum包含的元素：" + ones);
        List<Integer> two = admin.stream().filter(o -> !list3.contains(o)).collect(Collectors.toList());
        System.out.println("admin中不被adminNum包含的元素：" + two);
        List<Integer> three = admin.stream().filter(o -> !list4.contains(o)).collect(Collectors.toList());
        System.out.println("admin中不被adminNum包含的元素：" + three);
    }

}
