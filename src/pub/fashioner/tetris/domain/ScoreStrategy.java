package pub.fashioner.tetris.domain;

/**
 * <p>计算分数的接口</p>
 * @author pgy
 * @version 1.0
 * @Date 20:19 2021/9/12
 */
public interface ScoreStrategy {
    int calRndScore(Tetris info);
}
