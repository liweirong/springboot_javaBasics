package leetcode.B_array;

import java.util.Arrays;

/**
 * @author iris
 * @date 2021/10/8
 */
public class 螺旋矩阵 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix(3)));
    }
//leetcode 59 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 20
//
// Related Topics 数组 矩阵 模拟

    private static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int num = 1, count = n * n;
        int right = 0, left = n - 1, top = 0, down = n - 1;
        while (num <= count) {
            for (int i = right; i <= left; i++) {
                result[top][i] = num++;
            }
            top++;
            for (int i = top; i <= down; i++) {
                result[i][left] = num++;
            }
            left--;

            for (int i = left; i >= right; i--) {
                result[down][i] = num++;
            }
            down--;

            for (int i = down; i >= top; i--) {
                result[i][right] = num++;
            }
            right++;
        }
        return result;
    }
}
