package pub.fashioner.tetris.domain;

import org.junit.jupiter.api.Test;
import pub.fashioner.tetris.domain.exception.GameOverException;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private final Board mainBoard = new Board(6, 6, 2);
    private final BoardBlock boardBlock = mainBoard.getBoardBlock();

    void printBlock(int[][] block) {
        for(int i = 0; i < block.length; i++) {
            for(int j = 0; j < block[0].length; j++){
                System.out.print(block[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    void getBoardShot() {
        for(int i = 0; i < 5; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("down: "+boardBlock.downBlock());
        }
        for(int i = 0; i < 3; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("left: "+boardBlock.leftBlock());
        }
        for(int i = 0; i < 3; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("rotate: "+boardBlock.rotateBlock());
        }
        for(int i = 0; i < 5; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("right: "+boardBlock.rightBlock());
        }
        for(int i = 0; i < 5; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("down: "+boardBlock.downBlock());
        }
    }

    void move() {
        for(int i = 0; i < 5; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("down: "+boardBlock.downBlock());
        }
        for(int i = 0; i < 3; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("left: "+boardBlock.leftBlock());
        }
        for(int i = 0; i < 3; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("rotate: "+boardBlock.rotateBlock());
        }
        for(int i = 0; i < 5; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("right: "+boardBlock.rightBlock());
        }
        for(int i = 0; i < 5; i++) {
            printBlock(mainBoard.getBoardShot());
            System.out.println("down: "+boardBlock.downBlock());
        }
    }

    @Test
    void mergeBlock() {
        try {
            for(int i = 0; i < 5; i++) {
                move();
                mainBoard.mergeBlock();
                System.out.println("Merge!");
                boardBlock.loadBlocks();
            }
        } catch (GameOverException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void eraseRows() {
        int[][] board = mainBoard.getBoard();
//        000000
//        110001
//        111111
//        110100
//        111111
//        101101
        board[1][0] = board[1][1] = board[1][5] = 1;
        for(int i = 0; i < mainBoard.getWidth(); i++) board[2][i] = 1;
        board[3][0] = board[3][1] = board[3][3] = 1;
        for(int i = 0; i < mainBoard.getWidth(); i++) board[4][i] = 1;
        board[5][0] = board[5][2] = board[5][3] = board[5][5] = 1;

        //mainBoard.setCurHeight(5);

        printBlock(mainBoard.getBoard());
        System.out.println("erased: "+mainBoard.eraseRows());
        printBlock(mainBoard.getBoard());
        while(mainBoard.downRows()) {
            printBlock(mainBoard.getBoard());
        }
    }
}