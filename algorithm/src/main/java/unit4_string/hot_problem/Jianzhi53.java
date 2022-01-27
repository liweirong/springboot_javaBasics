package unit4_string.hot_problem;

public class Jianzhi53 {
    public static void main(String[] args) {
        String s = "    .1  ";
        System.out.println(isNumber(s));
    }

    public static boolean isNumber(String s) {
        int n = s.length();
        int index = 0;
        boolean hasNum = false, hasE = false;
        boolean hasSign = false, hasDot = false;
        while(index < n && s.charAt(index) == ' ')
            index++;
        while(index < n){
            while(index < n && s.charAt(index) >= '0' && s.charAt(index) <= '9'){
                index++;
                hasNum = true;
            }
            if(index == n){
                break;
            }
            char c = s.charAt(index);
            if(c == 'e' || c == 'E'){
                if(hasE || !hasNum){
                    return false;
                }
                hasE = true;
                hasNum = false; hasSign = false; hasDot = false;
            }else if(c == '+' || c == '-'){
                if(hasSign || hasNum || hasDot){
                    return false;
                }
                hasSign = true;
            }else if(c == '.'){
                if(hasDot || hasE){
                    return false;
                }
                hasDot = true;
            }else if(c == ' '){
                break;
            }else{
                return false;
            }
            index++;
        }
        while(index < n && s.charAt(index) == ' ')
            index++;
        return hasNum && index == n;
    }
}
