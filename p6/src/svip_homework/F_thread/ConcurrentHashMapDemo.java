package svip_homework.F_thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;


/**
 * @author iris
 * @date 2021/11/12
 */
public class ConcurrentHashMapDemo {

    private static final ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        concurrentHashMap.computeIfAbsent("iris0", k -> 0);
        System.out.println("iris0 computeIfAbsent:" + concurrentHashMap.get("iris0"));
        concurrentHashMap.put("iris0", 1);
        // key存在 不修改；key不存在 调用后面的mappingFunction计算，把计算值返回做value
        concurrentHashMap.computeIfAbsent("iris0", k -> 0);
        System.out.println("iris0 computeIfAbsent:" + concurrentHashMap.get("iris0"));
        // key存在 则修改；key不存在 返回null
        concurrentHashMap.computeIfPresent("iris1", (k, v) -> v + 1);
        System.out.println("iris1 computeIfPresent:" + concurrentHashMap.get("iris1"));
        // 合体版
        concurrentHashMap.compute("iris", (k, v) -> {
            if (v == null) {
                return 1;
            }
            return v + 1;
        });


        concurrentHashMap.put("iris2", 1);
        concurrentHashMap.get("iris2");
        concurrentHashMap.remove("idis");
        System.out.println("concurrentHashMap:" + concurrentHashMap);


        Stream.of("1", "2", "3", "4", "5", "5", "5").forEach(v -> {
            // 每个value + 5 再把相同的key合并
            concurrentHashMap.merge(v, 5, Integer::sum);
        });
        System.out.println("merge后：" + concurrentHashMap);
    }
}
