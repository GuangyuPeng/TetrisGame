package pub.fashioner.tetris.domain.block;

import java.util.Random;

/**
 * <p>BlockJ：J型方块</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/9 20:41
 */
public class BlockJ extends AbstractBlock {

    private final int[][][] shape = {
            {
                    {0,1,0},
                    {0,1,0},
                    {1,1,0}
            },
            {
                    {1,1,1},
                    {0,0,1},
                    {0,0,0}
            },
            {
                    {1,1,0},
                    {1,0,0},
                    {1,0,0}
            },
            {
                    {1,0,0},
                    {1,1,1},
                    {0,0,0}
            }
    };

    private final int[] trans = {1,2,3,0};

    private int state = new Random().nextInt(4);
    /**
     * <p>构造一个J型方块，创建一块
     * int[3][3]的二维区域，区域中0表示该方格空白，1表示
     * 方格占用，标识id = 2</p>
     *
     * @Date 17:09 2021/9/9
     * @author pgy
     * @since 1.0
     */
    public BlockJ() {
        super(3, 3, 2);
        super.setArea(shape[state]);
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
        return shape[trans[state]];
    }

    @Override
    public void setArea(int[][] area) {
        for(int i = 0; i < shape.length; i++) {
            if(area == shape[i]) {
                super.setArea(area);
                state = i;
                return;
            }
        }
        throw new IllegalArgumentException("area参数必须由rotate()方法返回");
    }
}
