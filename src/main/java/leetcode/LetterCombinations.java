package leetcode;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *  
 *
 * 提示：
 *
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ["2", "9"] 的一个数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LetterCombinations {
    public static List<String> letterCombinations(String digits){
        if (digits.length() ==0){
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        Map<Character,String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        if (digits.length() == 1){
            return Arrays.asList(map.get(digits.charAt(0)).split(""));
        }
        List<String> res = new ArrayList<>();
        backtrack(digits,0,new StringBuilder(),res,map);
        return res;
    }

    public static void backtrack(String c,int index, StringBuilder track, List<String> res,  Map<Character, String> map) {
        // 触发结束条件
        if (track.length() == c.length()) {
            res.add(track.toString());
            return;
        }

        String nums = map.get(c.charAt(index));
        for (int i = 0; i < nums.length(); i++) {
            // 做选择
            track.append(nums.charAt(i));
            index++;
            // 进入下一层决策树
            backtrack(c,index, track,res, map);
            // 取消选择
            index--;
            track.delete(track.length()-1,track.length());
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("234"));
    }
}
