package pub.fashioner.tetris.domain;

import pub.fashioner.tetris.domain.exception.GameOverException;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


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
     * 存放被消除的行的队列，{@code eraseRows()}方法向队列中增加元素，
     * {@code downRows()}方法向队列中移除元素
     */
    private final Deque<Integer> erasedRowQueue = new ArrayDeque<>();

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
     * @return int[][] 0表示空，>0表示占用， -1表示被消除
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
     * @return int[][] 0表示空，>0表示占用，-1表示被消除
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
                if(boardShot[leftY+i][leftX+j] != -1)
                    boardShot[leftY+i][leftX+j] = id;
        }
        return false;
    }

    private boolean hasBlock(int[] row) {
        for(int i : row) {
            if(i != 0) return true;
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
            if(leftY + i < 0)
                if(hasBlock(area[i])) throw new GameOverException("游戏结束");
                else continue;
            if (copyBlock(id, area, areaWidth, leftX, leftY, i, board)) break;
        }

        // 找到方块中的第一个有效行
        int i;
        for(i = 0; i < areaHeight; i++) {
            if(hasBlock(area[i])) break;
        }
        assert i < areaHeight;
        // 更新curHeight
        curHeight = Math.max(curHeight, height-leftY-i);
    }
    
    private boolean isFilled(int[] row) {
        for(int i : row) {
            if(i == 0) return false;
        }
        return true;
    }
    
    /**
     * <p>在游戏面板中执行俄罗斯方块的消除动作，返回消除行数,
     * 面板二维数组board中，被消除的行的值全部置为-1</p>
     * @return int 消除行数
     * @author pgy
     * @since 1.0
     * @Date 2021/9/15 16:16
     */
    public int eraseRows() {
        erasedRowQueue.clear();
        for(int i = height-curHeight; i < height; i++) {
            if(isFilled(board[i])) {
                Arrays.fill(board[i], -1);
                erasedRowQueue.addLast(i);
            }
        }
        return erasedRowQueue.size();
    }

    private void arrayAssign(int[] dest, int[] src) {
        int len = dest.length;
        for(int i = 0; i < len; i++)
            dest[i] = src[i];
    }

    /**
     * <p>执行完消除方法{@code eraseRows()}后，
     * 使上面的行下降一行填补消除行，无可填补行返回false，否则返回true</p>
     * @return boolean 无可填补行时返回false，否则返回true
     * @author pgy
     * @since 1.0
     * @Date 2021/9/15 17:31
     */
    public boolean downRows() {
        // 消除行队列为空，无法下移
        if(erasedRowQueue.isEmpty()) return false;
        // 整体下移
        int row = erasedRowQueue.removeFirst();
        int topRow = height - curHeight;
        for(int i = row-1; i >= topRow; i--) {
            arrayAssign(board[i+1], board[i]);
        }
        // 首行清空
        Arrays.fill(board[topRow], 0);
        // 高度-1
        --curHeight;
        return true;
    }

//    public void setCurHeight(int curHeight) {
//        this.curHeight = curHeight;
//    }
}
