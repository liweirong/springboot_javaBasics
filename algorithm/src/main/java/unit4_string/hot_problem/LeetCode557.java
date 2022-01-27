package unit4_string.hot_problem;

public class LeetCode557 {
    public static void main(String[] args) {
        String str="Let's take LeetCode contest";
        System.out.println(reverseWords2(str));

    }
    public static String reverseWords(String s) {
        StringBuffer ret = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                ret.append(s.charAt(start + i - 1 - p));
            }
            while (i < length && s.charAt(i) == ' ') {
                i++;
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    public static String reverseWords2(String s) {
        int length = s.length();
        char[] charArray = s.toCharArray();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && charArray[i] != ' ') {
                i++;
            }

            int left = start, right = i - 1;
            while (left < right ) {
                swap(charArray,left, right);
                left++;
                right--;
            }
            while (i < length && charArray[i] == ' ') {
                i++;
            }
        }
        return String.valueOf(charArray);
    }

    public static void swap(char []array ,int a,int  b){
        char c=array[a];
        array[a]=array[b];
        array[b]=c;
    }

}
