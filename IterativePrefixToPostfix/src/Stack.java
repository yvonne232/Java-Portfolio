/**
 * Name: Ziyu(Yvonne) Lin
 * Lab1
 * EN.605.202
 */

/**
 * This class builds stack data structure
 * @param <T> abstract data type
 */
public class Stack<T>
{
    private Node<T> head;
    private int size;

    /**
     * This is the private node class
     * @param <T> abstract data type
     */
    private class Node<T>
    {
        T data;
        Node<T> next;

        public Node(T data)
        {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Stack Constructor
     */
    public Stack()
    {
        this.head = null;
        this.size = 0;
    }

    /**
     * This is to determine whether a stack is empty
     * @return true if the stack is empty
     */
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * This is to push extra data to the stack
     * @param data new data
     */
    public void push(T data)
    {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * This is to pop data out of the stack
     * @return T
     */
    public T pop()
    {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /**
     * This is to look at the top value of the stack but do not change the stack
     * @return T
     */
    public T peek()
    {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return head.data;
    }

    /**
     * This returns the size of the stack
     * @return size of the stack
     */
    public int size()
    {
        return size;
    }
}
