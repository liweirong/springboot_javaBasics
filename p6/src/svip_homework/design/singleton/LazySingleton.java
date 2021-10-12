package svip_homework.design.singleton;


import lombok.extern.slf4j.Slf4j;

/**
 * @author iris
 * @date 2021/10/11
 * 懒汉式单例
 * 优点：资源利用率高，不执行getInstance()就不被实例
 * 缺点：第一次加载时较慢，每次调用getInstance方法都会进行同步，消耗不必要的资源。
 */
@Slf4j
public class LazySingleton {

    public static void main(String[] args) {
        // 测试创建100线程池对象
        for (int i = 0; i < 100; i++) {
            new Thread(() -> log.info("获取一个懒汉式单例：threadName:{} \t {}。", Thread.currentThread().getName(), LazySingletonObj.getInstance())).start();
        }

    }
}


class LazySingletonObj {

    private static volatile LazySingletonObj lazySingletonObj;

    private LazySingletonObj() {
        if (null != lazySingletonObj) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    /**
     * dcl 可被 反序列化暴力破解
     *
     * @return
     */
    public static LazySingletonObj getInstance() {
        if (lazySingletonObj == null) {
            synchronized (LazySingletonObj.class) {
                if (lazySingletonObj == null) {
                    // jvm生成对象非原子，采用volatile修饰 对所有线程可见性
                    lazySingletonObj = new LazySingletonObj();
                }

            }
        }
        return lazySingletonObj;
    }
}