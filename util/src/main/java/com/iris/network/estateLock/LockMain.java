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
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author iris
 * @date 2023/2/15
 */
@Slf4j
public class LockMain {
    public static Map<Integer, String> password = Maps.newHashMap();
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
//    private static String deviceUUID = "c44e01518194bc65a7385b0a9f08998f";

    private static List<Integer> admin = Lists.newArrayList(1, 2, 3);

    public static void main(String[] args) throws Exception {
        Map<String, List<LockInfoResp>> result = Maps.newLinkedHashMap();
        LockInfo.map.forEach((address, deviceUUID) -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockService lockService = new LockServiceImpl();
            LockUserListResponseVO lockUserList = lockService.getLockUserList(deviceUUID);
//            System.out.println("#####" + JSONObject.toJSONString(lockUserList));
            if (lockUserList.getResult() != 1) {
                System.out.println("异常数据：+" + deviceUUID + " result:" + JSONObject.toJSONString(lockUserList));
                return;
            }
            List<GetLockUserListResponse> rows = JSONObject.parseArray(JSONObject.toJSONString(lockUserList.getRows()), GetLockUserListResponse.class);

//            System.out.println("=============================address:" + address + " size:" + rows.size());
            List<Integer> adminNum = new ArrayList<>();
            List<LockInfoResp> list = new ArrayList<>();
            rows.forEach(lockUser -> {
                String decrypt = AesUtils.decrypt(lockUser.getTemporaryPassword(), deviceUUID);
                if (lockUser.getUsertype() == 2 && (admin.contains(lockUser.getUserId())
                        || systemPassword.contains(decrypt))
                ) {
                    // 管理员密码用户
//                    System.out.println(lockUser.getUserId() + " lockId:" + lockUser.getId() + " - 管理员密码: " + decrypt + " 锁状态:" + lockStatus.get(lockUser.getStatus()));
//                LockResponseVO lockResponseVO = lockService.modifyLockPassword(deviceUUID, lockUser.getId(), password.get(lockUser.getUserId()));
                    // 需要修改
                    list.add(new LockInfoResp(lockUser.getId(), lockUser.getUserId(), lockUser.getUsername(), decrypt, lockStatus.get(lockUser.getStatus())));
                    adminNum.add(lockUser.getUserId());

//                    if (lockUser.getUserId() == 12) {
//                    LockResponseVO lockResponseVO = lockService.modifyLockPassword(deviceUUID, lockUser.getId(), "230108");
//                    }
//                System.out.println(lockUser.getUserId() + " lockId:" + lockUser.getId() + " - 管理员密码: " + decrypt);
                } else {
                    //   System.out.println("非管理员密码" + lockUser.getUserId() + " lockId:" + lockUser.getId() + " : " + decrypt + " 锁状态:" + lockStatus.get(lockUser.getStatus()));
                    // 删除
//                if (lockUser.getUserId() == 89) {
//                    LockResponseVO delete = lockService.deleteByLockUserId(deviceUUID, lockUser.getId());
//                    if (!delete.isSuccess()) {
//                        System.out.println("deviceUUID:" + deviceUUID + " lockUserId:" + lockUser.getUserId() + "-锁用户删除失败 message:" + delete.getMessage());
//                    }
//                }

                }
            });
            result.put(address, list);
//            List<Integer> notContain = admin.stream().filter(o -> !adminNum.contains(o)).collect(Collectors.toList());
//            System.out.println(address + " 目前存在的管理员密码:" + adminNum + " 缺失的管理员密码为：" + notContain);
//            System.out.println("=============================end address:" + address);

        });

        Set<String> sumone = Sets.newHashSet();
        List<LockInfoResp> sumoneERROR = Lists.newArrayList();
        Set<String> sumtwo = Sets.newHashSet();
        Set<String> sumthree = Sets.newHashSet();


        List<String> sqlList = new ArrayList<>(512);
        result.forEach((address, list) -> {
            System.out.println(address);
            list.forEach(info -> {
                System.out.println("\t" + info);
                Integer userId = info.getUserId();
                if (userId == 1) {
                    if (!sumone.add(address + "|" + userId)) {
                        sumoneERROR.add(info);
                    }
                }
                if (userId == 2) {
                    sumtwo.add(address + "|" + userId);
                }
                if (userId == 3) {
                    sumthree.add(address + "|" + userId);
                }

                String sql = "INSERT INTO `apartment_temp_lock_user_info_lwr`" +
                        "(`address`, `deviceUUID`, `lock_user_id`,`lock_id`,`username`,`psd`,`status`)" +
                        " VALUES ('" + address + "', '" + LockInfo.map.get(address) + "', " + userId + ", '" + info.getId() + "','" + info.getUsername() + "','" + info.getPassword() + "','" + info.getStatus() + "');";
                sqlList.add(sql);
            });
        });


        System.out.println("输入门锁数：" + LockInfo.map.size() + " - 查询门锁总数量：" + result.size());
        System.out.println("门锁1异常数据：" + JSONObject.toJSONString(sumoneERROR));
        System.out.println("门锁缺失1的数量：" + (result.size() - sumone.size()));
        System.out.println("门锁缺失2的数量：" + (result.size() - sumtwo.size()));
        System.out.println("门锁缺失3的数量：" + (result.size() - sumthree.size()));

        System.out.println("======================================");
        sqlList.forEach(System.out::println);
    }


}
