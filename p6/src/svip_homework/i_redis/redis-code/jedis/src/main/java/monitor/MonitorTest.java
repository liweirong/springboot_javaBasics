package svip_homework.i_redis.redis;

import com.google.common.util.concurrent.AtomicLongMap;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

import java.util.List;

/**
 * iris
 */
public class MonitorTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.44.181", 6379);
        jedis.monitor(new JedisMonitor() {
            @Override
            public void onCommand(String command) {
                System.out.println("#monitor: " + command);
                AtomicLongMap<String> ATOMIC_LONG_MAP = AtomicLongMap.create();
                // ATOMIC_LONG_MAP.incrementAndGet(command);
            }
        });

    }
}
