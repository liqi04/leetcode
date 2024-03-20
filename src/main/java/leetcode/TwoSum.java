package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @author : lq
 * @date : 2021/4/22
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{nums[left], nums[right]};
            }
            //跳过重复
            if (nums[left] == nums[left + 1]) {
                left++;
            }
            //跳过重复
            if (nums[right] == nums[right - 1]) {
                right--;
            }
            left++;
            right--;
        }
        return null;
    }

    public static void test1() {
        int length = 0;
        int maxLength = 0;
        String a = "hello nowcoder";
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == ' ') {
                maxLength = Math.max(length, maxLength);
                length = 0;
            } else {
                length++;
            }
        }
        System.out.println(length);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str=br.readLine())!=null){
            System.out.println(str);
        }
    }
}
