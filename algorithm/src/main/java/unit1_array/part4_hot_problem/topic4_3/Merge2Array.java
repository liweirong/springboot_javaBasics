package unit1_array.part4_hot_problem.topic4_3;

import java.util.Arrays;

import static tools.ArrayTool.printList;

/**
 * 合并两个数组
 */
public class Merge2Array {
    public static void main(String[] args) {
        int[] arr1 = new int[6];
        arr1[0] = 1;
        arr1[1] = 2;
        arr1[2] = 3;
        int[] arr2 = new int[]{2, 5, 6};

        int testMethod = 3;
        switch (testMethod) {
            case 1://通过排序实现合并
                merge1(arr1, 3, arr2, 3);
                break;
            case 2://基本的逐个合并
                merge2(arr1, 3, arr2, 3);
                break;
            case 3://对2的优化
                merge3(arr1, 3, arr2, 3);
                break;
        }

        //打印方式1：使用toString方法，但是这样不能控制有效数据的长度
//        System.out.println(Arrays.toString(arr1));
        //打印方式2：自己设计一个打印方法
        printList("合并的结果为", arr1, 6);

    }

    /**
     * 方法1：先合并再排序实现排序
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 方法2：两个数组从后向前逐步合并
     *
     * @param nums1
     * @param nums1_len
     * @param nums2
     * @param nums2_len
     */
    public static void merge2(int[] nums1, int nums1_len, int[] nums2, int nums2_len) {
        int newLength = nums1_len + nums2_len - 1;
        int len1 = nums1_len - 1, len2 = nums2_len - 1;

        while (len1 >= 0 && len2 >= 0) {
            if (nums1[len1] <= nums2[len2]) {
                nums1[newLength] = nums2[len2];
                newLength--;
                len2--;
            } else {
                nums1[newLength] = nums1[len1];
                newLength--;
                len1--;
            }

        }
        //假如A或者B数组还有剩余
        while (len2 != -1) {
            nums1[newLength] = nums2[len2];
            newLength--;
            len2--;

        }

        while (len1 != -1) {
            nums1[newLength] = nums1[len1];
            newLength--;
            len1--;
        }
    }

    /**
     * 方法3：优化上面的方法2
     *
     * @param nums1
     * @param nums1_len
     * @param nums2
     * @param nums2_len
     */
    public static void merge3(int[] nums1, int nums1_len, int[] nums2, int nums2_len) {
        int i = nums1_len + nums2_len - 1;
        int len1 = nums1_len - 1, len2 = nums2_len - 1;
        while (len1 >= 0 && len2 >= 0) {
            if (nums1[len1] <= nums2[len2])
                nums1[i--] = nums2[len2--];
            else if (nums1[len1] > nums2[len2])
                nums1[i--] = nums1[len1--];
        }
        while (len2 != -1) nums1[i--] = nums2[len2--];
        while (len1 != -1) nums1[i--] = nums1[len1--];
    }

}
