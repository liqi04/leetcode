package leetcode;

/**
 * <a href="https://leetcode.cn/problems/merge-two-sorted-lists/">...</a>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2,new ListNode(4)));
        ListNode listNode2 = new ListNode(1, new ListNode(3,new ListNode(4)));
        ListNode listNode1 = mergeTwoListsRecursion(listNode, listNode2);
        System.out.println(listNode1);

    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode prev = head;
        while (list1 != null && list2 != null){
            if (list1.val > list2.val){
                prev.next = list2;
                list2 = list2.next;
            }else {
                prev.next = list1;
                list1 = list1.next;
            }
            prev = prev.next;
        }
        prev.next = list1 != null ? list1 : list2;

        return head.next;
    }

    public static ListNode mergeTwoListsRecursion(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        if (list1.val > list2.val){
            list2.next = mergeTwoListsRecursion(list1,list2.next);
            return list2;
        }else {
            list1.next = mergeTwoListsRecursion(list1.next,list2);
            return list1;
        }

    }
     private static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }

         @Override
         public String toString() {
             return val+"," + (next != null ? next.toString() : "");
         }
     }

}
