package xia.ncut;

/**
 * <b>System：</b>ncc<br/>
 * <b>Title：</b>Rob198<br/>
 * <b>Description：</b>动态规划-打家劫舍2<br/>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * <b>@author： </b>xiadong<br/>
 * <b>@date：</b>2019/8/20 14:49<br/>
 */
public class Rob199 {
    /**
     * （自底向上的动态规划）降低时间复杂度的动态规划实现
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }

        if (nums.length <=0) {
            return 0;
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp1 = new int[nums.length-1];
        int[] dp2 = new int[nums.length];

        // 允许偷第一家，不允许偷最后一家(直接用一个数组存去掉最后一家的数据)
        if(dp1.length < 3){
            dp1[0] = nums[0];
            dp1[1] = Math.max(nums[0], nums[1]);
        }else{
            for (int i = 2;i<dp1.length;i++) {
                    dp1[0] = nums[0];
                    dp1[1] = Math.max(nums[0], nums[1]);
                    dp1[i] = Math.max(dp1[i - 2] + nums[i], dp1[i - 1]);
                }
        }
        // 不允许偷第一家，允许偷最后一家
        for (int i = 2;i<dp2.length;i++) {
            // 边界：因为不能偷第一家，所以第一家金钱只能为0，屋子为两家时，获取的金钱总数为第二家的金钱数
            dp2[0] = 0;
            dp2[1] = nums[1];
            dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);
        }
        return Math.max(dp1[nums.length - 2],dp2[nums.length - 1]);
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int[] aa = {1,2,3,1};
        System.out.println(new Rob199().rob1(aa));
    }
}
