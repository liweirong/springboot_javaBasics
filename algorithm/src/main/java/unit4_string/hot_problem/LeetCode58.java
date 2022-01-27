package unit4_string.hot_problem;

public class LeetCode58 {
    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        System.out.println(lengthOfLastWord(s));
    }

    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int lastIndex =index;
        while (index >= 0 && s.charAt(index) != ' ') {
            index--;
        }
        return lastIndex - index ;
    }
}
