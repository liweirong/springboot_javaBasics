package unit1_array.part2_basic_array;

import static tools.ArrayTool.printList;

public class BasicAddByElement {
    public static void main(String[] args) {
//通过元素有序插入
        int[] arr = new int[20];
        arr[0] = 3;
        arr[1] = 4;
        arr[2] = 7;
        arr[3] = 8;
//中间位置插入
        addByElementSequence(arr, 4, 6);
        printList("通过元素顺序插入", arr, 5);
//尾部插入
        addByElementSequence(arr, 5, 9);
        printList("通过元素顺序，尾部插入", arr, 6);

//      测试元素有序并且在表头插入
        addByElementSequence(arr, 6, 0);
        printList("通过元素顺序，尾部插入", arr, 7);
    }

    /**
     * @param arr   数组
     * @param size  已经存放元素的数量
     * @param index 插入的位置
     * @param key   插入的元素
     */
    public static void addByIndex(int[] arr, int size, int index, int key) {
        //注意这里是错的，是size >= arr.length-1，或者size > arr.length都行
        if (size >= arr.length - 1) {
            throw new IllegalArgumentException("Add failed. array is full.");
        }
        //这里后面的条件应该是index >= arr.length
        if (index < 0 || index > arr.length)
            throw new IllegalArgumentException("Add failed.");
        for (int i = size - 1; i >= index; i--)
            arr[i + 1] = arr[i];
        arr[index] = key;
        size++;
    }

    /**
     * @param arr
     * @param size    数组已经存储的元素数量
     * @param element 待插入的元素
     * @return
     */
    public static int addByElementSequence(int[] arr, int size, int element) {
        if (size >= arr.length)
            throw new IllegalArgumentException("Add failed. Array is full.");
        int index = size;
        //找到新元素的插入位置
        for (int i = 0; i < size; i++) {
            if (element < arr[i]) {
                index = i;
                break;
            }
        }
        //元素后移
        for (int j = size; j > index; j--) {
            arr[j] = arr[j - 1]; //index下标开始的元素后移一个位置
        }
        arr[index] = element;//插入数据
        return index;
    }
}
