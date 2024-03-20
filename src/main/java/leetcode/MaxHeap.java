package leetcode;

import java.util.Arrays;

/**
 * 大顶堆
 * 1. 完全二叉树
 * 2. 父节点的值大于等于子节点的值
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
 * 9, 7, 8, 6, 1, 2, 4, 3, 5
 *             9
 *           7   8
 *         6  1 2  4
 *       3 5
 *
 *
 * 小顶堆：
 * 1, 3, 2, 5, 7, 8, 4, 6, 9
 *             1
 *         3       2
 *      5    7   8   4
 *    6  9
 */
public class MaxHeap {
    private int[] heap;
    private int capacity;
    private int size;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    public static void buildMax(int[] heap, int i, int heapSize){
        System.out.println(Arrays.toString(heap));
        int left = 2 * i + 1;//左节点
        int right = 2 * i + 2;//右节点
        int largest = i;// 根节点
        // left > root
        if (left < heapSize && heap[left] > heap[largest]){
            largest = left;
        }
        // right > root
        if (right < heapSize && heap[right] > heap[largest]){
            largest = right;
        }
        // 如果最大值不是根节点，那么调整根节点和最大值位置
        if (largest != i){
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            buildMax(heap,largest,heapSize);
        }
    }

    public static void buildMin(int[] heap, int i, int heapSize){
        System.out.println(Arrays.toString(heap));
        int left = 2 * i + 1;//左节点
        int right = 2 * i + 2;//右节点
        int parent = i;// 根节点
        // left > root
        if (left < heapSize && heap[left] < heap[parent]){
            parent = left;
        }
        // right > root
        if (right < heapSize && heap[right] < heap[parent]){
            parent = right;
        }
        // 如果最大值不是根节点，那么调整根节点和最大值位置
        if (parent != i){
            int temp = heap[i];
            heap[i] = heap[parent];
            heap[parent] = temp;
            buildMin(heap,parent,heapSize);
        }
    }

    public static void printHeapTree(int i, String indent, int[] heap, int size) {
        if (i < size) {
            printHeapTree(2 * i + 2, indent + "     ", heap, size); // print right subtree
            System.out.println(indent + heap[i]); // print root node
            printHeapTree(2 * i + 1, indent + "     ", heap, size); // print left subtree
        }
    }

    public static void printHeapTree(int i, int level, int[] heap, int size) {
        if (i < size) {
            printHeapTree(2 * i + 2, level + 1, heap, size); // print right subtree
            for (int j = 0; j < level; j++) {
                System.out.print("      ");
            }
            System.out.println(heap[i] + "      "); // print root node
            printHeapTree(2 * i + 1, level + 1, heap, size); // print left subtree
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 8, 5, 1,2,4,6,9};
        int n = arr.length;
        // 初始化堆 初始化后就可以得到 根节点为最大值
        // 在构建大顶堆的过程中，我们需要从非叶子节点开始向下调整。
        // 在完全二叉树中，给定一个节点的索引 i，其左子节点的索引为 2i+1，右子节点的索引为 2i+2，数组长度为n。
        // 因此，对于任何一个节点 i，如果 2*i+1 >= n，那么这个节点就是一个叶子节点。
        // 我们可以将这个不等式进行变形，得到 i < n/2。这就意味着，所有的非叶子节点 i 都满足 i < n/2。
        // 因此，最后一个非叶子节点的索引就是 n/2 - 1（在数组索引从0开始的情况下）。
        // 所以，我们从 n/2 - 1 开始向下调整，确保了所有的非叶子节点都会被处理，从而正确地构建出大顶堆。
        for (int i = n / 2 - 1; i >= 0; i--){
            buildMax(arr, i, arr.length);
        }
        printHeapTree(0,"",arr,arr.length);
        System.out.println("------------------------");
        //依次把最大值沉下去 从右到左断开最后一个元素，重新构造大顶堆 也就是从小到大 排序
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            buildMax(arr, 0, i);
        }
        // 打印结果
        System.out.println(Arrays.toString(arr));
    }
}
