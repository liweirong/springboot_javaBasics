package svip_homework.i_redis.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import util.ResourceUtil;
import java.util.Arrays;

/**
 * iris
 */
public class LuaTest {
    public static void main(String[] args) {
        Jedis jedis = getJedisUtil();
        jedis.eval("return redis.call('set',KEYS[1],ARGV[1])", 1,"test:lua:key","iris2673lua");
        System.out.println(jedis.get("test:lua:key"));
        for(int i=0; i<10; i++){
            limit();
        }
    }

    /**
     * 10秒内限制访问5次
     */
    public static void limit(){
        Jedis jedis = getJedisUtil();
        // 只在第一次对key设置过期时间
        String lua = "local num = redis.call('incr', KEYS[1])\n" +
                "if tonumber(num) == 1 then\n" +
                "\tredis.call('expire', KEYS[1], ARGV[1])\n" +
                "\treturn 1\n" +
                "elseif tonumber(num) > tonumber(ARGV[2]) then\n" +
                "\treturn 0\n" +
                "else \n" +
                "\treturn 1\n" +
                "end\n";
        Object result = jedis.evalsha(jedis.scriptLoad(lua), Arrays.asList("localhost"), Arrays.asList("10", "5"));
        System.out.println(result);
    }

    private static Jedis getJedisUtil() {
        String ip = ResourceUtil.getKey("redis.host");
        int port = Integer.valueOf(ResourceUtil.getKey("redis.port"));
        String password = ResourceUtil.getKey("redis.password");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool pool =null;
        if(null == password || "".equals(password)){
            pool = new JedisPool(jedisPoolConfig, ip, port, 10000);
        }else{
            pool = new JedisPool(jedisPoolConfig, ip, port, 10000, password);
        }
        return pool.getResource();
    }

}
