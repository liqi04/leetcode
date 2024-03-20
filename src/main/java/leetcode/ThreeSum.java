package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class ThreeSum {

    public static Integer[][] threeSum(int[] nums){
        List<Integer[]> list = new ArrayList<>();
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
//                if (nums[j] == nums[i]){
//                    continue;
//                }
                for (int k = j+1; k < nums.length; k++) {
//                    if (nums[k] == nums[j]){
//                        continue;
//                    }
                    if (nums[i]+nums[j]+nums[k] == 0){
                        list.add(new Integer[]{nums[i],nums[j],nums[k]});
                    }
                }
            }
        }
        return list.toArray(new Integer[][]{});
    }

    /**
     * 思路：
     * 1. 先把数组排序，
     * 2. 如果某三个数和等于0，由于数组为递增顺序，那么大于第三个数的数之和必大于0
     * 3. 如果某三个数和等于0，由于数组为递增顺序，那么小于第三个数的数之和必小于于0
     */
    public static List<List<Integer>> threeSumDoubleIndex(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length < 3){
            return new ArrayList<>();
        }
        for (int i = 0; i < nums.length; i++) {
            //跳过重复值
            if ( i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int l = i + 1;
            int r = nums.length -1;
            while (l < r){
                int result = nums[i] + nums[l] + nums[r];
                if (result > 0){
                    r--;
                }
                if (result < 0){
                    l++;
                }
                if (result == 0){
                    list.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    //跳过重复值
                    while (l < r && nums[l] == nums[l+1]){
                        l++;
                    }
                    //跳过重复值
                    while (l < r && nums[r] == nums[r-1]){
                        r--;
                    }
                    l++;
                    r--;

                }

            }
        }

        return list;
    }

    public static List<List<Integer>> threeSumDoubleIndex2(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 3){
            return new ArrayList<>();
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int l = i + 1;
            int r = nums.length -1;
            while (l < r){
                int result = nums[i] + nums[l] + nums[r];
                if (result > 0){
                    r--;
                }
                if (result < 0){
                    l++;
                }
                if(result ==0){
                    list.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while (l < r && nums[l] == nums[l+1]){
                        l++;
                    }
                    while (l < r && nums[r] == nums[r-1]){
                        r--;
                    }
                }
                l++;
                r--;

            }
        }
        return list;
    }
    public static void main(String[] args) {
        System.out.println(threeSumDoubleIndex(new int[]{-1, -1,0, 0,0,0,0,1, 2, -1, -4}));
    }

}
