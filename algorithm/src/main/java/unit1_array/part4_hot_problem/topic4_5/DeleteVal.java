package unit1_array.part4_hot_problem.topic4_5;

public class DeleteVal {
    public static void main(String[] args) {
        int []arr={3,2,2,3,3};
        int test=0;
        if(test==0){
            System.out.println(removeElement(arr,3));
        }else{
            System.out.println(removeElement(arr,2));
        }

    }

    public static int removeElement(int[] nums, int val) {
        int ans = 0;
        for(int num: nums) {
            if(num != val) {
                nums[ans] = num;
                ans++;
            }
        }
        //最后剩余元素的数量
        return ans;
    }

    public static int removeElement2(int[] nums, int val) {
        int ans = nums.length;
        for (int i = 0; i < ans;) {
            if (nums[i] == val) {
                nums[i] = nums[ans - 1];
                ans--;
            } else {
                i++;
            }
        }
        return ans;
    }
}
