package unit4_string.hot_problem;

public class LeetCode709 {

    public static void main(String[] args) {
        String s = "Hello";
        System.out.println(toLowerCase(s));
    }

    public static String toLowerCase(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            if (chars[i] >= 65 && chars[i] <= 90) {
                chars[i] += 32;
            }
        }
        String str = new String(chars);
        return str;
    }
}
