package leetcode;

/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};int target = 3;
        System.out.println(search(nums, target));
    }

    /**
     * 1. 先查找中间值
     * 2. left小于mid，说明left - mid为升序
     * 3. 右边小于中间值，说明右边为升序
     * @param nums
     * @param target
     * @return
     */
    public int searchInRotatedSortedArray(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        int mid = left + (right - left) /2;
        while (left <= right){
            if (nums[mid] == target){
                return mid;
            }else if (nums[left] < nums[mid] ){ // [left,mid] 为升序
                // if num[left,mid] contains target
                if (nums[left] < target && target < nums[mid]) {
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                // num[mid,right] contains target
                if (nums[mid] < target && target < nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }

        }

        return -1;
    }


    /**
     * 二分查找
     *1. 先查找中间值,如果 right < mid ,说明 mid 的
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[left] <= nums[mid]){
                // if num[0,mid] contains target
                if (nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                // if num[0,mid] contains target
                if (nums[right] >= target && target > nums[mid]){
                    left = mid + 1;
                }else {
                    right = mid  - 1;
                }
            }
        }
        return -1;
    }
}
