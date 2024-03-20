package leetcode;

/**
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 */
public class SwapPairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode curr = head.next;
        ListNode prev = head;
        ListNode grand = new ListNode(0,head);
        ListNode root = new ListNode(-1,grand);
        int i = 1;
        while (curr != null){
            i++;
            if (i % 2 == 0){
                // 交换
                ListNode next = curr.next;
                prev.next = curr.next;
                curr.next = prev;
                prev = curr;
                grand.next = prev;
                grand = grand.next;
                curr = next;
            }else {
                prev = curr;
                grand = grand.next;
                curr = curr.next;
            }
        }
        return root.next.next;
    }

    // 递归
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode one = head;
        ListNode two = head.next;
        ListNode three = swapPairs2(head.next.next);
        one.next = three;
        two.next = one;
        return two;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, 2, 3, 4);
        System.out.println(listNode);
        System.out.println(swapPairs2(listNode));
    }
}
