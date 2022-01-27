package unit1_array.part2_basic_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicCreateArray {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++)
            arr[i] = i;
        System.out.println("arr1:" + Arrays.toString(arr));
        int[] arr2 = new int[]{0, 1, 2, 3, 5, 6, 8};
        System.out.println("arr2:" + Arrays.toString(arr2));
        //这么写也行:
        int[] arr3 = {2, 5, 0, 4, 6, -10};
        System.out.println("arr3" + Arrays.toString(arr3));
        int[] nums;
        List<Integer> res=new ArrayList<>();

    }
}
