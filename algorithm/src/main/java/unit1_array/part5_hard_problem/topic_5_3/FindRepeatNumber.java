package unit1_array.part5_hard_problem.topic_5_3;

import java.util.HashSet;
import java.util.Set;

public class FindRepeatNumber {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(duplicate(array));

    }

    /**
     * 方法1：使用集合
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num))
                return num;
            set.add(num);
        }
        return -1;
    }

    /**
     * 方法二：调整索引实现
     *
     * @param numbers
     * @return
     */
    public static int duplicate(int numbers[]) {
        int length = numbers.length;
        if (numbers == null || length < 1) {
            return -1;
        }
        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) { //每个元素最多被交换两次就可以找到自己的位置，依次复杂度是O（n）
                if (numbers[numbers[i]] == numbers[i]) {
                    return numbers[i];
                } else {
                    int temp = numbers[numbers[i]]; //交换
                    numbers[numbers[i]] = numbers[i]; //将numbers[i]放到属于他的位置上
                    numbers[i] = temp;
                }
            }
        }
        return -1;
    }
}
