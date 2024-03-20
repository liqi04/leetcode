package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 */
public class GenerateParenthesis {
    static String[] arr = new String[]{"(",")"};
    public static List<String> generateParenthesis(int n) {
        LinkedList<String> trace = new LinkedList<>();
        List<String> res = new ArrayList<>();
//        backtrace(0,n,n,trace,res);
        dfs(n,n,"",res);
//        dfs("",n,n,res);
        System.out.println(res);
        return  res;
    }

    public static void backtrace(int i,int left,int right,LinkedList<String> trace,List<String> res){
        if (left < right){
            return;
        }
        if (left < 0 || right < 0 ){
            return;
        }
        if (left == 0 && right == 0){
            res.add(String.join("", trace));
            return;
        }
        trace.push("(");
        backtrace(i++,left-1,right,trace,res);
        trace.pop();
        i--;
//        trace.deleteCharAt(trace.length()-1);

        trace.push(")");
        backtrace(i++,left,right-1,trace,res);
        trace.pop();
        i--;
//        trace.deleteCharAt(trace.length()-1);
    }

    public static void dfs(int left, int right, String str, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(str.toString());
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(left - 1, right,str+("("),  res);
        }

        if (right > 0) {
            dfs( left, right - 1,str+(")"),  res);
        }

    }
    private static void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }


    public static void main(String[] args) {
        generateParenthesis(2);
    }
}
