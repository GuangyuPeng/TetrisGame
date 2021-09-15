package pub.fashioner.tetris.domain;

import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class BoardBlockTest {

    private final Board mainBoard = new Board(6, 6, 2);
    private final BoardBlock boardBlock = mainBoard.getBoardBlock();

    void printBlock(int[][] block) {
        for(int i = 0; i < block.length; i++) {
            for(int j = 0; j < block[0].length; j++){
                System.out.print(block[i][j]);
            }
            System.out.println();
        }
    }

    void printInfo() {
        System.out.println("site: ("+boardBlock.getLeftX()+","+boardBlock.getLeftY()+")");
        System.out.println("blockId: "+boardBlock.getCurBlockId());
        System.out.println("curBlock: ");
        int[][] curBlock = boardBlock.getCurBlock();
        printBlock(curBlock);
        System.out.println("nexBlocks: ");
        Deque<int[][]> nextblocks = boardBlock.getNexBlocks();
        nextblocks.forEach(this::printBlock);
        System.out.println();
    }

    @Test
    void loadBlocks() {
        for(int i = 0; i < 5; i++) {
            printInfo();
            boardBlock.loadBlocks();
        }
        printInfo();
    }

    @Test
    void rotateBlock() {
        printInfo();
        System.out.println("rotate: "+boardBlock.rotateBlock());
        printInfo();
    }

    @Test
    void leftBlock() {
        for(int i = 0; i < 5; i++) {
            printInfo();
            System.out.println("left: "+boardBlock.leftBlock());
        }
        printInfo();
    }

    @Test
    void rightBlock() {
        for(int i = 0; i < 5; i++) {
            printInfo();
            System.out.println("right: "+boardBlock.rightBlock());
        }
        printInfo();
    }

    @Test
    void downBlock() {
        for(int i = 0; i < 10; i++) {
            printInfo();
            System.out.println("down: "+boardBlock.downBlock());
        }
        printInfo();
    }

    @Test
    void moveBlock() {
        for(int i = 0; i < 5; i++) {
            printInfo();
            System.out.println("down: "+boardBlock.downBlock());
        }
        for(int i = 0; i < 3; i++) {
            printInfo();
            System.out.println("left: "+boardBlock.leftBlock());
        }
        for(int i = 0; i < 3; i++) {
            printInfo();
            System.out.println("rotate: "+boardBlock.rotateBlock());
        }
        for(int i = 0; i < 5; i++) {
            printInfo();
            System.out.println("right: "+boardBlock.rightBlock());
        }
        for(int i = 0; i < 5; i++) {
            printInfo();
            System.out.println("down: "+boardBlock.downBlock());
        }
    }
}