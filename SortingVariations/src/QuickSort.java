/**
 * This is a class for quick sort 4 variations
 */
public class QuickSort {

    /**
     * This is a statistics class for recording the comparisons and exchanges
     */
    public static class SortStats {
        public int comparisons = 0;
        public int exchanges = 0;

        void reset() {
            comparisons = 0;
            exchanges = 0;
        }

        @Override
        public String toString() {
            return "Comparisons: " + comparisons + ", Exchanges: " + exchanges;
        }
    }

    /**
     * This is a class for partition in the quick sort
     * @param arr input array
     * @param left left pointer
     * @param right right pointer
     * @param pivot pivot value
     * @param stats statistics for comparisons and exchanges
     * @return pivot index
     */
    private static int partition(int[] arr, int left, int right, int pivot, SortStats stats) {
        while (left <= right) {
            // Move left index until a value >= pivot is found
            while (left <= right && compare(arr[left], pivot, stats) < 0) {
                left++;
            }
            // Move right index until a value <= pivot is found
            while (left <= right && compare(arr[right], pivot, stats) > 0) {
                right--;
            }
            // swap the values, and move the left and right pointers
            if (left <= right) {
                swap(arr, left, right, stats);
                left++;
                right--;
            }
        }
        // return the pivot index
        return left;
    }

    /**
     * This is a class to compare 2 integers
     * @param a integer a
     * @param b integer b
     * @param stats statistics for comparisons and exchanges
     * @return -1 if a < b, 1 if a > b, 0 if a = b
     */
    private static int compare(int a, int b, SortStats stats) {
        stats.comparisons++;
        return Integer.compare(a, b);
    }

    /**
     * This is class to swap a and b
     * @param arr input array
     * @param a integer a
     * @param b integer b
     * @param stats statistics for comparisons and exchanges
     */
    private static void swap(int[]arr, int a, int b, SortStats stats) {
        stats.exchanges++;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * Quick sort variant A: Select the first item of the partition as the pivot. Treat partitions of size one and two as stopping cases.
     * @param arr input array
     * @param left left pointer
     * @param right right pointer
     * @param stats statistics for comparisons and exchanges
     */
    public static void quickSortA(int[] arr, int left, int right, SortStats stats) {
        // Base case: size one and two as stopping cases.
        if (right - left + 1 <= 2) {
            // Handle partition of size 2 by direct comparison. Need to add this, or it is always incorrect.
            if (right > left && compare(arr[left], arr[right], stats) > 0) {
                swap(arr, left, right, stats);
            }
            return;
        }
        int pivot = arr[left];
        int pivotIndex = partition(arr, left, right, pivot, stats);
        quickSortA(arr, left, pivotIndex-1, stats);
        quickSortA(arr, pivotIndex, right, stats);
    }

    /**
     * Quick sort variant B: Same pivot selection. For a partition of size 100 or less, use an insertion sort to finish.
     * @param arr input array
     * @param left left pointer
     * @param right right pointer
     * @param stats statistics for comparisons and exchanges
     */
    public static void quickSortB(int[] arr, int left, int right, SortStats stats) {
        if (right - left + 1 <= 100) {
            insertionSort(arr, left, right, stats);
            return;
        }
        int pivot = arr[left];
        int pivotIndex = partition(arr, left, right, pivot, stats);
        quickSortB(arr, left, pivotIndex-1, stats);
        quickSortB(arr, pivotIndex, right, stats);
    }

    /**
     * Quick sort variant C: Same pivot selection. For a partition of size 50 or less, use an insertion sort to finish.
     * @param arr input array
     * @param left left pointer
     * @param right right pointer
     * @param stats statistics for comparisons and exchanges
     */
    public static void quickSortC(int[] arr, int left, int right, SortStats stats) {
        if (right - left + 1 <= 50) {
            insertionSort(arr, left, right, stats);
            return;
        }
        int pivot = arr[left];
        int pivotIndex = partition(arr, left, right, pivot, stats);
        quickSortC(arr, left, pivotIndex-1, stats);
        quickSortC(arr, pivotIndex, right, stats);
    }


    /**
     * Quick sort variant D: Select the median-of-three as the pivot. Treat partitions of size one and two as stopping cases.
     * @param arr input array
     * @param left left pointer
     * @param right right pointer
     * @param stats statistics for comparisons and exchanges
     */
    public static void quickSortD(int[] arr, int left, int right, SortStats stats) {
        if (right - left + 1 <= 2) {
            // Handle partition of size 2 by direct comparison.
            if (right > left && compare(arr[left], arr[right], stats) > 0) {
                swap(arr, left, right, stats);
            }
            return;
        }
        int pivot = medianOfThree(arr, left, right, stats);
        int pivotIndex = partition(arr, left, right, pivot, stats);
        quickSortD(arr, left, pivotIndex-1, stats);
        quickSortD(arr, pivotIndex, right, stats);
    }

    // New general version with customizable cutoff
    /**
     * Quick sort variant with cutoff: Same pivot selection. For a partition of size {cutoff} or less, use an insertion sort to finish.
     * @param arr input array
     * @param left left pointer
     * @param right right pointer
     * @param stats statistics for comparisons and exchanges
     */
    public static void quickSortWithCutoff(int[] arr, int left, int right, int cutoff, SortStats stats) {
        if (right - left + 1 <= cutoff) {
            insertionSort(arr, left, right, stats);
            return;
        }
        int pivot = arr[left];
        int pivotIndex = partition(arr, left, right, pivot, stats);
        quickSortWithCutoff(arr, left, pivotIndex - 1, cutoff, stats);
        quickSortWithCutoff(arr, pivotIndex, right, cutoff, stats);
    }


    /**
     * Insertion sort function
     * @param arr input array
     * @param left left index
     * @param right right index
     * @param stats statistics for comparisons and exchanges
     */
    private static void insertionSort(int[] arr, int left, int right, SortStats stats) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && compare(arr[j], key, stats) > 0) {
                arr[j + 1] = arr[j];
                j--;
                stats.exchanges++;
            }
            arr[j + 1] = key;
            stats.exchanges++;
        }
    }

    /**
     * Find the median of the array
     * @param arr input array
     * @param left left pointer
     * @param right right pointer
     * @param stats statistics for comparisons and exchanges
     * @return median numberof the array
     */
    private static int medianOfThree(int[] arr, int left, int right, SortStats stats) {
        int mid = (left + right) / 2;
        int a = arr[left], b = arr[mid], c = arr[right];
        stats.comparisons += 3;
        // a is median
        if ((a - b) * (a - c) <= 0) {
            return a;
        } // b is median
        else if ((b - a) * (b - c) <= 0) {
            return b;
        }  // else, c is median
        else {
            return c;
        }
    }
}