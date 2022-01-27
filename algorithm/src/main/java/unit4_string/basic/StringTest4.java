package unit4_string.basic;

public class StringTest4 {
    public static void main(String[] args) {
        String str = "  anAdEfg  ";
        String str1 = "axcde";

        //将指定字符进行互换
        String s = str.replace('a', 'b');
        System.out.println(s);
        //将指定字符串进行互换
        String s1 = str.replace("ab", "qq");
        System.out.println(s1);
        //去除两端空格
        String s2 = str.trim();
        System.out.println(s2);
        //通过字典顺序去比较 返回的值是 ASCII 码的差值  调用者减去传入者
        int i = "abc".compareTo("ABc");
        System.out.println(i);
        //如果前面几个字母一样会根据两个字符串长度进行减法运算返回该减法的结果 （通过长度去比）
        int i1 = "abc".compareTo("a");
        System.out.println(i1);
        //忽略大小写的比较
        int i2 = "abc".compareToIgnoreCase("ABC");
        System.out.println(i2);
    }
}