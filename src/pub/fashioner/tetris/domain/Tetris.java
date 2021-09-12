package pub.fashioner.tetris.domain;

/**
 * <p>Tetris 表示一局游戏的相关信息</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/10 15:40
 */
public class Tetris {
    private int round;      //轮数，代表第几个方块
    private int score;      //分数
    private int rndErased;  //该轮消掉的行数
    private int totErased;  //总共消掉的行数

    /**
     * <p>返回当前的轮数</p>
     * @return int 当前轮数
     * @author pgy
     * @since 1.0
     * @Date 20:23 2021/9/12
     */
    public int getRound() {return round;}

    /**
     * <p>进入下一轮，更新总共消掉的行数，
     * 归零该轮消掉的行数</p>
     * @author pgy
     * @since 1.0
     * @Date 20:23 2021/9/12
     */
    public void nextRound() {
        ++round;
        totErased += rndErased;
        rndErased = 0;
    }

    /**
     * <p>返回该轮消掉的行数</p>
     * @return int 该轮消掉的行数
     * @author pgy
     * @since 1.0
     * @Date 20:24 2021/9/12
     */
    public int getRndErased() {return rndErased;}

    /**
     * <p>设置该轮消掉的行数</p>
     * @param re 将被设置的行数值
     * @author pgy
     * @since 1.0
     * @Date 20:25 2021/9/12
     */
    public void setRndErased(int re) {rndErased = re;}

    /**
     * <p>返回整局游戏中总共消掉的行数</p>
     * @return int 总共消掉的行数
     * @author pgy
     * @since 1.0
     * @Date 20:26 2021/9/12
     */
    public int getTotErased() {return totErased;}

    /**
     * <p>返回当前的分数</p>
     * @return int 分数值
     * @author pgy
     * @since 1.0
     * @Date 20:27 2021/9/12
     */
    public int getScore() {return score;}

    /**
     * <p>根据给定的加分策略，进行加分，返回加分后的分数</p>
     * @param ss 加分策略接口
     * @return int 加分后的分数
     * @author pgy
     * @since 1.0
     * @Date 20:28 2021/9/12
     */
    public int addScore(ScoreStrategy ss) {
        int add =  ss.calRndScore(this);
        score += add;
        return score;
    }
}
