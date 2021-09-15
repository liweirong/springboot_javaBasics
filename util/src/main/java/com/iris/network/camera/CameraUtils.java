package com.iris.network.camera;


import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * @author iris
 * @date 2021/8/17
 */
public class CameraUtils {

    public static void main(String[] args) {
String s= "{\n\"ItemNum\": 2,\n\"Condition\": [{\n\"QueryType\": 256,\n\"LogicFlag\": 0,\n\"QueryData\": \"1001\"\n},\n {\n\"QueryType\": 257,\n\"LogicFlag\": 0,\n\"QueryData\": \"1\"\n }\n],\n\"QueryCount\": 1,\n\"PageFirstRowNumber\": 0,\n\"PageRowNum\": 20\n }\n";

String condition = "{\n\" +\n" +
        "\t\"ItemNum\": 2,\n\" +\n" +
        "\t\"Condition\": [{\n\" +\n" +
        "\t\t\"QueryType\": 256,\n\" +\n" +
        "\t\t\"LogicFlag\": 0,\n\" +\n" +
        "\t\t\"QueryData\": \"\" + ResType +\"\"\n\" +\n" +
        "\t}, {\n\" +\n" +
        "\t\t\"QueryType\": 257,\n\" +\n" +
        "\t\t\"LogicFlag\": 0,\n\" +\n" +
        "\t\t\"QueryData\": \"+ IsQuerySub + \"\"\n\" +\n" +
        "\t}],\n\" +\n" +
        "\t\"QueryCount\":\"+ IsQueryTotal +\",\n\" +\n" +
        "\t\"PageFirstRowNumber\":\"+LowStart + \",\n\" +\n" +
        "\t\"PageRowNum\": \" + PageSize + \"\n\" +\n" +
        "\"}";



String domain = " \"http://95.1.0.4:8088/VIID/query?condition=";
String d= " -H \"Authorization:9TS1OLIbK74xkHZpJEcJ\" ";
        System.out.println("curl "+d+ domain+condition);
        // 95.1.0.4  loadmin/Tx123456#
        System.out.println( getLoginSignature("loadmin","EXOOo8w9LSADRZnByew9","Tx123456#"));
    }

    /**
     * LoginSignature = MD5(BASE64(UserName)+ AccessCode + MD5(用户密码) )
     *
     * @return
     */
    public static String getLoginSignature(String userName, String accessCode, String password) {
        return DigestUtils.md5Hex(getBase64(userName)+ accessCode + DigestUtils.md5Hex(password));
    }

    /// <summary>
    /// MD5加密(32位小写)
    /// </summary>
    /// <param name="str">加密字符</param>
    /// <returns></returns>
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }
}
