package leetcode.A_array;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author iris
 * @date 2021/9/15
 */
public class 数组中使奇数都在偶数前面的三道题 {

    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 3, 4, 5, 6, 11, 13};
        System.out.println(JSONObject.toJSONString(reOrderArray(array)));
    }

    /**
     * 输入一个整数数组，通过一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分。
     * 这里可以有两个题目，一个是不要求调整后顺序的，另一个就是这里要求的相对位置不变。
     * <p>
     * 1 不考虑调整后元素的顺序
     * 2 要求调整后的顺序仍与原始数组的顺序一致
     *
     * @return
     */
    private static int[] reOrderArray(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            // 如果这里没有顺序的要求可以用双指针的方式，从前后两端分别遍历，然后如果遇到需要的再等另外一次也找到
            while (array[left] % 2 != 0 && left < right) {
                left++;
            }
            while (array[right] % 2 == 0 && left < right) {
                right--;
            }
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
        return array;
    }

    /**
     *给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     *
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * @param array
     * @return
     */
    private static int[] reOrderArray2(int[] array) {
        int[] res = new int[array.length];
        int o = 0;
        int j = 1;
        for(int i = 0;i < array.length;++i){
            if(array[i] % 2 == 0){
                res[o] = array[i];
                //到下一个偶数位置等着
                o+=2;
            }else{
                res[j] = array[i];
                //到下一个奇数位置等着
                j+=2;
            }
        }
        return res;
    }
}
