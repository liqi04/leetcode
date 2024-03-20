package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DPTest {

    public int fib(int n){
        if (n == 1 || n ==2){
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public int fib2(int n){
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[n-1]+dp[n-2];
        }
        return dp[n];
    }

    public int fib3(int n){
        int dp1 = 1,dp2 = 1,dp=1;
        for (int i = 2; i < n; i++) {
            dp = dp1 + dp2;
            dp1 = dp2;
            dp2 = dp;
        }
        return dp;
    }
    private static int[] mem = null;
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        if (amount < 0){
            return -1;
        }
        if (mem[amount] != -1){
            return mem[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int n = coinChange(coins,amount - coins[i]);
            if (n == -1){
                continue;
            }
            res = Math.min(res,n+1);
        }
        return res;
    }

    /**
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
     *
     * 叶子节点 是指没有子节点的节点。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null){
            return false;
        }
        boolean r1 = root.left != null && hasPathSum(root.left, targetSum - root.val);
        boolean r2 = root.right != null && hasPathSum(root.right, targetSum - root.val);
        boolean r3 = root.left == null && root.right == null && root.val == targetSum;
        return r1 || r2 || r3;
    }

    /**
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     *
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     *
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/distinct-subsequences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     */

    public static int numDistinct(String s, String t) {

        return -1;
    }


    /**
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     *
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     *
     *  
     *
     * 示例 1：
     *
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
     * 示例 2：
     *
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     *      注意，你可以重复使用字典中的单词。
     * 示例 3：
     *
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 300
     * 1 <= wordDict.length <= 1000
     * 1 <= wordDict[i].length <= 20
     * s 和 wordDict[i] 仅有小写英文字母组成
     * wordDict 中的所有字符串 互不相同
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/word-break
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param wordDict
     * @return
     * 思路：
     * 循环wordDict，判断s是否以当前字符串开头，如果是则将s剩下的字符串继续比较，伪代码如下：
     * fun dp(s):
     *  for word in wordDict
     *    if s.startWith(word)
     *       res = dp(s-word)
     *       if res
     *          return true
     * return false
     */

    public static boolean wordBreak(String s, List<String> wordDict) {
        int[] mem = new int[s.length()+1];
        Arrays.fill(mem,-1);
       return wordBreakDP(s,0,wordDict,mem);
    }
    public static boolean wordBreakDP(String s, int i, List<String> wordDict, int[] mem) {
        if (mem[i] != -1){
            return mem[i] > 0;
        }
        for (String word : wordDict) {
            if (s.length() == 0){
                return true;
            }
            if (!s.startsWith(word)){
                continue;
            }
            if (wordBreakDP(s.substring(word.length()), i+word.length(), wordDict, mem)) {
                mem[i] = 1;
                return true;
            }
        }
        mem[i] = 0;
        return false;
    }

    /**
     * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
     * <p>
     * 注意：词典中的同一个单词可能在分段中被重复使用多次。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
     * 输出:["cats and dog","cat sand dog"]
     * 示例 2：
     * <p>
     * 输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
     * 输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
     * 解释: 注意你可以重复使用字典中的单词。
     * 示例 3：
     * <p>
     * 输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
     * 输出:[]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 20
     * 1 <= wordDict.length <= 1000
     * 1 <= wordDict[i].length <= 10
     * s 和 wordDict[i] 仅有小写英文字母组成
     * wordDict 中所有字符串都 不同
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/word-break-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param wordDict
     * @return
     */
    public static List<String>[] wordBreak2Mem;
    public static List<String> wordBreak2Res = new ArrayList<>();
    public static List<String>  wordBreak2(String s, List<String> wordDict) {
        List<String>[] wordBreak2Mem  = new List[s.length()];
        Arrays.fill(wordBreak2Mem,null);
        return wordBreakDP2(s,0,wordDict,wordBreak2Mem);
    }

    public static List<String> wordBreakDP2(String s, int i, List<String> wordDict, List<String>[] wordBreak2Mem) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0){
            res.add("");
            return res;
        }
        if (wordBreak2Mem[i]!=null){
            return wordBreak2Mem[i];
        }
        for (String word : wordDict) {
            int len = word.length();
            if (!s.startsWith(word)) {
                continue;
            }
            List<String> subRes = wordBreakDP2(s.substring(len), i + len, wordDict, wordBreak2Mem);
            if (subRes.size() > 0){
                subRes.forEach(o -> res.add((word + (o.length() == 0 ? "" : (" "  + o) ))));
            }
        }
        wordBreak2Mem[i] = res;
        return res;
    }


    public static void main(String[] args) {
//        int[] coins = {1, 2, 3, 4, 5};
//        int amount = 10;
//        mem = new int[amount+1];
//        Arrays.fill(mem,-1);
//        int i = coinChange(coins, amount);
//        System.out.println(i);
//        System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//                Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
//        "pineapplepenapple"
//                ["apple","pen","applepen","pine","pineapple"]
//        "catsanddog"
//                ["cat","cats","and","sand","dog"]
        System.out.println(wordBreak2("catsanddog",
                Arrays.asList("cat","cats","and","sand","dog")));
    }



      private static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
}
