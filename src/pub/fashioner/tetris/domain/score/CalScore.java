package pub.fashioner.tetris.domain.score;

import pub.fashioner.tetris.domain.ScoreStrategy;
import pub.fashioner.tetris.domain.Tetris;

/**
 * <p>CalScore 实现了分数策略接口</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/12 20:31
 */
public class CalScore implements ScoreStrategy {

    @Override
    public int calRndScore(Tetris info) {
        int rndErased = info.getRndErased();
        return 10 + 10*rndErased;
    }
}
