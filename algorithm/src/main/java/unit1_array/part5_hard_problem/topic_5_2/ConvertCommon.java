package unit1_array.part5_hard_problem.topic_5_2;

public class ConvertCommon {
    /**
     * 进制转换，将整数转换成任一其他不大于16的进制
     *
     * @param M int整型 给定整数
     * @param N int整型 转换到的进制
     * @return string字符串
     */
    // 因为要考虑到 余数 > 9 的情况，2<=N<=16.
    public static final String[] F = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public static String solve(int M, int N) {
        Boolean flag = false;
        if (M < 0) {
            flag = true;
            M *= -1;
        }
        StringBuffer sb = new StringBuffer();
        int temp;
        while (M != 0) {
            temp = M % N;
//精华之一：通过数组F[]解决了大量繁琐的不同进制之间映射的问题
            sb.append(F[temp]);
            M = M / N;
        }
//精华之二：使用StringBuffer的reverse()方法，让原本麻烦的转置瞬间美好
        sb.reverse();
        //精华之三：最后处理正负的问题，不要从一开始就揉在一起。
        return (flag ? "-" : "") + sb.toString();

    }

    public static void main(String[] args) {
//        转换成16进制
        System.out.println(solve(3, 16));
        System.out.println(solve(10, 16));
        System.out.println(solve(11, 16));
        System.out.println(solve(17, 16));
//转换成15进制
        System.out.println(solve(3, 15));
        System.out.println(solve(10, 15));
        System.out.println(solve(11, 15));
        System.out.println(solve(17, 15));

    }
}
