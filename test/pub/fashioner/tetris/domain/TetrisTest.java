package pub.fashioner.tetris.domain;

import org.junit.jupiter.api.Test;
import pub.fashioner.tetris.domain.score.CalScore;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TetrisTest {

    private Tetris tetris = new Tetris();

    void printInfo() {
        System.out.println("Round: "+tetris.getRound());
        System.out.println("Score: "+tetris.getScore());
        System.out.println("RndErased: "+tetris.getRndErased());
        System.out.println("TotErased: "+tetris.getTotErased());
    }

    @Test
    void testTetris() {
        int[] rows = {1, 0, 2, 3, 0, 0, 0, 2, 1, 1};
        for(int i = 0; i < 10; i++) {
            tetris.nextRound();
            printInfo();
            System.out.println("erased rows : "+rows[i]);
            tetris.setRndErased(rows[i]);
            tetris.addScore(new CalScore());
            printInfo();
            System.out.println();
        }
    }


}