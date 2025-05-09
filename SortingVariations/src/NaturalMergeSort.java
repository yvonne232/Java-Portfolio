import java.util.ArrayList;
import java.util.List;

/**
 * This is a class for natural merge sort using linked list
 */
public class NaturalMergeSort {

    /**
     * Node class to define one node in merge sort
     */
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }

    /**
     * This is the sort statistics class for recording comparisons and exchanges.
     */
    public static class SortStats {
        public int comparisons = 0;
        public int exchanges = 0;

        @Override
        public String toString() {
            return "Comparisons: " + comparisons + ", Exchanges: " + exchanges;
        }
    }

    /**
     * This is a sort class that divides the list into 2 pieces, sort them and then merge them
     * @param head head of the linked list
     * @param stats sort statistics
     * @return head of the new sorted linked list
     */
    public static Node sort(Node head, SortStats stats) {
        // Base case
        if (head == null || head.next == null) {
            return head;
        }

        // Find middle
        Node middle = findMiddle(head, stats);
        Node secondHalf = middle.next;
        // Break the link between middle and middle.next
        middle.next = null;

        Node left = sort(head, stats);
        Node right = sort(secondHalf, stats);

        return merge(left, right, stats);
    }

    /**
     * This is a class to find the middle pointer of the list
     * @param head head of the list
     * @param stats sort statistics
     * @return middle pointer of the linked list
     */
    private static Node findMiddle(Node head, SortStats stats) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * This is a class in the merge sort part that merges 2 lists
     * @param a linked list a head
     * @param b linked list b head
     * @param stats sorting statistics
     * @return new sorted linked list head
     */
    private static Node merge(Node a, Node b, SortStats stats) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (a != null && b != null) {
            stats.comparisons++;
            if (a.data <= b.data) {
                tail.next = a;  // Link node to a
                a = a.next; // Move forward in list a
            } else {
                tail.next = b; // Link node to b
                b = b.next; // Move forward in list b
            }
            stats.exchanges++; // I consider appending new nodes in the linked list is an exchange
            tail = tail.next;
        }

        // If there are still nodes left in a
        while (a != null) {
            tail.next = a;
            a = a.next;
            tail = tail.next;
            stats.exchanges++;
        }

        // If there are still nodes left in b
        while (b != null) {
            tail.next = b;
            b = b.next;
            tail = tail.next;
            stats.exchanges++;
        }
        return dummy.next;
    }

    /**
     * This is a class to convert one array to one linked list if the input is an array
     * @param arr input array
     * @return head of the corresponding linked list
     */
    public static Node fromArray(int[] arr) {
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * This is a class to convert linked list to an array if needed
     * @param head head of the linked list
     * @return corresponding array
     */
    public static int[] toArray(Node head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.data);
            head = head.next;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

}
