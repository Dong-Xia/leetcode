package xia.ncut;

import xia.ncut.entity.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal103 {
    /**
     * 解题思路：从0层计数，进行广度优先搜索，将每层放入ArrayDeque双向队列中（既可以实现队列的功能，也能实现栈的功能），偶数层从左往右遍历，奇数层从右向左遍历，
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 使用ArrayDeque双向队列临时存广度优先搜索出来的每层树节点
        ArrayDeque<TreeNode> treeNodesQuery = new ArrayDeque<>();
        //记录是奇数层还是偶数层
        int count = 0;
        treeNodesQuery.add(root);
        while (!treeNodesQuery.isEmpty()) {
            int size = treeNodesQuery.size();
            ArrayDeque<Integer> stack=new ArrayDeque<>(); //临时存储每一层的值
            while (size > 0) {
                TreeNode node = treeNodesQuery.removeFirst();
                stack.add(node.val);
                if (node.left != null) {
                    treeNodesQuery.add(node.left);
                }
                if (node.right != null) {
                    treeNodesQuery.add(node.right);
                }
                size--;
            }
                if (count % 2 == 0) {
                    //偶数层，从左往右遍历
                    List<Integer> tmp=new ArrayList<>(stack);
                    result.add(tmp);
                } else {
                    List<Integer> tmp=reverseStack(stack);
                    result.add(tmp);
                }
            count++;
            }
        return result;
        }

    /**
     * 反转stack
     * @param stack
     * @return
     */
    public  List<Integer> reverseStack(ArrayDeque<Integer> stack){
        List<Integer> res=new ArrayList<>();
        Object[] a=  stack.toArray();
        for(int i=a.length-1;i>=0;i--){
            res.add((int)a[i]);
        }
        return res;
    }
}
