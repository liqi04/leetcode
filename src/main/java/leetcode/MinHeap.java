package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最小堆
 * 1. 完全二叉树
 * 2. 父节点的值小于等于子节点的值
 * 3. 用数组存储
 * 4. 父节点的索引为i, 左子节点的索引为2*i+1, 右子节点的索引为2*i+2
 * 5. 子节点的索引为i, 父节点的索引为(i-1)/2
 * 6. 插入元素: 先插入到数组的最后, 然后向上调整
 * 7. 删除元素: 先将数组的最后一个元素替换到要删除的元素, 然后向下调整
 * 8. 取出堆顶元素: 取出数组的第一个元素, 然后将数组的最后一个元素替换到第一个元素, 然后向下调整
 * 9. 建堆: 从最后一个非叶子节点开始, 依次向下调整
 * 10. 排序: 依次取出堆顶元素, 然后向下调整
 * 11. 时间复杂度: 插入O(logn), 删除O(logn), 取出堆顶元素O(1), 建堆O(n), 排序O(nlogn)
 * 12. 空间复杂度: O(n)
 * 13. 适用场景: 1. 优先级队列 2. 求topK
 * 14. 优点: 1. 插入删除效率高 2. 取出堆顶元素效率高 3. 建堆效率高
 * 15. 缺点: 1. 不适合查找 2. 不适合修改
 */
public class MinHeap {
    private int[] heap;
    private int capacity;
    private int size;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    public void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void shiftUp(int i) {
        while (i > 0 && heap[parent(i)] > heap[i]) {
            swap(heap, parent(i), i);
            i = parent(i);
        }
    }


    public void shiftDown(int i) {
        int minIndex = i;
        int l = left(i);
        if (l < size && heap[l] < heap[minIndex]) {
            minIndex = l;
        }
        int r = right(i);
        if (r < size && heap[r] < heap[minIndex]) {
            minIndex = r;
        }
        if (i != minIndex) {
            swap(heap, i, minIndex);
            shiftDown(minIndex);
        }
    }

    public void insert(int value) {
        if (size == capacity) {
            throw new RuntimeException("heap is full");
        }
        heap[size] = value;
        shiftUp(size);
        size++;
    }

    public int extractMin() {
        if (size == 0) {
            throw new RuntimeException("heap is empty");
        }
        int result = heap[0];
        swap(heap, 0, size - 1);
        size--;
        shiftDown(0);
        return result;
    }

    public void remove(int i) {
        heap[i] = Integer.MIN_VALUE;
        shiftUp(i);
        extractMin();
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("heap is empty");
        }
        return heap[0];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        printTree(0, "", sb);
        for (int i = 0; i < heap.length; i++) {
            sb.append(heap[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    private void printTree(int index, String prefix, StringBuilder sb) {
        if (index >= size) {
            return;
        }

        boolean isLeaf = index >= size / 2 && index < size;

        sb.append(prefix);
        sb.append("|-- ");
        sb.append(heap[index]);
        sb.append(isLeaf ? " (Leaf)" : "");
        sb.append("\n");

        if (index < size / 2) {
            printTree(left(index), prefix + "|   ", sb);
            printTree(right(index), prefix + "|   ", sb);
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(3);
        minHeap.insert(2);
        minHeap.insert(6);
        minHeap.insert(1);
        minHeap.insert(4);
        minHeap.insert(5);
        minHeap.insert(7);
        System.out.println(minHeap);
//        System.out.println("Extracted min value: " + minHeap.extractMin()); // Output: 1
//        System.out.println("Peek min value: " + minHeap.peek()); // Output: 2
//        System.out.println(minHeap);
//        System.out.println("Extracted min value: " + minHeap.extractMin()); // Output: 2
//        System.out.println("Peek min value: " + minHeap.peek()); // Output: 3
//        System.out.println(minHeap);
        for (int i = 0; i < 7; i++) {
            System.out.println("Extracted min value: " + minHeap.extractMin());
            System.out.println(minHeap);
        }
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        PriorityQueue<Integer> queue = new PriorityQueue<>(10, comparator.reversed());
        for (int i = 0; i < 10; i++) {
            queue.add(10-i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.poll());
        }

    }



}
