package unit1_array.part5_hard_problem.topic_5_4;

import java.util.HashSet;
import java.util.Set;

public class FindOneNum {
    public static void main(String[] args) {
        int[] arr = {2, 2, 1};
        System.out.println(findOneNum(arr));
        System.out.println(findOneNum_2(arr));
    }

    /**
     * 方法1，使用集合
     *
     * @param arr
     * @return
     */
    public static Integer findOneNum(int[] arr) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i : arr) {
            if (!set.add(i))//添加不成功返回false，前加上！运算符变为true
                set.remove(i);//移除集合中与这个要添加的数重复的元素
        }
        //注意边界条件的处理
        if (set.size() == 0)
            return null;
        //如果Set集合长度为0，返回null表示没找到
        return set.toArray(new Integer[set.size()])[0];
    }

    /**
     * 方法2 使用异或运算
     *
     * @param arr
     * @return
     */
    public static int findOneNum_2(int[] arr) {
        int flag = 0;
        for (int i : arr) {
            flag ^= i;
        }
        return flag;
    }
}
