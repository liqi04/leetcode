package leetcode;

import org.apache.kafka.common.network.ListenerName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 */

public class RemoveNthFromEnd {


    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode temp = head;
        int count = 1;
        while (temp.next != null) {
            count++;
            temp = head.next;
        }
        ListNode prev = new ListNode(0, head);
        ListNode curr = prev;
        for (int i = 0; i < count - n - 1; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return prev.next;
    }


    /**
     * 循环两次,第一次获取length，第二次更换指针
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = new ListNode(0, head), curr = head;
        ListNode temp = head;
        int j = 0;
        while (temp.next != null) {
            j++;
            temp = temp.next;
        }
        curr = prev;
        for (int i = 0; i < j - n + 1; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return prev.next;
    }

    /**
     * 循环一次，使用数组记录节点位置
     *
     * @param head
     * @param n
     * @param <List>
     * @return
     */
    public static <List> ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode ans = new ListNode(0, head);
        ListNode temp = head;
        ListNode[] arr = new ListNode[31];
        arr[0] = ans;
        int i = 1;
        while (temp.next != null) {
            arr[i] = temp;
            i++;
            temp = temp.next;
        }
        ListNode prev = arr[i - n];
        prev.next = prev.next.next;
        return ans.next;
    }

    /**
     * 使用双指针
     * @param head
     * @param n
     * @return
     * @param <List>
     */
    public static <List> ListNode removeNthFromEnd4(ListNode head, int n) {
        ListNode slow = head, fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * arr = [1,2,3,4,5]
     * <p>
     * <p>
     * 递归调用
     *
     * @param head
     * @param n
     * @param <List>
     * @return
     */


    public static <List> ListNode removeNthFromEnd3(ListNode head, int n) {
        int num = backtrack(head, n);
        if (num == n) {
            return head.next;
        }
        return head;
    }

    public static int backtrack(ListNode node, int n) {
        if (node == null) {
            return 0;
        }
        ListNode curr = node.next;
        int num = backtrack(curr, n);
        if (num == n) {
            node.next = curr.next;
        }
        return num + 1;
    }


    public static void main(String[] args) {
//        ListNode listNode = new ListNode(1);
//        ListNode listNode2 = new ListNode(2, listNode);
//        ListNode listNode3 = new ListNode(3, listNode2);
//        System.out.println(listNode3);
//        System.out.println(removeNthFromEnd3(listNode3, 2));
//        System.out.println(permute(new int[]{1, 2, 3, 4, 5}));
        nQueens(5);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }


        @Override
        public String toString() {
            return "" + val + (next != null ? "," + next : "") + "";
        }
    }

    // 全排列

    /**
     * result = []
     * def backtrack(路径, 选择列表):
     * if 满足结束条件:
     * result.add(路径)
     * return
     * <p>
     * for 选择 in 选择列表:
     * 做选择
     * backtrack(路径, 选择列表)
     * 撤销选择
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> link = new LinkedList<>();
        backtrack(link, nums, new LinkedList<>());
        System.out.println(link.size());
        return link;
    }

    public static void backtrack(List<List<Integer>> link, int[] nums, LinkedList<Integer> track) {

        if (track.size() == nums.length) {
            link.add(new ArrayList<>(track));
            return;
        }
        for (int num : nums) {
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            backtrack(link, nums, track);
            track.removeLast();
        }
    }

    /**
     * 给你一个 N×N 的棋盘，让你放置 N 个皇后，使得它们不能互相攻击。
     * <p>
     * PS：皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位。
     *
     * @param n
     * @return
     */
    public static int[][] nQueens(int n) {
        int[][] trace = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trace[i][j] = 0;
            }
        }
        ArrayList<String> ans = new ArrayList<>();
        backtrack(ans, trace, 0);
        System.out.println(ans.stream().collect(Collectors.joining("\n")));
        System.out.println(n + "皇后，解法数：" + ans.size());
        return null;
    }

    public static void backtrack(List<String> asn, int[][] trace, int row) {
        if (row == trace.length) {
            asn.add(arrayToString(trace));
            return;
        }
        for (int col = 0; col < trace.length; col++) {
            if (!isValid(trace, row, col)) {
                continue;
            }
            trace[row][col] = 1;
            backtrack(asn, trace, row + 1);
            trace[row][col] = 0;
        }
    }

    public static String arrayToString(int[][] arr) {
        String s = "";
        for (int i = 0; i < arr.length; i++) {
            s += Arrays.toString(arr[i]) + "\n";
        }
        return s;
    }

    public static boolean isValid(int[][] trace, int row, int col) {
        // 同一列
        for (int i = 0; i < row; i++) {
            if (trace[i][col] == 1) {
                return false;
            }
        }
        // 左上
        if (col > 0 && trace[row - 1][col - 1] == 1) {
            return false;
        }
        // 右上
        if (col < trace.length - 1 && trace[row - 1][col + 1] == 1) {
            return false;
        }
//        int n = trace.length;
//
//        // 检查列是否有皇后互相冲突
//        for (int i = 0; i < n; i++) {
//            if (trace[i][col] == 1)
//                return false;
//        }
//        // 检查右上方是否有皇后互相冲突
//        for (int i = row - 1, j = col + 1;
//             i >= 0 && j < n; i--, j++) {
//            if (trace[i][j] == 1)
//                return false;
//        }
//        // 检查左上方是否有皇后互相冲突
//        for (int i = row - 1, j = col - 1;
//             i >= 0 && j >= 0; i--, j--) {
//            if (trace[i][j] == 1)
//                return false;
//        }
        return true;
    }
}


