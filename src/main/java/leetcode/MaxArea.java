package leetcode;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 *  
 *
 * 提示：
 *
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxArea {

    public static void main(String[] args) {
        int[] heights = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea2(heights));
    }


    /**
     * 暴力求解
     * @param heights
     * @return
     */
    public static int maxArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i+1; j < heights.length; j++) {
                int width = j -i;
                int height = heights[i] - heights[j] > 0 ? heights[j] : heights[i];
                int area = width * height;
                if (area > maxArea){
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    /**
     * 双指针
     * @param heights
     * @return
     */
    public static int maxArea2(int[] heights) {
        int l = 0, r= heights.length -1, area = 0;
        while (l < r){
            area = Math.max((r - l) * Math.min(heights[r], heights[l]), area);
            if ( heights[l] < heights[r]){
                l ++;
            }else {
                r--;
            }
        }
        return area;
    }


}
