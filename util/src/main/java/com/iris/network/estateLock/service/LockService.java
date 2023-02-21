package com.iris.network.estateLock.service;

import com.iris.network.estateLock.vo.GetLockUserListResponse;
import com.iris.network.estateLock.vo.LockResponseVO;
import com.iris.network.estateLock.vo.LockUserListResponseVO;
import com.iris.network.estateLock.vo.Response;

import java.util.List;

/**
 * @author iris
 * @date 2023/2/15
 */
public interface LockService {

    /**
     * 获取密码
     *
     * @param deviceUUID
     * @return
     */
    LockUserListResponseVO<GetLockUserListResponse> getLockUserList(String deviceUUID);

    /**
     * 根据锁用户id删除密码
     *
     * @param lockUserId
     * @return
     */
    LockResponseVO deleteByLockUserId(String deviceUUID, String lockUserId);

    /**
     * 删除管理员锁用户id重复的数据  ! 先别删
     * @param deviceUUID
     * @param lockUserId
     * @return
     */
    LockResponseVO deleteSameAdminLockUserId(String deviceUUID,String lockUserId);

    /**
     * 修改新密码
     *
     * @param deviceUUID
     * @param lockUserId
     * @param newPassword 新密码
     * @return
     */
    LockResponseVO modifyLockPassword(String deviceUUID, String lockUserId, String newPassword);

    /**
     * 只能下发普通密码，不支持管理员密码
     * @param deviceUUID
     * @param lockUserId
     * @param password
     * @return
     */
    LockResponseVO addBasePassword(String deviceUUID, String lockUserId, String password);
}
