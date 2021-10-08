package leetcode.B_array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iris
 * @date 2021/9/26
 */
public class 打印二维数组 {

    public static void main(String[] args) {

        int[][] arr = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        for (int[] ints : arr) {
            for (int s : ints) {
                System.out.print(s + "\t");
            }
            System.out.println();
        }
        System.out.println(spiralOrder(arr));
        System.out.println(spiralOrder2(arr));
    }

    public static ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        //定义四个坐标，表示每次循环的四个位置
        int one = 0, two = matrix[0].length - 1;
        int four = 0, three = matrix.length - 1;
        while (four < (matrix.length + 1) / 2 && one < (matrix[0].length + 1) / 2) {
            // one-------> two
            //              |
            //   |          |
            //  four<-----three
            //
            for (int i = one; i <= two; i++) {
                res.add(matrix[four][i]);
            }

            for (int i = four + 1; i <= three; i++) {
                res.add(matrix[i][two]);
            }

            for (int i = two - 1; i >= one && four != three; i--) {
                res.add(matrix[three][i]);
            }

            for (int i = three - 1; i >= (four + 1) && one != two; i--) {
                res.add(matrix[i][one]);
            }
            one++;
            two--;
            three--;
            four++;


        }

        return res;


    }

    public static List<Integer> spiralOrder2(int[][] matrix) {
        //方向优先级右下左上
        //遍历过的都令为233
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = 0;
        // int[] res = new int[m*n];
        List<Integer> res = new ArrayList<>(m * n);
        int direction = 0;//0代表右，1代表下，2代表左，3代表上
        for (int cur = 0; cur < m * n; cur++) {
            res.add(matrix[i][j]);
            matrix[i][j] = 233;
            //确定方向
            if (direction == 0 && (j == n - 1 || matrix[i][j + 1] == 233)) {//方向向右，遇到右边界
                direction = 1;
            }
            if (direction == 1 && (i == m - 1 || matrix[i + 1][j] == 233)) {//方向向下，遇到下边界
                direction = 2;
            }
            if (direction == 2 && (j == 0 || matrix[i][j - 1] == 233)) {//方向向左，遇到左边界
                direction = 3;
            }
            if (direction == 3 && (i == 0 || matrix[i - 1][j] == 233)) {//方向向上，遇到上边界
                direction = 0;
            }

            //根据方向来走
            if (direction == 0) {
                j++;
            }
            if (direction == 1) {
                i++;
            }
            if (direction == 2) {
                j--;
            }
            if (direction == 3) {
                i--;
            }
        }
        return res;
    }
}
