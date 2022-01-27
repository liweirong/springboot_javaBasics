package unit4_string.basic;

public class StringTest2 {
    public static void main(String[] args) {
        String str1 = "axcde";
        String str2 = "Axcde";
        System.out.println("String类的判断功能");
        //比较字符串的内容是否相同，区分大小写
        boolean b = str1.equals(str2);
        //比较字符串的内容是否相同,忽略大小写
        boolean b1 = str1.equalsIgnoreCase(str2);
        //判断字符串中是否包含传递进来的字符串
        boolean b2 = str1.contains("cde");
        //判断字符串是否以传递进来的字符串开头
        boolean b3 = str1.startsWith("ax");
        //判断字符串是否以传递进来的字符出结尾
        boolean b4 = str2.endsWith("de");
        //判断字符串的内容是否为空
        boolean b5 = str1.isEmpty();
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);
        System.out.println(b5);
    }
}