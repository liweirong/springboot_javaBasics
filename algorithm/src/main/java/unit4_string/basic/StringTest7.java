package unit4_string.basic;

public class StringTest7 {
    public static void main(String[] args) {
        //统计字符串中的小写字母，大写字母数字空格的个数
        String str = "1123ahdiASDFGF    shaid";
        int upper = 0;
        int lower = 0;
        int num = 0;
        int space = 0;
        for (int i = 0; i < str.length(); i++) {
            //返回该索引处的char值
            char c = str.charAt(i);
            System.out.print(c+" ");
            if(c>'a'&&c<'z'){
                lower++;
            }
            if(c>'A'&&c<'Z'){
                upper++;
            }
            if(c>'0'&&c<'9'){
                num++;
            }
            if(c==' '){
                space++;
            }
        }
        System.out.println();
        System.out.println(lower);
        System.out.println(upper);
        System.out.println(num);
        System.out.println(space);
    }
}