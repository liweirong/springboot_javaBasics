package unit1_array.part2_basic_array;

import java.util.Arrays;

import static tools.ArrayTool.printList;

public class BasicDelete {
    public static void main(String[] args) {


        int[] arr = new int[]{2, 3, 4, 9, 10, 11, 12};
        System.out.println(Arrays.toString(arr));
        //根据索引位置删除
        removeByIndex(arr, 7, 2);
        printList("根据索引删除", arr, 6);
        removeByElement(arr, 6, 2);
        printList("根据索引删除", arr, 5);
    }

    /**
     * @param arr
     * @param size  数组元素数量
     * @param index 删除位置
     * @return
     */
    public static int removeByIndex(int[] arr, int size, int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        int ret = arr[index];
        for (int i = index + 1; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        size--;
        return ret;
    }

    /**
     * 从数组中删除元素e
     *
     * @param arr
     * @param size 数组
     * @param key
     */
    public static void removeByElement(int[] arr, int size, int key) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arr[i] == key) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index + 1; i < size; i++)
                arr[i - 1] = arr[i];
            size--;
        }
    }
}
