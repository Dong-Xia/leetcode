package xia.ncut;

/**
 * 最大子序和：
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaximumSubarray53 {
    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int cur = 0;
        int result = nums[0];
        for (int num : nums) {
            if (cur > 0) {
                cur += num;
            } else {
                cur = num;
            }
            result = Math.max(result, cur);
        }
        return result;
    }
}
