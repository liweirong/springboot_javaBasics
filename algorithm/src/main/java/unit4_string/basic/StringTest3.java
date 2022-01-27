package unit4_string.basic;

public class StringTest3 {
    public static void main(String[] args) {
        String str = "anAdEfg";
        String str1 = "axcde";
        int a=123323;
        //把字符串转换为字节数组
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]+" ");
        }
        //把字符串转换为字符数组
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]+" ");
        }
        System.out.println();
        //把字符数组转换成字符串
        String s1 = new String (chars);
        //把int类型的数据转成字符串
        String s2 = Integer.toString(a);
        //把字符串转换成小写
        String s = str.toLowerCase();
        //把字符串变成大写
        String s3 = str.toUpperCase();
        //字符串拼接
        String s4 = str.concat(str1);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
    }
}