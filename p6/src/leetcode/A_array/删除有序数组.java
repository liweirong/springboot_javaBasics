package leetcode.A_array;

import com.alibaba.fastjson.JSONObject;

/**
 * @author iris
 * @date 2021/9/16
 */
public class 删除有序数组 {


    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2};
        int last = removeDuplicates1(arr);
        System.out.println(last + JSONObject.toJSONString(arr));

        int[] arr2 = new int[]{0, 1, 1, 2, 3, 2, 3, 2, 3, 4};
        int last2 = remove(arr2, 2);
        System.out.println(last2 + "--" + JSONObject.toJSONString(arr2));

        int[] arr3 = new int[]{0, 1, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4};
        int last3 = remove2(arr3, 2);
        System.out.println(last3 + "--" + JSONObject.toJSONString(arr3));

    }

    /**
     * 请你原地删除重复出现的元素，使每个元素只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums 有序数组 nums
     * @return
     */

    private static int removeDuplicates1(int[] nums) {
        int n = nums.length;
        System.out.println(JSONObject.toJSONString(nums));
        //j用来标记有效位
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != nums[j]) {
                nums[j++] = nums[i];
                // System.out.println(i + "->" + j + "==" + JSONObject.toJSONString(nums));
            }
        }
        return j + 1;
    }

    /**
     * leetcode27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 要求：不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例1：
     * <p>
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 示例2:
     * <p>
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3]
     */
    private static int remove(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    /**
     * .leetcode 80. 删除有序数组中的重复项 II
     * 给你一个   有序数组 nums   ，请你原地删除重复出现的元素，使每个元素最多出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     */

    private static int remove2(int[] nums, int limit) {
        System.out.println("删除前==" + JSONObject.toJSONString(nums));
        int j = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[j] == nums[i]) {
                count++;
            } else if (nums[i] != nums[j]) {
                count = 1;
            }
            if (count <= limit) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }
}