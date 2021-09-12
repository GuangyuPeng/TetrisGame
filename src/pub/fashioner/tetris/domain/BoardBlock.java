package pub.fashioner.tetris.domain;

import pub.fashioner.tetris.domain.block.AbstractBlock;
import pub.fashioner.tetris.domain.block.BlockFactory;

import java.util.ArrayDeque;
import java.util.Deque;
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

    }

    public boolean rotateBlock() {

    }

    public boolean leftBlock() {

    }

    public boolean rightBlock() {

    }

    public boolean downBlock() {

    }

    public int getLeftX() {

    }

    public int getLeftY() {

    }

    public int[][] getCurBlock() {

    }

    public Deque<int[][]> getNexBlocks() {

    }
}
