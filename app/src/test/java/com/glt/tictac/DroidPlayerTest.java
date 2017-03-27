package com.glt.tictac;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gltrager on 2/27/17.
 */

public class DroidPlayerTest {
    DroidPlayer droid;

    @Before
    public void setUp() {
        droid = new DroidPlayer();
    }

    @Test
    public void DroidWinsInOneMove(){

        int playerToken = 1;


        int[][] board_1 = {
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0}
        };

        int [][] test_1 = droid.makeMoveFor(playerToken, board_1);


        int[][] board_spec = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };

        assertArrayEquals(test_1[1],board_spec[1]);

        //

        int[][] board_2 = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 0, 0}
        };
        int[][] test_2 = droid.makeMoveFor(playerToken, board_2);
        int[][] board_spec_2 = {
                {0, 0, 1},
                {0, 1, 0},
                {1, 0, 0}
        };
        assertArrayEquals(test_2[0],board_spec_2[0]);


    }

    @Test
    public void DroidOptsToBlockWin(){

        int playerToken = 1;


        int[][] board_1 = {
                {0, 0, 1},
                {2, 2, 0},
                {0, 1, 0}
        };

        int [][] test_1 = droid.makeMoveFor(playerToken, board_1);
        int[][] board_spec_1 = {
                {0, 0, 1},
                {2, 2, 1},
                {0, 1, 0}
        };

        assertArrayEquals(test_1[1],board_spec_1[1]);

    }




}
