package unit7_sort;

public class Kth {
    public static void main(String[] args) {
        int[] array = {6, 3, 2, 4, 5, 8, 7};
        int k = 2;//找第二大元素
        int n = array.length - k + 1;
        System.out.println(quickSort(array, 0, array.length - 1, n));
    }

    public static int quickSort(int[] array, int start, int end, int k) {
        if (start == end) {
            return array[start];
        }

        int left = start, right = end, pivot = array[(start + end) >> 1];
        while (left < right) {
            while (left <= right && array[left] < pivot) {
                left++;
            }
            while (left <= right && array[right] > pivot) {
                right--;
            }
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }

        int cnt = right - start + 1;
        if (k <= cnt) return quickSort(array, start, right, k);
        else return quickSort(array, right + 1, end, k - cnt);
    }
}
