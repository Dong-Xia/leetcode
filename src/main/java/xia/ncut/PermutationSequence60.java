package xia.ncut;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class PermutationSequence60 {

    /**
     * 康托展开公式
     *
     * 解题思路：
     *
     * 这道题是让求出n个数字的第k个排列组合，由于其特殊性，我们不用将所有的排列组合的情况都求出来，然后返回其第k个，我们可以只求出第k个排列组合即可，那么难点就在于如何知道数字的排列顺序，可参见https://bangbingsyb.blogspot.com/2014/11/leetcode-permutation-sequence.html
     *
     * 首先我们要知道当n = 3时，其排列组合共有3! = 6种，当n = 4时，其排列组合共有4! = 24种，我们就以n = 4, k = 17的情况来分析，所有排列组合情况如下：
     *
     * 1234
     * 1243
     * 1324
     * 1342
     * 1423
     * 1432
     * 2134
     * 2143
     * 2314
     * 2341
     * 2413
     * 2431
     * 3124
     * 3142
     * 3214
     * 3241
     * 3412	<--- k = 17           计算出第一个位置后，剩下124数字，全排列后3！=6个（124 142 214 241 412 421），根据康托展开运算：17对应6个数字全排列位置为17 % (3!) = 5
     * 3421
     * 4123
     * 4132
     * 4213
     * 4231
     * 4312
     * 4321
     *
     * 我们可以发现，每一位上1,2,3,4分别都出现了6次，当第一位上的数字确定了，后面三位上每个数字都出现了2次，当第二位也确定了，后面的数字都只出现了1次，当第三位确定了，那么第四位上的数字也只能出现一次，那么下面我们来看k = 17这种情况的每位数字如何确定，由于k = 17是转化为数组下标为16：
     *
     * 最高位可取1,2,3,4中的一个，每个数字出现3！= 6次，所以k = 16的第一位数字的下标为16 / 6 = 2，即3被取出
     * 第二位此时从1,2,4中取一个，k = 16时，k' = 16 % (3!) = 4（得到3个数字全排列后和16位置相对应的全排列的位置），而剩下的每个数字出现2！= 2次，所以第二数字的下标为4 / 2 = 2，即4被取出
     * 第三位此时从1,2中去一个，k' = 4时，k'' = 4 % (2!) = 0，而剩下的每个数字出现1！= 1次，所以第三个数字的下标为 0 / 1 = 0，即1被取出
     * 第四位是从2中取一个，k'' = 0时，k''' = 0 % (1!) = 0，而剩下的每个数字出现0！= 1次，所以第四个数字的下标为0 / 1= 0，即2被取出
     *
     * 那么我们就可以找出规律了
     * a1 = k / (n - 1)!
     * k1 = k
     *
     * a2 = k1 / (n - 2)!
     * k2 = k1 % (n - 2)!
     * ...
     *
     * an-1 = kn-2 / 1!
     * kn-1 = kn-2 / 1!
     *
     * an = kn-1 / 0!
     * kn = kn-1 % 0!
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        if(n==1){
            return "1";
        }
        StringBuilder sb=new StringBuilder();
        List<Integer> list = new ArrayList<Integer>();
        int[] factor=new int[n];//阶乘
        factor[1]=1;
        for(int i=2;i<n;i++){
            factor[i]=factor[i-1]*i;
        }
        for(int i=1;i<=n;i++){
            list.add(i);
        }
        int target=k-1;
        int rem,mer;//余数，商
        for(int i=n-1;i>0;i--){//康托展开运算
            rem=target%factor[i];
            mer=target/factor[i];
            sb.append(list.get(mer));
            list.remove(mer);
            target=rem;
        }
        //处理最后一个
        sb.append(list.get(0));
        return sb.toString();
    }
}