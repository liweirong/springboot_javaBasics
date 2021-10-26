package svip_homework.a_design.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author iris
 * @date 2021/10/11
 * 优点
 * 1.线程安全
 * 2.在类加载的同时已经创建好一个静态对象，调用时反应速度快
 * <p>
 * 缺点
 * 资源效率不高，可能getInstance()永远不会执行到，但执行该类的其他静态方法或者加载了该类（class.forName)，那么这个实例仍然初始化
 */
@Slf4j
public class HungrySingleton {

    public static void main(String[] args) {
        // 测试创建100线程池对象
        for (int i = 0; i < 100; i++) {
            new Thread(() -> log.info("获取一个饿汉式单例：threadName:{} \t {}。", Thread.currentThread().getName(), HungrySingletonObj.getInstance())).start();
        }
    }

}

class HungrySingletonObj {

    private HungrySingletonObj() {
        if (null != instance) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    private static final HungrySingletonObj instance = new HungrySingletonObj();

    public static synchronized HungrySingletonObj getInstance() {
        return instance;
    }
}
