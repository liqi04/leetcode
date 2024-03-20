package leetcode;

import java.util.Arrays;

public class StringFirstIndex {

    /**
     * abcdddcacd
     *       cacd
     * "mississippi"
     * " issip"
     * @param origin
     * @param search
     * @return
     */
    public static int strStr(String haystack ,String needle){
        if (haystack.length() < needle.length()){
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            int k = i;
            int j = 0;
            while (j< needle.length() && k < haystack.length() &&  haystack.charAt(k) == needle.charAt(j)){
                k++;j++;
            }
            if (j == needle.length()){
                return i;
            }
        }
        return -1;
    }

    public static int[] searchRange(int[] nums, int target) {
        int left = searchLeft(nums, target);
        int right = searchLeft(nums, target+1);
        if(left  == right) return new int[]{-1,-1};
        return new int[]{left,right-1};
    }

    // l=0,r=6,m=3
    // l=0,r=3,m=1
    // l=2,r=3,m=1
    // l=3,r=2,m=2
    public static int searchLeft(int[] nums, int target) {
        int left = 0,right = nums.length;
        while(left < right){// 循环结束条件为left=right
            int mid = (right + left) >> 1;
            if(nums[mid] >= target){// 中间值 >= 目标值，
                right = mid; // 右边界的值 >= 目标值
            }else {// 中间值 < 目标值，则将左边界向前推进
                left = mid + 1;
            }
        }
        // 右边界的值 >= 目标值始终大于目标值，左边界的值最大等于目标值，当left = right 时，left=right时，left即为target 第一个值的位置
        // 因为右边界的值 >= 目标值 且 循环结束条件为left=right
        return left;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7,8, 8,8,10}, 6)));
    }
}
