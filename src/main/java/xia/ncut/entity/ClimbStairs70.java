package xia.ncut.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>System：</b>ncc<br/>
 * <b>Title：</b>ClimbStairs70<br/>
 * <b>Description：</b>爬楼梯<br/>
 * <b>@author： </b>xiadong<br/>
 * <b>@date：</b>2019/8/19 10:20<br/>
 */
public class ClimbStairs70 {
    /**
     * 采用动态规划，实现核心步骤：
     *
     */
    // 备忘录
    Map<Integer,Integer> map = new HashMap<>();
    Integer sum = 0;
    public int climbStairs(int n) {
        if(n==1){
            return 1;
        }

        if(n==2){
            return 2;
        }

        if(map.containsKey(n)){
            sum = map.get(n);
        }else {
            sum = climbStairs(n - 1) + climbStairs(n - 2);
            map.put(n,sum);
        }
        return sum;
    }
}
