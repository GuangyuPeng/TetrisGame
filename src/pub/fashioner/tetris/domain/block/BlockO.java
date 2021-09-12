package pub.fashioner.tetris.domain.block;

/**
 * <p>O型方块</p>
 * @author pgy
 * @Date 2021/9/9 17:25
 * @version 1.0
 */
public class BlockO extends AbstractBlock {

    private final int[][][] shape = {
            {
                    {1,1},
                    {1,1}
            }
    };

    /**
     * <p>构造一个O型方块，创建一块
     * int[2][2]的二维区域，区域中0表示该方格空白，1表示
     * 方格占用，标识id = 4</p>
     *
     * @Date 17:09 2021/9/9
     * @author pgy
     * @since 1.0
     */
    public BlockO() {
        super(2, 2, 4);
        super.setArea(shape[0]);
    }

    /**
     * <p>逆时针旋转方块一次，返回旋转后的方块对应的二维数组，
     * 该方法不改变对象内的二维区域的状态</p>
     * @return int[][] 逆时针旋转后的方块对应的二维数组，0表示该方格空白，1表示
     * 方格占用
     * @author pgy
     * @since 1.0
     * @Date 17:55 2021/9/9
     */
    @Override
    public int[][] rotate() {
        return shape[0];
    }

    @Override
    public void setArea(int[][] area) {
        if(area != shape[0])
            throw new IllegalArgumentException("area参数必须由rotate()方法返回");
        super.setArea(area);
    }
}
