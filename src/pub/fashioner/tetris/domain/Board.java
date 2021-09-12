package pub.fashioner.tetris.domain;

/**
 * <p>Board 表示游戏主面板</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/10 15:40
 */
public class Board {

    private int width;          //宽度
    private int height;         //高度
    private int[][] board;      //二维数组，0表示空，非0表示占用
    private int[][] curHeight;  //当前面板被占用的高度
    private BoardBlock boardBlock; // 关联的BoardBlock对象
    private Tetris tetris;      //关联的Tetris对象

    public Board(int width, int height) {

    }

    /**
     * <p>返回游戏主面板的宽度</p>
     * @return int 宽度
     * @author pgy
     * @since 1.0
     * @Date 16:09 2021/9/10
     */
    public int getWidth() {
        return width;
    }

    /**
     * <p>返回游戏主面板的高度</p>
     * @return int 高度
     * @author pgy
     * @since 1.0
     * @Date 16:11 2021/9/10
     */
    public int getHeight() {
        return height;
    }

    /**
     * <p>返回游戏主面板对应的二维数组int[][]</p>
     * @return int[][] 0表示空，非0表示占用
     * @author pgy
     * @since 1.0
     * @Date 16:12 2021/9/10
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * <p>返回关联的BoardBlock对象</p>
     * @return pub.fashioner.tetris.domain.BoardBlock
     * @author pgy
     * @since 1.0
     * @Date 20:07 2021/9/12
     */
    public BoardBlock getBoardBlock() {
        return boardBlock;
    }

    /**
     * <p>返回关联的Tetris对象</p>
     * @return pub.fashioner.tetris.domain.Tetris
     * @author pgy
     * @since 1.0
     * @Date 20:08 2021/9/12
     */
    public Tetris getTetris() {
        return tetris;
    }

    public int[][] getBoardShot() {

    }

    public void mergeBlock() {

    }

    public int eraseRows() {

    }

    public boolean downRows() {

    }
}
