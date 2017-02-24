package com.glt.tictac;

import android.util.Log;

/**
 * Created by gltrager on 2/24/17.
 */

public class GameChecker {

    public static final int isPlayerOTurn = 0;
    public static final int isPlayerXTurn = 1;
    public static final int XplayerWins = 2;
    public static final int OplayerWins = 3;
    public static final int TieGame = 4;


    public static final int X_mark = 1;
    public static final int O_mark = 2;
    public static final int no_mark = 0;

    public static int board[][];

    /**
     * Checks moves made and win status for a t3 board
     * @param board
     * @return the status of the current bord
     */
    public static int GameChecker(int[][] board) {
        GameChecker.board = board;
        int x_marks = 0;
        int o_marks = 0;
        int openSpots = 0;
        if (isWonBy(O_mark)) return OplayerWins;
        else if(isWonBy(X_mark)) return XplayerWins;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                switch (board[i][j]) {
                    case O_mark:
                        o_marks++;
                        break;
                    case X_mark:
                        x_marks++;
                        break;
                    case no_mark:
                        openSpots++;
                }
            }
        }
        if (openSpots == 0) return TieGame;
        if (x_marks > o_marks) return isPlayerOTurn;
        if (x_marks == o_marks) return isPlayerXTurn;
        Log.d("Game Checker", "should never be reached");
        return -1;
    }

    public static boolean isWonBy(int player){
        // check cols (iterate row position)
        return player == board[0][0]  && player == board[0][1] && player == board[0][2] ||
        player == board[1][0] && player == board[1][1] && player == board[1][2] ||
        player == board[2][0] && player == board[2][1] && player == board[2][2] ||

        // rows
        player == board[0][1] && player  == board[1][1]&& player  == board[2][1] ||
        player == board[0][2] && player == board[1][2] && player == board[2][2] ||
        player == board[0][3] && player == board[1][3] && player == board[2][3] ||

        player == board[0][0] && player == board[1][1] && player == board[2][2] ||
        player == board[2][0] && player == board[1][1] && player == board[2][2] ;

    }

    public static int findWin(int player){ return 0;}

}


