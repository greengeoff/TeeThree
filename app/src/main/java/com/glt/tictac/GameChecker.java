package com.glt.tictac;

import android.util.Log;

import com.glt.tictac.utils.BoardUtils;
import com.glt.tictac.utils.Constants;

/**
 * Determines status of a 2x2 tic-tac-toe board.
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

    /**
     * Checks moves made and win status for a t3 board
     *
     * @return the status of the current bord
     */
    public static int gameStatus(int[][] board) {
        int x_marks = 0;
        int o_marks = 0;
        int no_marks = 0;
        if (isWonBy(O_mark, board)){
            return OplayerWins;}
        else if(isWonBy(X_mark, board)){
            return XplayerWins;}

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
                        no_marks++;
                }
            }
        }

        if (no_marks == 0){
            return TieGame;}
        if (x_marks > o_marks){
            return isPlayerOTurn;}
        if (x_marks == o_marks) {
            return isPlayerXTurn;}
        Log.d("Game Checker", "should never be reached");
        return -1;
    }

    /**
     * @return int token of next players move
     */
    public static int getNextPlayerToken(int[][] board){
        int status = gameStatus(board);
        switch(status){
            case isPlayerOTurn:
                return 2;
            case isPlayerXTurn:
                return 1;
            default:
                return 0;
        }
    }

    /**
     * @return whether the provided player has a won final position
      */
    public static boolean isWonBy(int player, int[][] board){
        // check cols (iterate row position)
        return player == board[0][0]  && player == board[0][1] && player == board[0][2] ||
        player == board[1][0] && player == board[1][1] && player == board[1][2] ||
        player == board[2][0] && player == board[2][1] && player == board[2][2] ||

        // rows
        player == board[0][0] && player == board[1][0]&& player  == board[2][0] ||
        player == board[0][1] && player == board[1][1] && player == board[2][1] ||
        player == board[0][2] && player == board[1][2] && player == board[2][2] ||

        player == board[0][0] && player == board[1][1] && player == board[2][2] ||
                player == board[0][2] && player == board[1][1] && player == board[2][0];

    }

    /**
     * @return constant used for drawing the appropriate line to illustrate a winning line of tokens on the board
     */
    public static int getWinType(int player, int[][] board){
        if (player == board[0][0]  && player == board[0][1] && player == board[0][2])
            return Constants.RESULT_WIN_0_2;
        if(player == board[1][0] && player == board[1][1] && player == board[1][2]){
            return Constants.RESULT_WIN_3_5;
        }
        if(player == board[2][0] && player == board[2][1] && player == board[2][2]){
            return Constants.RESULT_WIN_6_8;
        }
        if(player == board[0][0] && player == board[1][0]&& player  == board[2][0]){
            return Constants.RESULT_WIN_0_6;
        }
        if(player == board[0][1] && player == board[1][1] && player == board[2][1]){
            return Constants.RESULT_WIN_1_7;
        }
        if(player == board[0][2] && player == board[1][2] && player == board[2][2]){
            return Constants.RESULT_WIN_2_8;
        }
        if(player == board[0][0] && player == board[1][1] && player == board[2][2]){
            return Constants.RESULT_WIN_0_8;
        }
        if(player == board[0][2] && player == board[1][1] && player == board[2][0]){
            return Constants.RESULT_WIN_6_2;
        }
        else return Constants.RESULT_TIE;
    }

}


