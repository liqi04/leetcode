package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 16. 最接近的三数之和
 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。

 返回这三个数的和。

 假定每组输入只存在恰好一个解。



 示例 1：

 输入：nums = [-1,2,1,-4], target = 1
 输出：2
 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 示例 2：

 输入：nums = [0,0,0], target = 1
 输出：0


 提示：

 3 <= nums.length <= 1000
 -1000 <= nums[i] <= 1000
 -104 <= target <= 104
 */
public class ThreeSumClosest {

    public static int threeSumDoubleIndex(int[] nums,int target){
        Arrays.sort(nums);
        if (nums.length == 3){
            return nums[0] + nums[1] + nums[2];
        }
        int result = nums[0] + nums[1] + nums[2];
        int diff = Math.abs(target-result);
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length -1;
            while (l < r){
                int temp = nums[i] + nums[l] + nums[r];
                if (temp == target){
                    return temp;
                }

                if (temp > target){
                    r--;
                    while (l < r && nums[r] == nums[r-1]){
                        r--;
                    }

                }
                if (temp < target){
                    l++;
                    while (l < r && nums[l] == nums[l+1]){
                        l++;
                    }
                }
                if (Math.abs(target - temp) <= diff){
                    result = temp;
                    diff = Math.abs(target - temp);
                }

            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(threeSumDoubleIndex(new int[]{1,1,1,1,0},0));
    }

}
