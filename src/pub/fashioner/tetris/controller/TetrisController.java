package pub.fashioner.tetris.controller;

import pub.fashioner.tetris.domain.Board;
import pub.fashioner.tetris.domain.BoardBlock;
import pub.fashioner.tetris.domain.Tetris;

/**
 * <p>TetrisController 俄罗斯方块游戏的控制器，、
 * 负责游戏主逻辑，将domain层和view层联系起来</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/15 20:46
 */
public class TetrisController {

    private Board mainBoard = new Board(10, 20, 1);
    private Tetris gameInfo = mainBoard.getTetris();
    private BoardBlock boardBlock = mainBoard.getBoardBlock();

    public void rotate() {

    }

    public void goDown() {

    }

    public void goLeft() {

    }

    public void goRight() {

    }

    public void goStraightDown() {

    }

    public void mainLoop() {

    }
}
