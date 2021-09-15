package leetcode.A_array;

import java.math.BigDecimal;

/**
 * @author iris
 * @date 2021/9/15
 * 给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数。M是32位整数，2<=N<=16.
 */
public class 进制转换 {

    public static void main(String[] args) {
        System.out.println(solve("-25611111112561111111256111111121", 16));
    }


    private static String[] F = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /**
     * 给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数。M是32位整数，2<=N<=16.
     *
     * @param m M是32位整数
     * @param n 2<=N<=16.
     * @return
     */
    private static String solve(String m, int n) {
        boolean flag = true;
        StringBuilder sb = new StringBuilder();
        BigDecimal bigDecimal = new BigDecimal(m);
        if (bigDecimal.compareTo(new BigDecimal(0)) < 0) {
            bigDecimal = bigDecimal.multiply(new BigDecimal(-1));
            flag = false;
        }

        while (bigDecimal.compareTo(new BigDecimal(n)) >= 0) {
            BigDecimal[] bigDecimals = bigDecimal.divideAndRemainder(new BigDecimal(n));
            sb.append(F[bigDecimals[1].intValue()]);
            bigDecimal = bigDecimals[0];
        }

        if (bigDecimal.compareTo(new BigDecimal(n)) < 0) {
            sb.append(F[bigDecimal.intValue()]);
        }
        if (flag) {
            return sb.reverse().toString();
        }
        return "-" + sb.reverse().toString();
    }
}