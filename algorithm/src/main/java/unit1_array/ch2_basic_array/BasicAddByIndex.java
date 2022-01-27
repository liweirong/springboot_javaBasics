package unit1_array.ch2_basic_array;

import static tools.ArrayTool.printList;

public class BasicAddByIndex {
    public static void main(String[] args) {
//通过元素有序插入
        int[] arr = new int[5];
        arr[0] = 3;
        arr[1] = 4;
        arr[2] = 7;
        arr[3] = 8;

        addByIndex(arr, 4, 2, 6);
        printList("通过元素顺序插入", arr, 5);

    }
    /**
     * @param arr   数组
     * @param size  已经存放元素的数量
     * @param index 索引值，从0开始
     * @param key   插入的元素
     */
    public static void addByIndex(int[] arr, int size, int index, int key) {
        if (size > arr.length) {
            throw new IllegalArgumentException(" array is full.");
        }
        if (index < 0 || index > arr.length - 1)
            throw new IllegalArgumentException("Add failed.");
        for (int i = size - 1; i >= index; i--)
            arr[i + 1] = arr[i];
        arr[index] = key;
        size++;
    }
}
