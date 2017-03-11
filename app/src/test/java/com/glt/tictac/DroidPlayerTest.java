package com.glt.tictac;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gltrager on 2/27/17.
 */

public class DroidPlayerTest {

    @Test
    public void DroidWinsInOneMove(){
        GameChecker checker = new GameChecker();
        DroidPlayer droid = new DroidPlayer();
        int playerToken = 1;


        int[][] board_1a = {
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0}
        };
        int[][] board_1b = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };


        //assertArrayEquals(droid.showWinFor(playerToken,board_1b[1]);

        int[][] board_2a = {
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0}
        };
        int[][] board_2b = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        //droid.swapBoard(board_2a);
        //checker.swapBaord(board_2a);
        //assertArrayEquals(droid.showWinFor(playerToken)[1],board_2b[1]);


    }

    @Test
    public void DroidOptsToBlockWin(){
        GameChecker checker = new GameChecker();
        DroidPlayer droid = new DroidPlayer();
        int playerToken = 1;


        int[][] board_1a = {
                {0, 0, 1},
                {2, 2, 0},
                {0, 1, 0}
        };
        int[][] board_1b = {
                {0, 0, 1},
                {2, 2, 1},
                {0, 1, 0}
        };
        //droid.swapBoard(board_1a);
        //hecker.swapBaord(board_1a);
        //assertArrayEquals(droid.showWinFor(playerToken)[1],board_1b[1]);

    }




}
