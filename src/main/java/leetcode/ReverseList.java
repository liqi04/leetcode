package leetcode;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseList {



    /**
     *  单向链表只有next索引，
     *  1. 保存当前节点的next节点
     *  2. 当前节点的next指向前一个节点
     *  3. 前一个节点指向当前节点
     *  4. 当前节点指向保存的next节点
     *  5. 重复1-4步骤
     *         head           tail
     *  next = curr.next
     *  curr.next = prev
     *  prev = curr
     *  curr = next
     *  原始链表null -> 1 -> 2 -> 3 -> 4
     *         prev=null, head=cur
     *         next = cur.next = 2
     *         cur.next = prev = null
     *         prev = cur = 1
     *         cur = next =2
     *         1 -> null
     *         2 -> 3 -> 4
     *         next = cur.next = 3
     *         cur.next = prev = 1
     *         prev = cur = 2
     *         cur = next = 3
     *         2 -> 1 -> null
     *
     *         next = cur.next = 4
     *         cur.next = prev = 2
     *         prev = cur = 3
     *         cur = next = 4
     *         3 -> 2 -> 1 -> null
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        ListNode prev =null,curr= head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev =null, curr = head;
        while (curr!=null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * null <- 1 <- 2 <- 3 <- 4
     * 1    1.2.next = 1, 1.next = null
     *      2   2.3.next = 2,2.next = null
     *          3   3.4.next = 3,3.next = null
     *              4.next is null,return
     * @param head
     * @return
     */
    public static ListNode reverseListRecursion(ListNode head) {
        if (head ==null || head.next == null ){
            return head;
        }
        ListNode listNode = reverseListRecursion(head.next);
        head.next.next  = head;
        head.next = null;
        return listNode;
    }

    public static ListNode reverseListIterator(ListNode head) {
        ListNode ans = null;
        for (ListNode x = head; x != null; x = x.next){
            ans = new ListNode(x.val,ans);
        }
        return ans;

    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2,listNode);
        ListNode listNode3 = new ListNode(3,listNode2);
        System.out.println(listNode3);
        ListNode listNode1 = reverseListRecursion(listNode3);
        System.out.println(listNode1);
    }



    //    Definition for singly-linked list.
    public static class ListNode {
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
            return ""+val+(next!=null ? ","+ next :"")+"";
        }
    }

}
