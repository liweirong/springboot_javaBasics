package unit1_array.part2_basic_array;

public class BasicSearch {
    public static void main(String[] args) {

        int[] arr = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10};
        //通过索引查找
        int index = 2;
        System.out.println("索引为 " + index + "位置的元素为:" + findByIndex(arr, 9, index));
        //通过元素查找
        int key = 10;
        System.out.println("元素" + key + "的索引位置为：" + findByElement(arr, 9, key));
    }

    /**
     * @param arr
     * @param index 要查找的位置
     * @return
     */
    public static int findByIndex(int[] arr, int size, int index) {
        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index < size.");
        return arr[index];
    }

    /**
     * @param arr
     * @param size 已经存放的元素容量
     * @param key  待查找的元素
     * @return
     */
    public static int findByElement(int[] arr, int size, int key) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

}
