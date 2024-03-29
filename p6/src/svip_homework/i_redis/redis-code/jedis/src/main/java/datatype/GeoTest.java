package datatype;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.Map;

/**
 * iris
 */
public class GeoTest {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.44.181", 6379);
        Map<String, GeoCoordinate> geoMap = new HashMap<>();
        GeoCoordinate coordinate = new GeoCoordinate(112.881953,28.238426);
        geoMap.put("gupao",coordinate);
        jedis.geoadd("positions", geoMap);
        System.out.println(jedis.geopos("positions","gupao"));
        jedis.close();
    }
}
