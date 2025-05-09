import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SortsTest {

    @Test
    public void testQuickSortA() {
        int[] arr = {5, 3, 3, 8, 4, 4, 2};
        QuickSort.SortStats stats = new QuickSort.SortStats();
        QuickSort.quickSortA(arr, 0, arr.length - 1, stats);
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println(stats.toString());
        assertArrayEquals(new int[]{2, 3, 3, 4, 4, 5, 8}, arr);
    }

    @Test
    public void testQuickSortB() {
        int[] arr = {5, 3, 3, 8, 4, 4, 2};
        QuickSort.SortStats stats = new QuickSort.SortStats();
        QuickSort.quickSortB(arr, 0, arr.length - 1, stats);
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println(stats.toString());
        assertArrayEquals(new int[]{2, 3, 3, 4, 4, 5, 8}, arr);
    }

    @Test
    public void testQuickSortC() {
        int[] arr = {5, 3, 3, 8, 4, 4, 2};
        QuickSort.SortStats stats = new QuickSort.SortStats();
        QuickSort.quickSortC(arr, 0, arr.length - 1, stats);
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println(stats.toString());
        assertArrayEquals(new int[]{2, 3, 3, 4, 4, 5, 8}, arr);
    }

    @Test
    public void testQuickSortD() {
        int[] arr = {5, 3, 3, 8, 4, 4, 2};
        QuickSort.SortStats stats = new QuickSort.SortStats();
        QuickSort.quickSortD(arr, 0, arr.length - 1, stats);
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println(stats.toString());
        assertArrayEquals(new int[]{2, 3, 3, 4, 4, 5, 8}, arr);
    }

    @Test
    public void testMergeSort() {
        int[] arr = {5, 3, 3, 8, 4, 4, 2};
        NaturalMergeSort.SortStats stats = new NaturalMergeSort.SortStats();
        NaturalMergeSort.Node head = NaturalMergeSort.fromArray(arr);
        head = NaturalMergeSort.sort(head, stats);
        System.out.println(stats.toString());

        int[] sorted = NaturalMergeSort.toArray(head);;
        System.out.println(java.util.Arrays.toString(sorted));
        assertArrayEquals(new int[]{2, 3, 3, 4, 4, 5, 8}, sorted);
    }

    @Test
    public void testQuickSortWithCutoff() {
        int[] arr = {5, 3, 3, 8, 4, 4, 2};
        QuickSort.SortStats stats = new QuickSort.SortStats();
        QuickSort.quickSortWithCutoff(arr, 0, arr.length - 1, 200, stats);
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println(stats.toString());
        assertArrayEquals(new int[]{2, 3, 3, 4, 4, 5, 8}, arr);
    }
}
