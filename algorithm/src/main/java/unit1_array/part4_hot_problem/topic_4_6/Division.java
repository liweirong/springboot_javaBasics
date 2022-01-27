package unit1_array.part4_hot_problem.topic_4_6;

import java.util.Arrays;

public class Division {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        int test=1;
        if(test==2){
            //双指针
            System.out.println(Arrays.toString(sortArrayByParity(arr)));
        }else{
            //模仿冒泡排序
            System.out.println(Arrays.toString(reOrderArray(arr)));
        }
    }

    /**
     * 双指针，不稳定 的移动
     * @param A
     * @return
     */
    public static  int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            if (A[i] % 2 > A[j] % 2) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

            if (A[i] % 2 == 0) i++;
            if (A[j] % 2 == 1) j--;
        }

        return A;
    }

    /**
     * 模仿冒泡的稳定移动方法
     * @param array
     * @return
     */
    public static int[] reOrderArray (int[] array) {
        if (array == null || array.length == 0)
            return new int[0];
        int n = array.length;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n-1-i; j++) {
                // 左边是偶数, 右边是奇数的情况
                if ((array[j] & 1) == 0 && (array[j+1] & 1) == 1) {
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
        return array;
    }
}
