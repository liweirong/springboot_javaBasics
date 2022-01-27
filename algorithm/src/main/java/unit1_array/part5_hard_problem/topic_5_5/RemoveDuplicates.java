package unit1_array.part5_hard_problem.topic_5_5;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3};
        System.out.println(removeDuplicates(arr));
    }

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        //前两个元素不必管
        //slow=2表示索引为2的位置可以被替换
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
