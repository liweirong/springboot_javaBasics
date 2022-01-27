package unit4_string.basic;

public class StringTest6 {
    public static void main(String[] args) {
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println(s1.equals(s2));//判断字符串内容
        System.out.println(s1 == s2);//判断字符串引用
    }
}