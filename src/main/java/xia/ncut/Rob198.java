package xia.ncut;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>System：</b>ncc<br/>
 * <b>Title：</b>Rob198<br/>
 * <b>Description：</b>动态规划<br/>
 * <b>@author： </b>xiadong<br/>
 * <b>@date：</b>2019/8/20 14:49<br/>
 */
public class Rob198 {
    // 备忘录
    private Map<Integer, Integer> map = new HashMap<>();
    public int rob(int[] nums) {
        return jisuan(nums,nums.length);
    }

    private int jisuan(int[] nums, int length) {
        int sum = 0,sum1 = 0;
        if(length == 1){
            return nums[0];
        }

        if (length <=0) {
            return 0;
        }

        // 备忘录逻辑
        if (map.containsKey(length-1)) {
            sum = map.get(length - 1);
        }else{
            sum = jisuan(nums, length - 1);
            map.put(length-1,sum);
        }

        if (map.containsKey(length-2)) {
            sum1 = map.get(length - 2);
        }else{
            sum1 = jisuan(nums, length - 2);
            map.put(length-2,sum1);
        }

        // 动态规划
        if(sum > nums[length-1] + sum1){
            return sum;
        } else{
            return nums[length-1] + sum1;
        }
    }


    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int[] aa = {183,219,57,193,94,233,202,154,65,240,97,234,100,249,186,66,90,238,168,128,177,235,50,81,185,165,217,207,88,80,112,78,135,62,228,247,211};
        System.out.println(new Rob198().rob(aa));
        System.out.println(System.currentTimeMillis() - l);
    }
}
