package svip_homework.a_design.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author iris
 * @date 2021/10/11
 * 注册式单例 - 1 枚举 2 *容器式*
 * 优点：容器时单例方式可以使我们很方便的管理很多单例对象，也对用户隐藏了具体实现类,降低了耦合度；但是为了避免造成内存泄漏，一般在生命周期销毁的时候也要去销毁它。
 */
@Slf4j
public class ContainerSingleton {
    public static void main(String[] args) {
        // 测试创建100线程池对象
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    log.info("获取一个容器式单例：threadName:{} \t {}。", Thread.currentThread().getName(), ContainerSingletonObj.getBean(ContainerSingletonObj.class.getName()));
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

class ContainerSingletonObj {

    private ContainerSingletonObj() {
    }

    private static final Map<String, Object> map = new ConcurrentHashMap<>();

    static {
        ContainerSingletonObj singleton = new ContainerSingletonObj();
        map.put(singleton.getClass().getName(), singleton);
    }

    public static Object getBean(String clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        synchronized (map) {
            if (map.containsKey(clazz)) {
                return map.get(clazz);
            } else {
                Object obj = Class.forName(clazz).newInstance();
                map.put(clazz, obj);
                return obj;
            }
        }
    }
}
