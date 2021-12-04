import redis.clients.jedis.Jedis;

/**
 * iris
 */
public class BasicTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.44.181", 6379);
        jedis.set("iris", "2673jedis");
        System.out.println(jedis.get("iris"));
        jedis.close();
    }
}
