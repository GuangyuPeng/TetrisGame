package pub.fashioner.tetris.view;

import java.util.Arrays;

/**
 * <p>SimpleTetrisView 控制台版本的游戏界面，用于测试</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/15 20:59
 */
public class SimpleTetrisView {

    public void printTetrisView(int[][] board,
                                int round,
                                int score,
                                int totErased,
                                int[][] nexBlock) {
        int nexBlockRow = nexBlock.length;
        System.out.println("------------------------");
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 0){
                    System.out.print('.');
                }
                else if(board[i][j] == -1) {
                    System.out.print(' ');
                }
                else {
                    System.out.print('#');
                }
            }
            if(i == 1)
                System.out.print("      round: " + round);
            else if(i == 2)
                System.out.print("      score: " + score);
            else if(i == 3)
                System.out.print("      totErased: " + totErased);

            else if(i >= 10 && i < 10+nexBlockRow) {
                System.out.print("      ");
                for(int j = 0; j < nexBlock[i-10].length; j++){
                    if(nexBlock[i-10][j] == 0) {
                        System.out.print('.');
                    }
                    else {
                        System.out.print('#');
                    }
                }
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }
}
