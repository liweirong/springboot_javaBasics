package svip_homework.design.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author iris
 * @date 2021/10/11
 * 注册式单例 - 1 *枚举* 2 容器式
 *
 * 优点：这种方式不仅能解决多线程同步问题，而且能防止反序列化重新创建新的对象，
 * 因为自身的类没有无参构造方法才导致的异常，并不能说单例枚举避免了反射攻击
 *
 * jdk1.5中才加入enum特性，所以不常用
 */
@Slf4j
public class EnumSingleton {
    public static void main(String[] args) {
        // 测试创建100线程池对象
        for (int i = 0; i < 100; i++) {
            new Thread(() -> log.info("获取一个枚举式单例：threadName:{} \t {}。", Thread.currentThread().getName(), EnumSingletonObj.getInstance())).start();
        }
    }
}

enum EnumSingletonObj {
    SINGLETON_OBJ;

    public static EnumSingletonObj getInstance() {
        return SINGLETON_OBJ;
    }
}