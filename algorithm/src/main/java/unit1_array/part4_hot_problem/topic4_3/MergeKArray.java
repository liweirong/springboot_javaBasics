package unit1_array.part4_hot_problem.topic4_3;

import java.util.Arrays;

/**
 * 合并K个数组
 */
public class MergeKArray {
    public static void main(String[] args) {

        int[] arr1 = {1, 3, 5, 6};
        int[] arr2 = {1, 2, 5, 7};
        int[] arr3 = {3, 6, 9, 11};
        int[] arr4 = {5, 7, 13, 17};

        //先合并到一起再排序
        int[][] twoArray2 = new int[4][4];
        twoArray2[0] = Arrays.copyOf(arr1, 4);
        twoArray2[1] = Arrays.copyOf(arr2, 4);
        twoArray2[2] = Arrays.copyOf(arr3, 4);
        twoArray2[3] = Arrays.copyOf(arr4, 4);
        int[] a = MergeArrays(twoArray2);
        System.out.println(Arrays.toString(a));
    }

    public static int[] MergeArrays(int[][] array) {
        int N = array.length, L;
        if (N == 0) {
            return new int[0];
        }

        //数组内容校验
        L = array[0].length;
        for (int i = 1; i < N; i++)
            if (L != array[i].length)
                return new int[0];
        //开辟空间
        int[] result = new int[N * L];
        //将各个数组依次合并到一起
        for (int i = 0; i < N; i++)
            for (int j = 0; j < L; j++)
                result[i * L + j] = array[i][j];
        //排序一下完事
        Arrays.sort(result);
        return result;

    }


}
