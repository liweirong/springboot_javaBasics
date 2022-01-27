package unit1_array.part5_hard_problem.topic_5_5;

import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {
    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDuplicates(arr));
    }

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int index = (nums[i] - 1) % n;
            nums[index] += n;
            if (nums[index] > 2 * n) res.add(index + 1);
        }
        return res;
    }
}
