package leetcode;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 *  
 *
 * 示例 1：
 *
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 *
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 *
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 *  
 *
 * 提示：
 *-231 <= x <= 231 - 1
 * -231 <= x <= 231 - 1
 *  
 *
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Palindrome {

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)){
            return false;
        }
        int r = 0;
        while (x > r){
            r = x % 10 + r * 10;
            x /= 10;
        }
        return r == x || x == r/10;
    }

    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
           if (s.charAt(i) != s.charAt(s.length()-1-i)){
               return false;
           }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("121"));
        System.out.println(isPalindrome(121));
    }
}
