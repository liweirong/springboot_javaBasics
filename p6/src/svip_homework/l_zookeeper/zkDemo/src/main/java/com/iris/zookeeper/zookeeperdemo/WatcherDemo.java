package com.iris.zookeeper.zookeeperdemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class WatcherDemo implements Watcher {
    static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();
    static {
        try {
            zooKeeper = new ZooKeeper("192.168.8.129:2181", 10000,new WatcherDemo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent event) {
        System.out.println("监听到节点事件---------------------------------------eventType:"+event.getType());
        try {
            zooKeeper.exists(event.getPath(),true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        if(event.getType()==Event.EventType.NodeDataChanged){
//            try {
//                zooKeeper.exists(event.getPath(),true);
//                //输出更改之后的值
//                byte [] bytes = zooKeeper.getData(event.getPath(),true,stat);
//                System.out.println(new String(bytes));
//            } catch (KeeperException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException, KeeperException {
        String path = "/watcher";
        //没有 /watcher 则创建 并赋值为0
        if (zooKeeper.exists(path, true) == null) {
            zooKeeper.create("/watcher", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        Thread.sleep(1000);
        System.out.println("-----------");
        //true表示使用zookeeper实例中配置的watcher
//        Stat stat=zooKeeper.exists(path,true);


        System.in.read();
    }
}