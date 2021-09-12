package pub.fashioner.tetris.domain.block;

/**
 * AbstractBlock抽象类，表示所有可操作方块的抽象
 * @author pgy
 * @Date 2021/9/9 17:02
 * @version 1.0
 */
public abstract class AbstractBlock {
    private int x;
    private int y;
    private int id;
    private int[][] area;

    /**
     * <p>AbstractBlock抽象类的构造函数，根据x和y值创建一块
     * int[x][y]的二维区域，区域中0表示该方格空白，1表示
     * 方格占用，id值唯一标识一种方块</p>
     * @Date 17:09 2021/9/9
     * @param x 该方块对应的二维区域int[][]的第一维数值
     * @param y 该方块对应的二维区域int[][]的第二维数值
     * @param id 该种方块的唯一标识符
     * @author pgy
     * @since 1.0
     */
    public AbstractBlock(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    /**
     * <p>抽象方法，逆时针旋转方块一次，返回旋转后的方块对应的二维数组，
     * 该方法不改变对象内的二维区域的状态</p>
     * @Date 17:16 2021/9/9
     * @return int[][] 逆时针旋转后的方块对应的二维数组，0表示该方格空白，1表示
     *         方格占用
     * @author pgy
     * @since 1.0
     */
    public abstract int[][] rotate();

    /**
     * <p>返回该方块对应的二维区域int[][]的第一维数值</p>
     * @Date 17:18 2021/9/9
     * @return int 该方块对应的二维区域int[][]的第一维数值
     * @author pgy
     * @since 1.0
     */
    public int getX() {
        return x;
    }

    /**
     * <p>返回该方块对应的二维区域int[][]的第二维数值</p>
     * @Date 17:20 2021/9/9
     * @return int 该方块对应的二维区域int[][]的第二维数值
     * @author pgy
     * @since 1.0
     */
    public int getY() {
        return y;
    }

    /**
     * <p>返回该种方块的唯一标识符</p>
     * @Date 17:21 2021/9/9
     * @return int 该种方块的唯一标识符
     * @author pgy
     * @since 1.0
     */
    public int getId() {
        return id;
    }

    /**
     * <p>返回该方块对应的二维区域int[][]</p>
     * @Date 17:22 2021/9/9
     * @return int[][] 该方块对应的二维区域
     * @author pgy
     * @since 1.0
     */
    public int[][] getArea() {
        return area;
    }

    /**
     * <p>设置返回该方块对应的二维区域int[][]</p>
     * @Date 17:23 2021/9/9
     * @param area int[][]引用，表示一个二维区域，0表示该方格空白，1表示
     *        方格占用，<strong>area参数必须由rotate()方法返回</strong>
     * @throws IllegalArgumentException area参数不是rotate()方法返回得到的
     * @author pgy
     * @since 1.0
     */
    public void setArea(int[][] area) {
        this.area = area;
    }
}
