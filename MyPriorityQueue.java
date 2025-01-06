/**
 * A custom implementation of a priority queue using a binary heap.
 * This class extends AbstractQueue and supports generic types.
 *
 * @param <T> the type of elements held in this priority queue
 */

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class MyPriorityQueue<T> extends AbstractQueue<T> {
    /** Comparator used for ordering elements in the priority queue */
    private Comparator<T> cmp;

    /** ArrayList to store the elements of the binary heap */
    private ArrayList<T> heap;

    /**
     * Constructs a priority queue with a specified initial capacity and comparator.
     *
     * @param initial_capacity the initial capacity of the heap
     * @param cmp              the comparator to order the elements
     */
    public MyPriorityQueue(int initial_capacity, Comparator<T> cmp) {
        this.cmp = cmp;
        this.heap = new ArrayList<>(initial_capacity);
    }

    /**
     * Constructs a priority queue with a default initial capacity and a comparator.
     *
     * @param cmp the comparator to order the elements
     */
    public MyPriorityQueue(Comparator<T> cmp) {
        this(11, cmp); // Default initial capacity of 11
    }

    /**
     * Returns the index of the parent node of a given node.
     *
     * @param index the index of the current node
     * @return the index of the parent node
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the index of the left child of a given node.
     *
     * @param index the index of the current node
     * @return the index of the left child
     */
    private int lchild(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the index of the right child of a given node.
     *
     * @param index the index of the current node
     * @return the index of the right child
     */
    private int rchild(int index) {
        return 2 * index + 2;
    }

    /**
     * Retrieves the comparator used to order the elements.
     *
     * @return the comparator used in the priority queue
     */
    public Comparator<T> comparator() {
        return cmp;
    }

    /**
     * Returns an iterator over the elements in the priority queue.
     *
     * @return an iterator over the elements
     */
    public Iterator<T> iterator() {
        return heap.iterator();
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return the size of the priority queue
     */
    public int size() {
        return heap.size();
    }

    /**
     * Removes all elements from the priority queue.
     */
    public void clear() {
        heap.clear();
    }

    /**
     * Inserts the specified element into the priority queue.
     *
     * @param elt the element to be added
     * @return true if the element was added successfully, false otherwise
     */
    public boolean offer(T elt) {
        if (elt == null) {
            throw new NullPointerException("Cannot add null element to the priority queue.");
        }
        heap.add(elt);
        percolateUp(heap.size() - 1);
        return true;
    }

    /**
     * Retrieves, but does not remove, the head of the priority queue,
     * or returns null if the queue is empty.
     *
     * @return the head of the queue, or null if empty
     */
    public T peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    /**
     * Retrieves and removes the head of the priority queue,
     * or returns null if the queue is empty.
     *
     * @return the head of the queue, or null if empty
     */
    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }
        T result = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            percolateDown(0);
        }
        return result;
    }

    /**
     * Moves an element down the heap to maintain heap order.
     *
     * @param hole the index of the element to be moved
     */
    private void percolateDown(int hole) {
        T value = heap.get(hole);
        int size = heap.size();
        while (lchild(hole) < size) {
            int child = lchild(hole);
            if (child + 1 < size && cmp.compare(heap.get(child + 1), heap.get(child)) < 0) {
                child = child + 1;
            }
            if (cmp.compare(heap.get(child), value) < 0) {
                heap.set(hole, heap.get(child));
                hole = child;
            } else {
                break;
            }
        }
        heap.set(hole, value);
    }

    /**
     * Moves an element up the heap to maintain heap order.
     *
     * @param hole the index of the element to be moved
     */
    private void percolateUp(int hole) {
        T value = heap.get(hole);
        while (hole > 0 && cmp.compare(value, heap.get(parent(hole))) < 0) {
            heap.set(hole, heap.get(parent(hole)));
            hole = parent(hole);
        }
        heap.set(hole, value);
    }
}
