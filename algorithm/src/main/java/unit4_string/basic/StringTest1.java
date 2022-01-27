package unit4_string.basic;

public class StringTest1 {
    public static void main(String[] args) {
        String str = "anAdEfg";

        System.out.println("String类的获取功能");
        //获取字符串的长度
        int length = str.length();
        //获取指定索引位置的字符
        char c1 = str.charAt(0);
        //返回指定字符在此字符串中第一次出现处的索引
        int c2 = str.indexOf('n');
        //返回指定字符串在此字符串中第一次出现的索引
        int c3 = str.indexOf("fg");
        //返回指定字符在此字符串中从指定位置后第一次出现处的索引。
        int c4= str.indexOf('f', 2);
        //返回指定字符串在此字符串中从指定位置后第一次出现处的索引。
        int c5 = str.indexOf("fg", 2);
        //从指定位置开始截取字符串，默认到末尾
        String c6 = str.substring(2);
        //从指定位置开始到指定位置结束截取字符串
        String c7 = str.substring(2, 4);
        System.out.println(length);
        System.out.println("c1:"+c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
    }
}