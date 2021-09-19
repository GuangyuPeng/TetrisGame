package pub.fashioner.tetris.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pub.fashioner.tetris.controller.exception.RoundOverException;
import pub.fashioner.tetris.domain.Board;
import pub.fashioner.tetris.domain.BoardBlock;
import pub.fashioner.tetris.domain.Tetris;
import pub.fashioner.tetris.domain.exception.GameOverException;
import pub.fashioner.tetris.domain.score.CalScore;
import pub.fashioner.tetris.view.TetrisViewController;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Condition;

/**
 * <p>TetrisController 俄罗斯方块游戏的控制器类，
 * 连接模型层domain和视图层view</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/18 21:54
 */
public class TetrisController {

    // domain层三个核心类
    private Board mainBoard = new Board(10, 20, 1);
    private Tetris gameInfo = mainBoard.getTetris();
    private BoardBlock boardBlock = mainBoard.getBoardBlock();

    // 视图层控制器
    private final TetrisViewController viewController;

    private final Stage primaryStage;

    // 定时器，定时下降方块
    private Timer goDownTimer;

    public TetrisController(TetrisViewController viewController, Stage primaryStage) {
        this.viewController = viewController;
        this.viewController.initBlockPane(20, 10);
        this.primaryStage = primaryStage;
        start();
    }

    public void start() {
        gameInfo.nextRound();
        boardBlock.loadBlocks();

        activateTimer();
        activateKeyEvent();
    }

    private EventHandler<KeyEvent> keyEventEventHandler = keyEvent -> {
        if(keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.UP) {
            rotate();
        }
        else if(keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.LEFT) {
            goLeft();
        }
        else if(keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.RIGHT) {
            goRight();
        }
        else if(keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.DOWN) {
            goDown();
        }
        else if(keyEvent.getCode() == KeyCode.SPACE) {
            goStraightDown();
        }
    };

    private void activateKeyEvent() {
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventEventHandler);
    }

    private void inactivateKeyEvent() {
        primaryStage.removeEventHandler(KeyEvent.KEY_PRESSED, keyEventEventHandler);
    }

    /**
     * <p>调用视图层，在UI界面显示游戏面板实时画面</p>
     * @param board 二维数组，表示游戏面板快照
     * @author pgy
     * @since 1.0
     * @Date 2021/9/19 9:46
     */
    public void show(int[][] board) {
        viewController.setData(gameInfo.getRound(),
                gameInfo.getScore(),
                gameInfo.getTotErased());
        viewController.showBlock(board);
        viewController.showNexBlock(boardBlock.getNexBlocks().getFirst());
    }

    private void inactivateTimer() {
        if(goDownTimer != null)
            goDownTimer.cancel();
    }

    private void activateTimer() {
        goDownTimer = new Timer(true);
        goDownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->goDown());
            }
        }, 0, 800);
    }

    private void gameOverHandler() {
        System.out.println("Game Over!!!");
    }

    /**
     * <p>当方块无法继续往下后，说明一轮已经结束，
     * 此时判断并消除可被消除的行，填补消除后的空行，进入下一轮</p>
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 20:59
     */
    private void roundOverHandler() {
        // 令goDownTimer失效
        inactivateTimer();
        // 令keyEvent失效
        inactivateKeyEvent();
        // 将方块合并至主面板中
        try {
            mainBoard.mergeBlock();
        } catch (GameOverException e) {
            gameOverHandler();
            return;
        }
        // 消除行
        int erasedRows = mainBoard.eraseRows();
        // 设置消除行数并加分
        gameInfo.setRndErased(erasedRows);
        gameInfo.addScore(new CalScore());
        show(mainBoard.getBoard());
        // 填补空行
        Timer fillEmptyRowsTimer = new Timer(true);
        fillEmptyRowsTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    if(mainBoard.downRows())
                        show(mainBoard.getBoard());
                    else
                        fillEmptyRowsTimer.cancel();
                });
            }
        }, 500, 500);
        // 进入下一轮
        gameInfo.nextRound();
        boardBlock.loadBlocks();
        // 令goDownTimer生效
        activateTimer();
        // 令keyEvent生效
        activateKeyEvent();
    }

    /**
     * <p>旋转当前块</p>
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:05
     */
    public synchronized void rotate() {
        boardBlock.rotateBlock();
        show(mainBoard.getBoardShot());
    }

    /**
     * <p>当前块下降一格</p>
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:05
     */
    public synchronized void goDown() {
        if(!boardBlock.downBlock())
            roundOverHandler();
        else show(mainBoard.getBoardShot());
    }

    /**
     * <p>当前块左移一格</p>
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:06
     */
    public synchronized void goLeft() {
        boardBlock.leftBlock();
        show(mainBoard.getBoardShot());
    }

    /**
     * <p>当前块右移一格</p>
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:06
     */
    public synchronized void goRight() {
        boardBlock.rightBlock();
        show(mainBoard.getBoardShot());
    }

    /**
     * <p>令当前块直接下移到最下面，结束当前轮</p>、
     * @author pgy
     * @since 1.0
     * @Date 2021/9/16 21:07
     */
    public synchronized void goStraightDown() {
        while(boardBlock.downBlock());
        show(mainBoard.getBoardShot());
        roundOverHandler();
    }
}
