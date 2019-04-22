package xia.ncut;

import xia.ncut.entity.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class MergeIntervals56 {
    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     *
     * 思路:
     * 以【7，9】，【1，6】，【10，15】，【2，5】为例。
     *
     * 期望输出【1，6】【7，15】
     *
     * 首先按照区间的左端点进行排序，形成【1，6】【2，5】，【7，9】，【8，15】
     *
     * 取第一个区间【1，6】，然后判断该区间的右端点是否大于等于下一个区间的左端点，记为条件1，好，现在满足，再取【1，6】【2，5】右端点的最大值为6，则不用修改【1，6】的区间。再次判断【1，6】【7，9】，不满足条件1，则将【1，6】加入到结果区间中。使得当前区间变为【7，9】，判断【7，9】与【8，15】，满足条件1，取右端点最大值为15，将【7，9】改为【7，15】，由于遍历结束，将【7，15】加入到结果区间中。最后返回【1，6】【7，15】。
     * ---------------------
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty() || intervals.size() == 1) {
            return intervals;
        }
        // 先按照区间第一个数字排序
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start > o2.start) {
                    return 1;
                } else if (o1.start < o2.start) {
                    return -1;
                }

                return 0;
            }
        });
        List<Interval> list = new ArrayList<>();
        Interval temp = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            //此为条件1
            if (temp.end >= intervals.get(i).start) {
                //取右端点最大值
                temp.end = Math.max(temp.end, intervals.get(i).end);
            } else {
                //不满足条件1，则加入到结果区间中，将当前比较区间变更为遍历区间
                list.add(temp);
                temp = intervals.get(i);
            }
            //遍历到最后一个，判断完直接加入到结果区间中
            if (i == intervals.size() - 1) {
                list.add(temp);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Interval> ll = new ArrayList<>();
        ll.add(new Interval(1, 3));
        ll.add(new Interval(2, 6));
        ll.add(new Interval(15, 18));
        ll.add(new Interval(8, 10));
        System.out.println(new MergeIntervals56().merge(ll).toString());
    }
}
