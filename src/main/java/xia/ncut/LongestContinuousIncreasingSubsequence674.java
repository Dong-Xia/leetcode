package xia.ncut;

/**
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 * 注意：数组长度不会超过10000。
 */
public class LongestContinuousIncreasingSubsequence674 {
    /**
     * 定义一个哨兵
     */    public int findLengthOfLCIS(int[] nums) {
        if(nums.length==0) return 0;
        int max = nums[0];
        // 记录当前序列的大小
        int sum = 1;
        // 记录最大的连续递增序列
        int summax = 0;
        for (int i = 1 ;i<nums.length;i++){
            if (max<nums[i]){
                max=nums[i];
                sum++;
            }else{
                max=nums[i];
                if(sum>summax){
                    summax = sum;
                }
                sum = 1;
            }
        }
        if(sum>summax){
            summax = sum;
        }
        return summax;
    }

}
