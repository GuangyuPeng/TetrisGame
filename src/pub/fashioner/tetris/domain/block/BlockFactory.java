package pub.fashioner.tetris.domain.block;

/**
 * <p>BlockFactory 工厂类，根据id值生成对应的Block</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/9 23:06
 */
public class BlockFactory {

    private static final int types = 7;

    /**
     * <p>返回方块类型数</p>
     * @return int 方块类型数
     * @author pgy
     * @since 1.0
     * @Date 23:08 2021/9/9
     */
    public static int getTypes() {
        return types;
    }

    /**
     * <p>静态工厂方法，根据id值构造对应的block</p>
     * @param id 正整数，标识一种block
     * @return pub.fashioner.tetris.domain.block.AbstractBlock
     * @throws IllegalArgumentException when id not in [1, <i>types</i>],
     * <i>types</i> is the number of block types, and can be gotten by getTypes() method
     * @author pgy
     * @since 1.0
     * @Date 23:17 2021/9/9
     */
    public static AbstractBlock createBlock(int id) {
        if(id <= 0 || id > types)
            throw new IllegalArgumentException("id must in range [1, "+types+"]");
        switch (id) {
            case 1:
                return new BlockI();
            case 2:
                return new BlockJ();
            case 3:
                return new BlockL();
            case 4:
                return new BlockO();
            case 5:
                return new BlockS();
            case 6:
                return new BlockT();
            case 7:
                return new BlockZ();
            default:
                return null;
        }
    }
}
