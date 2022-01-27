package unit1_array.part5_hard_problem.topic_5_4;

import java.util.HashMap;
import java.util.Map;

public class MoreThanHalfNum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThanHalfNum(arr));
    }


    public static int moreThanHalfNum(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        int len = array.length;
        int result = array[0];
        int times = 1;
        for (int i = 1; i < len; i++) {
            if (times == 0) {
                result = array[i];
                times = 1;
                continue;
            }

            if (array[i] == result)
                times++;
            else
                times--;
        }
        //检查是否符合
        times = 0;
        for (int i = 0; i < len; i++) {
            if (array[i] == result)
                times++;
            if (times > len / 2)
                return result;
        }
        return 0;
    }
}
