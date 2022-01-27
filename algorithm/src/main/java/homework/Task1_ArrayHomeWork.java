package homework;

import java.util.Arrays;

public class Task1_ArrayHomeWork {
    /**
     * 第一次作业
     * 请设计一个算法，在单调数组中，将出现的重复元素一个都不要，例如
     * 示例1：
     * 原始数组为[1，1，2，2，3，4，5]，由于1，2存在重复，所以数组变成[3,4,5]
     * 示例2：
     * 原始数组为[0，2，2，2，2，2，5]，由于2存在重复，所以返回[0,5]
     * 要求：
     * 1.时间复杂度不能高于O(n)，空间复杂度为O（1）级别，只允许操作原始数组，不得创建新数组
     * 2.在下面removeAllDuplicate()中实现你的代码，其他代码不可以修改
     * 3.提交时将本文件后面添加上你的在算法课程里的编号，并将文件压缩后发给荣容.不知道编号的参考《编号列表》
     *     假如你是10号，那么提交的文件名就是：Task1_ArrayHomeWork_10.java，
     *     然后压缩为Task1_ArrayHomeWork_10.zip,将该文件发送到荣荣邮箱（后续会通知）
     *     如果不按照规定导致找不到你人，作业判0分
     * 3.最晚提交时间2022年1月3日晚上12:00，之后要算法将进行第二阶段学习，为防止一直在纠结作业影响后续学习，逾期再提交的将作废
     * 4.请编写规范清晰，算法可运行，如果因为不规范,个人原因不能执行等原因要找你，每找一次扣5分。
     * 5.造题不易，一切为了你学习，题目请不要随意外泄
     * <p>
     * 提示：双指针思想再拓展一下
     */

    /**
     * @param arr 待处理的数组
     */
    public static void removeAllDuplicate(int[] arr) {
        //todo  这里实现你的算法

    }

    public static void main(String[] args) {

        int[] arr1 = {1, 1, 2, 2, 3, 4, 5};
        removeAllDuplicate(arr1);
        // 此时arr1的前三个元素为[3 4 5]

        int[] arr2 = {0, 2, 2, 2, 2, 2, 5};
        removeAllDuplicate(arr2);
        //此时arr2的前两个元素为[0 5]

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }


}
