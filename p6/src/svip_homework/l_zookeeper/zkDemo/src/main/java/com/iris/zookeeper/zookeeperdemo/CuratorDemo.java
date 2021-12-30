package com.iris.zookeeper.zookeeperdemo;

import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**

 **/
public class CuratorDemo {

    public static void main(String[] args) throws Exception {
        List<AuthInfo> list=new ArrayList<>();
        AuthInfo authInfo=new AuthInfo("digest","u2:u2".getBytes());
        list.add(authInfo);
        CuratorFramework curatorFramework=
                CuratorFrameworkFactory.builder().
                        connectString("localhost:2181").
                        sessionTimeoutMs(5000).
                        retryPolicy(new ExponentialBackoffRetry
                                (1000,3)).
                        connectionTimeoutMs(4000).authorization(list).build();
        curatorFramework.start(); //表示启动.
        //创建
       create(curatorFramework);
        //修改
//        update(curatorFramework);
//        operatorWithAsync(curatorFramework);

//        authOperation(curatorFramework);
//        get(curatorFramework);

//        addNodeCacheListener(curatorFramework,"/first");
//        addPathChildCacheListener(curatorFramework,"/first");
//        System.in.read();
       // create(curatorFramework);
       // originApiTest();
    }

    private static String get(CuratorFramework curatorFramework) throws Exception {
        String rs=new String(curatorFramework.getData().forPath("/first_auth"));
        System.out.println(rs);
        return rs;
    }

    private static String create(CuratorFramework curatorFramework) throws Exception {

       String path=curatorFramework.create().
                creatingParentsIfNeeded().
                withMode(CreateMode.PERSISTENT).forPath("/fir1111","Hello Gupaao".getBytes());
        System.out.println("创建成功的节点： "+path);
        return path;
    }
    private static String update(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.setData().forPath("/first","Hello GuPaoEdu.cn".getBytes());
        return null;
    }
    //异步访问 | 同步（future.get()）
    //spring-boot-starter-data-redis-reactor
    //redisson
    private static String operatorWithAsync(CuratorFramework curatorFramework) throws Exception {
        CountDownLatch countDownLatch=new CountDownLatch(1);
        curatorFramework.create().creatingParentsIfNeeded().
                withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println(Thread.currentThread().getName()+":"+event.getResultCode());
                countDownLatch.countDown();
            }
        }).forPath("/second","second".getBytes());
        //TODO ...
        System.out.println("before");
        countDownLatch.await(); //阻塞
        System.out.println("after");
        return "";
    }

    private static String authOperation(CuratorFramework curatorFramework) throws Exception {
        List<ACL> acls=new ArrayList<>();
        ACL acl=new ACL(ZooDefs.Perms.CREATE| ZooDefs.Perms.DELETE,new Id("digest", DigestAuthenticationProvider.generateDigest("u1:u1")));
        ACL acl1=new ACL(ZooDefs.Perms.ALL,new Id("digest", DigestAuthenticationProvider.generateDigest("u2:u2")));
        acls.add(acl);
        acls.add(acl1);
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                withACL(acls).forPath("/first_auth","123".getBytes());
        return null;
    }

    /**
     * PathChild
     * Node
     * Tree
     */
    private static void addNodeCacheListener(CuratorFramework curatorFramework,String path) throws Exception {
        NodeCache nodeCache=new NodeCache(curatorFramework,path,false);
        NodeCacheListener nodeCacheListener=new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Receive Node Changed");
                System.out.println(""+nodeCache.getCurrentData().getPath()+"->"+new String(nodeCache.getCurrentData().getData()));
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }
    private static void addPathChildCacheListener(CuratorFramework curatorFramework,String path) throws Exception {
        PathChildrenCache childrenCache=new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener childrenCacheListener=new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("子节点事件变更的回调");
                ChildData childData=pathChildrenCacheEvent.getData();
                System.out.println(childData.getPath()+"-"+new String(childData.getData()));
            }
        };
        childrenCache.getListenable().addListener(childrenCacheListener);
        childrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }
    ZooKeeper zooKeeper;
    public void originApiTest() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper=new ZooKeeper("192.168.8.129:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //表示连接成功之后，会产生的回调时间
            }
        });
        Stat stat=new Stat();
        zooKeeper.getData("/first", new DataWatchListener(),stat); //针对当前节点

      /*  zooKeeper.exists();  //针对当前节点
        zooKeeper.getChildren();  //针对子节点的监听*/
    }
    class DataWatchListener implements Watcher{
        @Override
        public void process(WatchedEvent watchedEvent) {
            String path=watchedEvent.getPath();
            //再次注册监听
            try {
                zooKeeper.getData(path,this,new Stat());
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
