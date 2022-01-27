package unit7_sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array = {6, 3, 2, 1, 4, 5, 8, 7};
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
        System.out.println(Arrays.toString(array));

    }


    private static void mergeSort(int[] array, int start, int end, int temp[]) {
        if (start >= end) {
            return;
        }
        mergeSort(array, start, (start + end) / 2, temp);
        mergeSort(array, (start + end) / 2 + 1, end, temp);
        merge(array, start, end, temp);
    }

    private static void merge(int[] array, int start, int end, int[] temp) {
        int middle = (start + end) / 2;
        int left = start;
        int right = middle + 1;
        int index = left;
        while (left <= middle && right <= end) {
            if (array[left] < array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }
        while (left <= middle) {
            temp[index++] = array[left++];
        }
        while (right <= end) {
            temp[index++] = array[right++];
        }
        for (int i = start; i <= end; i++) {
            array[i] = temp[i];
        }

    }
}
