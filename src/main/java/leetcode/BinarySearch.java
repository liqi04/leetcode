package leetcode;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 5, 5, 5, 5, 6, 6};
//        System.out.println(binarySearch1(nums, 5));
//        System.out.println(binarySearch1(nums, 6));
        System.out.println(binarySearch1(nums, 2));
    }

    public static int binarySearch1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) { // [left,right)
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid; // [left,mid)
            }
            if (nums[mid] > target) {
                right = mid-1; // [left,mid)
            }
            if (nums[mid] < target) {
                left = mid + 1; // [mid+1,right)
            }
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * 二分查找闭区间写法 即[left,right]
     * 为什么用 <= ?
     * 因为搜索区间为[left,right], 结束条件为 left > right, 写成区间为[right+1,right] 此时区间内没有合法的数字了，
     * 如果用 < 那么结束条件为 left=right 写成区间为[right,right]，那么区间内还有right 这个下标没搜索，可以加个判断 num[right] == target ? right : -1解决
     * 为什么用 left = mid + 1;right = mid - 1; 而不是 left = mid；right=mid?
     * 还是用区间标识[left,mid],[mid,right] 即[mid]搜索过了，所以下次搜索范围为[left,mid-1],[mid+1,right]
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {// 区间为[left,right] 结束条件为left = right+1 即[right+1,right]
            int mid = left + (right - left) / 2; // 防止数字溢出
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;// 区间为[left,mid-1]
            } else if (nums[mid] < target) {
                left = mid + 1; // 区间为[mid+1,right]
            }
        }
        return -1;
    }

    // 搜索左边界 [left,right)
    public static int binarySearchLeft(int[] nums, int target) {
        int left = 0, right = nums.length;
//        if (nums[left] > target || nums[right-1] < target){
//            return -1;
//        }
        // 区间为[left,right)
        while (left < right) { // 结束条件为left=right [left,left)
            int mid = left + (right - left) / 2; // 防止数字溢出
            if (nums[mid] == target) {// 关键处理：当nums[mid] == target时不返回，而是继续缩小空间在[left,mid)搜索，这是为什么能搜索左边界的原因
                // 区间为[left,mid)
                right = mid;
            }else if (nums[mid] > target) {
                // 区间为[left,mid)
                right = mid;
            } else if (nums[mid] < target) {
                // 区间为[mid+1,right)
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

    // 搜索右边界(left,right]
    public static int binarySearchRight(int[] nums, int target) {
        int left = 0, right = nums.length;
        if (nums[left] > target || nums[right-1] < target){
            return -1;
        }
        while (left < right) { // 结束条件为left=right (right,right]
            int mid = left + (right - left) / 2; // 防止数字溢出
            if (nums[mid] == target) {// 关键处理：当nums[mid] == target时不返回，而是继续缩小空间在(mid+1,right]搜索，这是为什么能搜索右边界的原因
                // 区间为(left,mid]
                left = mid + 1;
            }else if (nums[mid] > target) {
                // 区间为(left,mid]
                right = mid;
            }else if (nums[mid] < target) {
                // 区间为(left,mid]
                left = mid + 1;
            }
        }
        // 结束时 因为nums[mid] <= target 所以 nums[left] 一定不等于 target 而nums(mid) 有可能等于target, 有因为mid = left -1， left=right 所以返回right-1
        return nums[right - 1] == target ? right - 1 : -1;
    }
}
