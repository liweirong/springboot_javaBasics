package datatype;

import util.JedisUtil;

/**
 * iris
 */
public class SimpleTest {
    public static void main(String[] args) {

        JedisUtil.getJedisUtil().set("iris","2673");
        //JedisUtil.getJedisUtil().get("iris");

    }
}
