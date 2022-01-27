package unit4_string.basic;

public class StringTest5 {
    public static void main(String[] args) {
        //int->String
        int i = 1234567;
        String s = "";
        s = i + "";//会产生两个String对象
        System.out.println(s);
        String s1 = String.valueOf(i);//使用String类的静态方法，只产生一个String对象
        System.out.println(s1);
        String s2 = Integer.toString(i);
        System.out.println(s2);
        //String->int
        String str = "12345";
        int i1 = Integer.parseInt(str);//直接使用静态方法，不会产生多余的对象
        System.out.println(i1);
        int i2 = Integer.valueOf(str).intValue();
        System.out.println(i2);
    }
}