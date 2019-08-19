package xia.ncut.entity;


/**
 * <b>Description：</b>回溯算法-八皇后问题<br/>
 *
 */
public class QueensOfEight {

    // 定义一个8*8的棋盘
    public static int[][] array = new int[8][8];
    // 记录总的解法
    public static int count = 0;

    /**
     * 得到皇后的解（回溯递归核心实现）
     * @param i 行号
     *
     * 该方法没有返回：因为执行结果有两种
     *          1、要么是得到解并输出后，也会回溯到上一层，进行广度优先寻找其它解
     *          2、要么就是执行完循环后也没有得到解，则方法结束回溯到上一层寻找其它解
     */
    public void getResultQueensOfEight(int i){
        // 满足条件的解
        if (i > 7) {
            count++;
            printResult();
            // 如果只需要获取一个解，不是所有可能的解，则可以在这里做处理
            // return;
        }else {
            // 该层循环的意义：为了获取全部的正确解，进行广度优先遍历：在棋盘中按每行横向移动，找出所有解
            for (int k = 0; k < 8; k++) {
                // 进行是否有资格进行下一层次递归的判断
                if (judgeLegal(i, k)) {
                    // 落点合规，设置该回溯点为1
                    array[i][k] = 1;
                    // 进行下一层递归，进行深度优先遍历
                    getResultQueensOfEight(i + 1);
                    // 回溯:寻找其它解，清除状态，设置回溯点状态为0
                    array[i][k] = 0;
                }
            }
        }
    }

    private void printResult() {
        System.out.println("解法"+ count + ":");
        for (int i = 0; i < 8; i++) {
            for (int j = 0;j < 8; j++) {
                if (array[i][j] == 1) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 进行深层次递归的判断：落点是否合规
     * @param i : 行号
     * @param j : 列号
     * @return
     */
    public boolean judgeLegal(int i, int j){
        // 判断j列是否有另一个皇后已经落点，只需判断j列0到i行的棋盘落点，相当于array[i][j]位置的竖向正上方的所有位置
        for (int k = 0; k < i; k++) {
            if (array[k][j] == 1) {
                return false;
            }
        }

        // 判断array[i][j]位置左上斜线是否有其它皇后落点
        for (int k = i - 1,p = j - 1;k >= 0 && p >= 0;k--,p--) {
            if (array[k][p] == 1) {
                return false;
            }
        }

        // 判断array[i][j]位置右上斜线是否有其它皇后落点
        for (int k = i -1,p = j + 1;k >= 0 && p < 8;k--,p++) {
            if (array[k][p] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new QueensOfEight().getResultQueensOfEight(0);
    }
}
