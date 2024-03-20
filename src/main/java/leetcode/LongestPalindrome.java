package leetcode;

import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abacbad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cdbbdc"
 * 输出："bb"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class LongestPalindrome {

    /**
     * 设 f(i,j) 为回文子串，那么 f(i+1,j-1) 也为回文子串 并且 s[i] == s[j]
     *  动态规划技巧
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
        }
        int begin = 0, length = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length ; j++) {
                int k = i + j +1;
                if (k >= chars.length || k <= i){
                    break;
                }
                if (chars[j] != chars[k]) {
                   dp[j][k] = false;
                }else {
                    dp[j][k] = k - j < 3 || dp[j + 1][k - 1];
                }
                if (dp[j][k] && k - j  > length) {
                    begin = j;
                    length = k - j ;
                }
            }
        }
        return s.substring(begin,begin + length + 1);
    }

    /**
     * 双指针
     * 先求单字母的回文串，再求双字母的回文串，取二者最大值
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);
            String s3 = s1.length() > s2.length() ? s1 : s2;
            res = res.length() > s3.length() ? res : s3;
        }
        return res;
    }

    private static String palindrome(String s,int l,int r){
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return s.substring(l+1,r);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome2("aaccc"));
    }
}
