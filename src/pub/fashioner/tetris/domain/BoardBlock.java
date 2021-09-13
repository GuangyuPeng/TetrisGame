package pub.fashioner.tetris.domain;

import pub.fashioner.tetris.domain.block.AbstractBlock;
import pub.fashioner.tetris.domain.block.BlockFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Random;

/**
 * <p>BoardBlock 表示主面板上的当前被操作块和预告块</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/10 15:40
 */
public class BoardBlock {

    private final int previewNum;           //预告块个数
    private int leftX;                      //当前块左上角相对于Board左上角的水平距离
    private int leftY;                      //当前块左上角相对于Board左上角的垂直距离
    private AbstractBlock curBlock;         //当前操作块
    private Deque<AbstractBlock>  nexBlocks;//预告块队列
    private final Board board;              //与BoardBlock关联的board对象

    /**
     * <p>构造BoardBlock对象，表示主面板上的当前被操作块和预告块，
     * 通过该对象，可以对当前方块进行旋转移动等操作</p>
     * @param preNum 需要预告的方块个数
     * @param board 与BoardBlock关联的board对象引用
     * @author pgy
     * @since 1.0
     * @Date 22:13 2021/9/12
     */
    public BoardBlock(int preNum, Board board) {
        this.previewNum = preNum;
        this.board = board;

        // 实例化nexBlocks
        int types = BlockFactory.getTypes();
        nexBlocks = new ArrayDeque<>(previewNum);
        while(nexBlocks.size() < previewNum) {
            int id = new Random().nextInt(types)+1;
            nexBlocks.addLast(BlockFactory.createBlock(id);
        }
        loadBlocks();
    }

    /**
     * <p>生成新的被操作方块和预告方块</p>
     * @author pgy
     * @since 1.0
     * @Date 22:28 2021/9/12
     */
    public void loadBlocks() {
        // 更新当前方块 curBlock
        curBlock = nexBlocks.removeFirst();

        // 预告块队列添加新方块
        int types = BlockFactory.getTypes();
        int id = new Random().nextInt(types)+1;
        nexBlocks.addLast(BlockFactory.createBlock(id);

        // 确定初始的 leftX 和 leftY
        int boardWidth = board.getBoard()[0].length;
        int[][] area = curBlock.getArea();
        int areaHeight = area.length;
        int areaWidth = area[0].length;
        leftX = boardWidth/2 - areaWidth/2;
        leftY = -areaHeight;
    }

    /**
     * <p>对当前方块逆时针旋转一次，若旋转后的方块与游戏面板Board
     * 不冲突，则旋转生效，返回true，否则返回false</p>
     * @return boolean 若旋转后的方块与游戏面板Board
     * 不冲突，则旋转生效，返回true，否则返回false
     * @author pgy
     * @since 1.0
     * @Date 10:14 2021/9/13
     */
    public boolean rotateBlock() {
        int[][] newBlock = curBlock.rotate();
    }

    public boolean leftBlock() {

    }

    public boolean rightBlock() {

    }

    public boolean downBlock() {

    }

    /**
     * <p>获取当前方块左上角相对于Board左上角的水平距离</p>
     * @return int
     * @author pgy
     * @since 1.0
     * @Date 9:44 2021/9/13
     */
    public int getLeftX() {
        return leftX;
    }

    /**
     * <p>获取当前块左上角相对于Board左上角的垂直距离</p>
     * @return int 可能为负数，表示还未下落至Board中
     * @author pgy
     * @since 1.0
     * @Date 9:45 2021/9/13
     */
    public int getLeftY() {
        return leftY;
    }

    /**
     * <p>返回当前方块对应的二维数组的对象引用，
     * 使用时不要改变数组的内部取值</p>
     * @return int[][] 0表示空，1表示占用
     * @author pgy
     * @since 1.0
     * @Date 9:48 2021/9/13
     */
    public int[][] getCurBlock() {
        return curBlock.getArea();
    }

    /**
     * <p>返回一个二维数组的队列，表示预告方块，
     * 使用时不要改变每个二维数组元素的内部取值</p>
     * @return java.util.Deque<int[][]>
     * @author pgy
     * @since 1.0
     * @Date 9:50 2021/9/13
     */
    public Deque<int[][]> getNexBlocks() {
        // 创建一个新的队列
        Deque<int[][]> deque = new ArrayDeque<>(previewNum);
        // 填充队列元素
        Iterator<AbstractBlock> iter = nexBlocks.iterator();
        iter.forEachRemaining((AbstractBlock block)->{
            deque.add(block.getArea());
        });
        //返回队列
        return deque;
    }
}
