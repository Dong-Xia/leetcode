package xia.ncut;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class TrappingRainWater43 {
    /**
     * 分析：只能遍历一遍，且不能存储所有的高度，一块直柱能接的水取决于左右两边较短的高度，
     * 所以一个比较直观的方法是从左到有遍历一遍记录该点左边的最大值，从右到左遍历一遍记录该点的右边的最大值，此方法为O(n) 时间, O(n) 空间，第二种方法可以利用单调递减栈来实现，此方法为O(n) 时间, O(n) 空间，
     * 第三种方法为先找出中间最大点，然后左边部分相当于已经确定了右边最大值，左指针不断向右逼近就行，右边部分相当于记录了左边最大值，右指针不断向左逼近就行，此方法为O(n) 时间, O(1) 空间，
     * 第四种方法类似第三种，左右夹逼，每次选择小的那个指针向中间靠拢，此方法为O(n) 时间, O(1) 空间，但是要比方法三少遍历一遍。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int ret = 0;
        if(height.length==0)
            return ret;
        int lmax = 0, rmax = 0;
        int l = 0, r = height.length-1;
        while(l<r)
        {
            lmax = Math.max(lmax,height[l]);
            rmax = Math.max(rmax,height[r]);
            if(lmax<rmax){
                ret += lmax-height[l];
                l++;
            }else{
                ret += rmax-height[r];
                r--;
            }
        }
        return ret;
    }

    /**
     * (推荐，速度和空间最优)解法二：观察下就可以发现被水填满后的形状是先升后降的塔形，因此，先遍历一遍找到塔顶，然后分别从两边开始，往塔顶所在位置遍历，水位只会增高不会减小，且一直和最近遇到的最大高度持平，这样知道了实时水位，就可以边遍历边计算面积
     */
    public int trapTwo(int[] height) {
        if(height.length <= 2){
            return 0;
        }

        int max = 0;
        // 最大值的下标
        int maxIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
                maxIndex = i;
            }
        }

        // 记录降水量
        int areaSum = 0;
        // 记录从左边到中间最大高度遍历时路程中最大的高度
        int leftHeightMax = height[0];
        for (int i = 1;i < maxIndex;i++){
            if (height[i] > leftHeightMax) {
                leftHeightMax = height[i];
            } else {
                areaSum += leftHeightMax - height[i];
            }
        }

        // 记录从右边到中间最大高度遍历时路程中最大的高度
        int rightHeightMax = height[height.length - 1];
        for (int i = height.length - 2;i>maxIndex; i--){
            if(height[i] > rightHeightMax){
                rightHeightMax = height[i];
            }else {
                areaSum += rightHeightMax - height[i];
            }
        }
        return areaSum;
    }
}
