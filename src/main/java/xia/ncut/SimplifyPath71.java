package xia.ncut;

import java.util.Stack;

/**
 * 71题--简化路径：
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * 注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 */
public class SimplifyPath71 {
    /**
     * 解题思路：采用栈进行处理
     * 这道题让简化给定的路径，光是根据题目中给的那一个例子还真不太好总结出规律，
     * 应该再加上两个例子 path = "/a/./b/../c/", => "/a/c" (采用从左往右进行读取路径，"./"表示相对路径，"../"表示上一层目录)
     * 和path = "/a/./b/c/", => "/a/b/c"，
     * 这样我们就可以知道中间是"."的情况直接去掉，是".."时删掉它上面挨着的一个路径，
     * 而下面的边界条件给的一些情况中可以得知，如果是空的话返回"/"，如果有多个"/"只保留一个。
     * 那么我们可以把路径看做是由一个或多个"/"分割开的众多子字符串，把它们分别提取出来一一处理即可。
     *
     */
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return path;
        }
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<String>();
        for (String s : strs) {
            if (s.equals("") || s.equals(".")) {
                continue;
            } else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    continue;
                }
            } else {
                stack.push(s);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder ret = new StringBuilder();
        while (!stack.isEmpty()) {
            // insert()方法：将指定的值插入到指定的下标位置，下面的位置指定为0，则代表插入到字符串队首位置
            StringBuffer popStr = new StringBuffer();
            popStr.append("/").append(stack.pop());
            ret.insert(0, popStr.toString());
        }
        return ret.toString();
    }
}
