package com.iris.network.estateLock;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.iris.network.estateLock.service.LockService;
import com.iris.network.estateLock.service.impl.LockServiceImpl;
import com.iris.network.estateLock.vo.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author iris
 * @date 2023/2/15
 */
@Slf4j
public class ChangePsdMain {
    private static Map<Integer, String> password = Maps.newHashMap();
    private static List<String> systemPassword = Lists.newArrayList();
    private static Map<String, String> systemModifyPassword = Maps.newHashMap();

    private static Map<Integer, String> lockStatus = Maps.newHashMap();

    static {
        // old 密码
        password.put(1, "230109");
        password.put(2, "230110");
        password.put(3, "230112");


        // new 密码
//        password.put(1, "230109");
//        password.put(2, "230110");
//        password.put(3, "230112");

        systemPassword.add("230109");
        systemPassword.add("230110");
        systemPassword.add("230112");

        systemModifyPassword.put("230109", "");
        systemModifyPassword.put("230110", "");
        systemModifyPassword.put("230112", "");


        lockStatus.put(0, "新创建待下发");
        lockStatus.put(1, "修改中");
        lockStatus.put(2, "删除中");
        lockStatus.put(3, "启用");
        lockStatus.put(4, "禁用");
        lockStatus.put(5, "启用中");
        lockStatus.put(6, "禁用中");
        lockStatus.put(10, "下发成功");
        lockStatus.put(11, "下发失败，用户已满");
        lockStatus.put(12, "下发失败，密码已存在 ");
        lockStatus.put(13, "下发失败 ");
        lockStatus.put(20, " 修改成功 ");
        lockStatus.put(21, "修改失败，密码已存在 ");
        lockStatus.put(22, "修改失败 ");
        lockStatus.put(31, "删除失败 ");
    }

    // 07号楼	3层	302
    private static String deviceUUID = "fb348c6f219340b3233ac1138d7a045a";
    private static List<Integer> admin = Lists.newArrayList(1, 2, 3);

    public static void main(String[] args) throws Exception {
        LockService lockService = new LockServiceImpl();
        LockResponseVO lockUserList = lockService.modifyLockPassword(deviceUUID, "e396191009724177af9ffaa27bc2b52b", password.get(1));
        System.out.println("#####" + JSONObject.toJSONString(lockUserList));
        LockResponseVO lockUserList2 = lockService.modifyLockPassword(deviceUUID, "e3bc5cff271242cabce8b1a2ca564d75", password.get(2));
        System.out.println("#####" + JSONObject.toJSONString(lockUserList2));
        LockResponseVO lockUserList3 = lockService.modifyLockPassword(deviceUUID, "e50d2000d2d745c0a55c32f9b6a1ec8e", password.get(3));
        System.out.println("#####" + JSONObject.toJSONString(lockUserList3));

        LockUserListResponseVO lockUserLis = lockService.getLockUserList(deviceUUID);
            System.out.println("#####@:" + JSONObject.toJSONString(lockUserLis));

    }


}
