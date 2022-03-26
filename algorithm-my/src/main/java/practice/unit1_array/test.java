package practice.unit1_array;

import java.util.LinkedList;
import java.util.List;

/**
 * @author iris
 * @date 2022/3/8
 */
public class test {
    public static void main(String[] args) {

//        System.out.println(isPalindrome("0P"));
        System.out.println(isPalindrome(Integer.MAX_VALUE));
    }
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        char[] chars = s.toCharArray();
        int right = 0, left = chars.length - 1;
        while (right <= left) {
            if(!Character.isLetterOrDigit(chars[right])){
                right++;
                continue;
            }
            if(!Character.isLetterOrDigit(chars[left])){
                left--;
                continue;
            }
            if (Character.toLowerCase(chars[right]) == Character.toLowerCase(chars[left])) {
                right++;
                left--;
            } else {
                return false;
            }
        }
        return true;

    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int xx = x;
        int a = 0;
        int b = 0;
        while (x!=0) {
            b = x % 10;
            //判断条件不满足的时候才进行计算
            a = a*10+b;
            //继续倒着取下一位
            x = x/10;
        }

        return a == xx;
    }
}