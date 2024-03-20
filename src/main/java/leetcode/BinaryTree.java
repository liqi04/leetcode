package leetcode;

import java.util.LinkedList;

public class BinaryTree {


    /**
     * 二叉树最大深度递归解法，先求出子树深度，再加上自身深度
     * @param node
     * @return
     */
    public static int maxDepth(TreeNode node){
        if (node == null){
            return 0;
        }
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return Math.max(left,right)+1;
    }

    public static int maxDepth2(TreeNode node){
        return traverse(node,1,1);
    }

    public static int traverse(TreeNode node,int depth,int res){
        if (node == null){
            return depth;
        }
        depth++;
        if (node.left == null && node.right == null){
            return depth;
        }
        traverse(node.left,depth,res);
        traverse(node.right,depth,res);
        depth--;
        return Math.max(depth,res);
    }

    /**
     * 给定一个 2 叉树，找到其最大深度。
     *
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     * 使用迭代法遍历
     */
    public static int maxDepth3(TreeNode node){
        if (node == null){
            return 0;
        }
        int depth = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                TreeNode poll = queue.poll();
                if (poll.left != null){
                    queue.add(poll.left);
                }
                if (poll.right != null){
                    queue.add(poll.right);
                }
            }
            depth++;
        }
        return depth;
    }


    private static class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;
    }
    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 示例 1：
     *
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * 示例 2：
     *
     * 输入：lists = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：lists = [[]]
     * 输出：[]
     * 提示：
     *
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] 按 升序 排列
     * lists[i].length 的总和不超过 10^4
     */

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        while (true){
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min){
                    min = lists[i].val;
                    index = i;
                }
            }
            if (index == -1){
                break;
            }
            temp.next = lists[index];
            temp = temp.next;
            lists[index] = lists[index].next;
        }
        return head.next;
    }
}
