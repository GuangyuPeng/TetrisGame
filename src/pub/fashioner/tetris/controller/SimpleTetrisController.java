package pub.fashioner.tetris.controller;

import pub.fashioner.tetris.controller.exception.RoundOverException;
import pub.fashioner.tetris.domain.Board;
import pub.fashioner.tetris.domain.BoardBlock;
import pub.fashioner.tetris.domain.Tetris;
import pub.fashioner.tetris.domain.exception.GameOverException;
import pub.fashioner.tetris.domain.score.CalScore;
import pub.fashioner.tetris.view.SimpleTetrisView;

import java.util.Scanner;

/**
 * <p>SimpleTetrisController 俄罗斯方块游戏的简单版控制器，
 * 与SimpleTetrisView配合，主要用于测试domain层逻辑</p>
 * @see pub.fashioner.tetris.view.SimpleTetrisView
 * @author pgy
 * @version 1.0
 * @Date 2021/9/15 20:46
 */
public class SimpleTetrisController {

    private Board mainBoard = new Board(10, 20, 1);
    private Tetris gameInfo = mainBoard.getTetris();
    private BoardBlock boardBlock = mainBoard.getBoardBlock();

    private SimpleTetrisView simpleTetrisView = new SimpleTetrisView();

    /**
     * <p>调用视图层，在控制台打印游戏主面板</p>
     * @param board 二维数组，主面板快照
     * @author 1.0
     * @since TODO
     * @Date 2021/9/16 20:58
     */
    private void show(int[][] board) {
        simpleTetrisView.printTetrisView(board,
                                         gameInfo.getRound(),
                                         gameInfo.getScore(),
                                         gameInfo.getTotErased(),
                                         boardBlock.getNexBlocks().getFirst());
    }

    /**
     * <p>当方块无法继续往下后，说明一轮已经结束，
     * 此时判断并消除可被消除的行，填补消除后的空行</p>
     * @return void
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 20:59
     */
    private void RoundOver() throws GameOverException {
        // 将方块合并至主面板中
        mainBoard.mergeBlock();
        // 消除行
        int erasedRows = mainBoard.eraseRows();
        // 设置消除行数并加分
        gameInfo.setRndErased(erasedRows);
        gameInfo.addScore(new CalScore());
        show(mainBoard.getBoard());
        // 填补空行
        while(mainBoard.downRows()) {
            show(mainBoard.getBoard());
        }
    }

    /**
     * <p>旋转当前块</p>
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:05
     */
    public void rotate() {
        boardBlock.rotateBlock();
        show(mainBoard.getBoardShot());
    }

    /**
     * <p>当前块下降一格</p>
     * @throws RoundOverException 无法再继续下降了
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:05
     */
    public void goDown() throws RoundOverException {
        if(!boardBlock.downBlock())
            throw new RoundOverException("can't move down any more");
        show(mainBoard.getBoardShot());
    }

    /**
     * <p>当前块左移一格</p>
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:06
     */
    public void goLeft() {
        boardBlock.leftBlock();
        show(mainBoard.getBoardShot());
    }

    /**
     * <p>当前块右移一格</p>
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:06
     */
    public void goRight() {
        boardBlock.rightBlock();
        show(mainBoard.getBoardShot());
    }

    /**
     * <p>令当前块直接下移到最下面，结束当前轮</p>、
     * @throws RoundOverException 无法再继续下降了
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:07
     */
    public void goStraightDown() throws RoundOverException {
        while(boardBlock.downBlock());
        show(mainBoard.getBoardShot());
        throw new RoundOverException("can't move down any more");
    }

    /**
     * <p>游戏主循环，不断接收控制台输入，执行相应动作</p>
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:08
     */
    public void mainLoop() {
        try(Scanner sc = new Scanner(System.in)) {
            while(true) {
                gameInfo.nextRound();
                boardBlock.loadBlocks();
                show(mainBoard.getBoardShot());
                try {
                    while(true) {
                        System.out.print("Input action(w a d s [space]): ");
                        String str = sc.nextLine();
                        if(str.length() == 0) continue;
                        char ch = str.charAt(0);
                        switch(ch) {
                            case 'w':
                                rotate();
                                break;
                            case 'a':
                                goLeft();
                                break;
                            case 'd':
                                goRight();
                                break;
                            case 's':
                                goDown();
                                break;
                            case ' ':
                                goStraightDown();
                                break;
                            default:
                        }
                    }
                }
                catch(RoundOverException roe) {
                    RoundOver();
                }
            }
        }
        catch (GameOverException e) {
            System.out.println("Game Over!!!");
        }
    }

    public static void main(String[] args) {
        SimpleTetrisController tetrisController = new SimpleTetrisController();
        tetrisController.mainLoop();
    }
}
