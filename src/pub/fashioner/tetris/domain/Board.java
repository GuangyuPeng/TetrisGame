package pub.fashioner.tetris.domain;

import pub.fashioner.tetris.domain.exception.GameOverException;


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
    private int curHeight;  //当前面板被占用的高度
    private BoardBlock boardBlock; // 关联的BoardBlock对象
    private Tetris tetris;      //关联的Tetris对象

    private int[][] boardShot;   //当前面板快照，仅申请一次空间

    /**
     * <p>构造一个游戏主面板对象</p>
     * @param width 宽度，正整数
     * @param height 高度，正整数
     * @param preNum 需要提前预告的方块个数
     * @throws IllegalArgumentException 参数width和height必须为正整数
     * @author pgy
     * @since 1.0
     * @Date 2021/9/14 20:48
     */
    public Board(int width, int height, int preNum) {
        if(width <= 0 || height <= 0 || preNum <= 0)
            throw new IllegalArgumentException("all parameters must be positive");
        this.width = width;
        this.height = height;
        board = new int[height][width];
        curHeight = 0;
        boardBlock = new BoardBlock(preNum, this);
        tetris = new Tetris();

        boardShot = new int[height][width];
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

    /**
     * <p>获取面板快照，包括面板本身和当前方块</p>
     * @return int[][] 0表示空，非0表示占用
     * @author pgy
     * @since 1.0
     * @Date 2021/9/15 10:14
     */
    public int[][] getBoardShot() {
        // 拷贝面板
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++)
                boardShot[i][j] = board[i][j];
        }
        // 拷贝当前方块
        int id = boardBlock.getCurBlockId();
        int[][] area = boardBlock.getCurBlock();
        int areaHeight = area.length;
        int areaWidth = area[0].length;
        int leftX = boardBlock.getLeftX();
        int leftY = boardBlock.getLeftY();

        for(int i = 0; i < areaHeight; i++) {
            if(leftY + i < 0) continue;
            if (copyBlock(id, area, areaWidth, leftX, leftY, i, boardShot)) break;
        }

        return boardShot;
    }

    private boolean copyBlock(int id, int[][] area, int areaWidth, int leftX, int leftY, int i, int[][] boardShot) {
        if(leftY + i >= height) return true;
        for(int j = 0; j < areaWidth; j++) {
            if(leftX+j >= 0 && leftX+j < width && area[i][j] != 0)
                boardShot[leftY+i][leftX+j] = id;
        }
        return false;
    }

    /**
     * <p>把当前操作方块合并至主面板board对象中</p>
     * @throws GameOverException 游戏结束了
     * @author pgy
     * @since 1.0
     * @Date 2021/9/14 21:02
     */
    public void mergeBlock() throws GameOverException {
        int id = boardBlock.getCurBlockId();
        int[][] area = boardBlock.getCurBlock();
        int areaHeight = area.length;
        int areaWidth = area[0].length;
        int leftX = boardBlock.getLeftX();
        int leftY = boardBlock.getLeftY();

        for(int i = 0; i < areaHeight; i++) {
            if(leftY + i < 0) throw new GameOverException("游戏结束");
            if (copyBlock(id, area, areaWidth, leftX, leftY, i, board)) break;
        }

    }

    public int eraseRows() {
        return 0;
    }

    public boolean downRows() {
        return true;
    }
}
