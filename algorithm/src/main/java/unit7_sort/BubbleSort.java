package unit7_sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {6, 3, 2, 1, 4, 5, 8, 7};
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            if (i != index) {
                swap(array, i, index);
            }
        }
    }

    public static void swap(int[] A, int i, int j) {
        int tmp=A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
