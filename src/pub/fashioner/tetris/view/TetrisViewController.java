package pub.fashioner.tetris.view;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

/**
 * <p>TetrisViewController 连接GameBoard.fxml和TetrisView主程序，
 * 用于显示数据</p>
 *
 * @author pgy
 * @version 1.0
 * @Date 2021/9/18 11:23
 */
public class TetrisViewController {

    @FXML
    private Label round;
    @FXML
    private Label score;
    @FXML
    private Label rows;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private GridPane blockPane;
    @FXML
    private GridPane nexBlockPane;

    private IntegerProperty blockPaneRows;
    private IntegerProperty blockPaneColumns;

    private int[][] nexBlockCache;

    private Object[][] blockPaneNodes;
    private Object[][] nexBlockPaneNodes;

    public void setData(int round, int score, int rows) {
        this.round.setText(Integer.toString(round));
        this.score.setText(Integer.toString(score));
        this.rows.setText(Integer.toString(rows));
    }

    public void initSize() {
        // set size
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double height = screenRectangle.getHeight();
        rootPane.setPrefHeight(height*0.8);
        rootPane.setPrefWidth(height*0.8*0.8);
        nexBlockPane.setPrefHeight(200*height*0.8/1000);
        nexBlockPane.setPrefWidth(200*height*0.8/1000);
    }

    public void initBlockPane(int height, int width) {
        for(int i = 0; i < height; i++) {
            RowConstraints con = new RowConstraints();
            con.setPercentHeight((double)100/height);
            blockPane.getRowConstraints().add(con);
        }
        for(int i = 0; i < width; i++) {
            ColumnConstraints con = new ColumnConstraints();
            con.setPercentWidth((double)100/width);
            blockPane.getColumnConstraints().add(con);
        }

        blockPaneNodes = new Object[height][width];
        nexBlockPaneNodes = new Object[4][4];

        blockPaneRows = new SimpleIntegerProperty(height);
        blockPaneColumns = new SimpleIntegerProperty(width);
    }

    public void showBlock(int[][] board) {

        ReadOnlyDoubleProperty paneHeight =  blockPane.heightProperty();
        ReadOnlyDoubleProperty paneWidth = blockPane.widthProperty();
        DoubleBinding rectHeight = paneHeight.divide(blockPaneRows).add(-1);
        DoubleBinding rectWidth = paneWidth.divide(blockPaneColumns).add(-1);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(blockPaneNodes[i][j] != null) {
                    blockPane.getChildren().remove(blockPaneNodes[i][j]);
                    blockPaneNodes[i][j] = null;
                }

                if(board[i][j] > 0) {
                    Rectangle rect = new Rectangle();
                    rect.heightProperty().bind(rectHeight);
                    rect.widthProperty().bind(rectWidth);
                    rect.setFill(Color.LIGHTSKYBLUE);
                    rect.setLayoutX(rect.getLayoutX()+2);
                    rect.setLayoutY(rect.getLayoutY()+2);
                    blockPane.add(rect, j, i);
                    blockPaneNodes[i][j] = rect;
                }
            }
        }
    }

    public void showNexBlock(int[][] nexBlock) {
        if(nexBlock != nexBlockCache) {
            int row = 4;
            int col = 4;
            double height = nexBlockPane.getHeight();
            double width = nexBlockPane.getWidth();
            double rectHeight = height/row - 1;
            double rectWidth = width/col - 1;

            for(int i = 0; i < col; i++)
                for(int j = 0; j < row; j++)
                    if(nexBlockPaneNodes[i][j] != null) {
                        nexBlockPane.getChildren().remove(nexBlockPaneNodes[i][j]);
                        nexBlockPaneNodes[i][j] = null;
                    }


            for(int i = 0; i < nexBlock.length; i++) {
                for(int j = 0; j < nexBlock[0].length; j++) {
                    if(nexBlock[i][j] > 0) {
                        Rectangle rect = new Rectangle();
                        rect.setHeight(rectHeight);
                        rect.setWidth(rectWidth);
                        rect.setFill(Color.DARKGRAY);
                        rect.setX(rect.getX()+1);
                        rect.setY(rect.getY()+1);
                        nexBlockPane.add(rect, j, i);
                        nexBlockPaneNodes[i][j] = rect;
                    }
                }
            }

            nexBlockCache = nexBlock;
        }
    }
}
