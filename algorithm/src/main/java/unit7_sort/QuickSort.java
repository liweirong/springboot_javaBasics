package unit7_sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {6, 3, 2, 1, 4, 5, 8, 7};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }


    private static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start, right = end;
        int pivot = array[(start + end) / 2];
        while (left <= right) {
            while (left <= right && array[left] < pivot) {
                left++;
            }
            while (left <= right && array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;

            }
        }
        quickSort(array, start, right);
        quickSort(array, left, end);
    }
}
