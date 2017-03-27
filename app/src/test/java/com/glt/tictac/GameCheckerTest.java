package com.glt.tictac;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameCheckerTest {


    @Test
    public void winningBoardsEvaluatedAsWins() throws Exception {

        GameChecker checker = new GameChecker();

        int[][] xhBoard = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
       };

        assertTrue(checker.isWonBy(1, xhBoard));

        int[][] xyBoard = {
                {1, 0, 0},
                {1, 0, 0},
                {1, 0, 0}
        };
        //checker.swapBaord(xyBoard);
        assertTrue(checker.isWonBy(1, xyBoard));

        int[][] yhBoard = {
                {0, 0, 0},
                {2, 2, 2},
                {0, 0, 0}
        };
        //checker.swapBaord(yhBoard);
        assertTrue(checker.isWonBy(2, yhBoard));

        int[][] yyBoard = {
                {2, 0, 0},
                {2, 0, 0},
                {2, 0, 0}
        };
        //checker.swapBaord(yyBoard);
        assertTrue(checker.isWonBy(2, yyBoard));


        int[][] xCrossBoard = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        //checker.swapBaord(xCrossBoard);
        assertTrue(checker.isWonBy(1, xCrossBoard));

        int[][] yCrossBoard = {
                {0, 0, 2},
                {0, 2, 0},
                {2, 0, 2}
        };

        //checker.swapBaord(yCrossBoard);
        assertTrue(checker.isWonBy(2, yCrossBoard));
    }
    @Test
    public void TieBoardsEvaluatedAsWins() throws Exception {

        int[][] xhBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int[][] xvBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int[][] yhBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int[][] yvBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int[][] xCrossBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int[][] yCrossBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
    }
    @Test
    public void unfinishedBoardsEvaluatedAsNotWins() throws Exception {

        int[][] xhBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int[][] xvBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int[][] yhBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int[][] yvBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int[][] xCrossBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int[][] yCrossBoard = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
    }
}