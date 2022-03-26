package practice.unit1_array;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;

/**
 * @author iris
 * @date 2022/1/27
 */
public class AddByIndex {

    public static void main(String[] args) {
        //通过元素有序插入
        int[] arr = new int[20];
        arr[0] = 3;
        arr[1] = 4;
        arr[2] = 7;
        arr[3] = 8;

        addByIndex(arr, 4, 2, 6);
        addByIndex(arr, 4, 2, 5);

        System.out.println(JSONUtil.toJsonStr(arr));

    }

    /**
     * @param arr
     * @param size
     * @param index 指定位置
     * @param value
     */
    private static void addByIndex(int[] arr, int size, int index, int value) {
        if (size > arr.length) {
            throw new IllegalArgumentException("array full");
        }
        if (index < 0 || index > arr.length - 1){
            throw new IllegalArgumentException("out of index");
        }
        for (int i = size - 1; i >= index ; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = value;
    }
}
