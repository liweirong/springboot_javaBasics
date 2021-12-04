package datatype;

import util.JedisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * iris
 */
public class HashTest {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        //灰灰的ID是18，买了商品ID为10，数量1个
        JedisUtil.getJedisUtil().hset("18", "10", "1");
        //10的商品不错，再来二个
        JedisUtil.getJedisUtil().hincrby("18", "10", 2L);
        //灰灰钱多，再买个11的商品
        JedisUtil.getJedisUtil().hset("18", "11", "1");
        //有钱，全选付款
        map = JedisUtil.getJedisUtil().hgetall("18");
        System.out.println(map.toString());
        //发现没钱买11了，把11给删掉
        JedisUtil.getJedisUtil().hdel("18", "11");
        map = JedisUtil.getJedisUtil().hgetall("18");
        System.out.println(map.toString());

    }
}
