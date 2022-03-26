package com.iris.zookeeper.zookeeperdemo.leader;

import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

/**
 * @author iris
 * @date 2022/3/22
 */
public class LeaderLatch implements LeaderLatchListener {
    @Override
    public void isLeader() {
        System.out.println("选为主！！！！！！");
    }

    @Override
    public void notLeader() {
        System.out.println("未选为主");
    }
}
