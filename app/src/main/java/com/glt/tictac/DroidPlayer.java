package com.glt.tictac;

import android.util.Log;

/**
 * AI player that can make a reasonable move, provided a standard 2x2 tic-tac-toe board
 */

public class DroidPlayer {

    private static int PLAYER_TOKEN = 1;
    private static int OP_TOKEN = 2;

    // Droid player outsources it's game logic to utility class GameChecker
    GameChecker checker;

    /**
     * Makes a move provided a player token ( range Int(0,1) ) and a 2D array
     * representation of a tic-tac-toe board
     * @param player integer token of next player's move on board param
     * @param board 2D int array
     * @return
     */
    public int[][] makeMoveFor(int player, int[][] board){
        if (checker == null){
            checker = new GameChecker();
        }
        PLAYER_TOKEN = player;

        // opponents token used when checking for a blocking move
        OP_TOKEN =   PLAYER_TOKEN == 1 ? 2 : 1;
        if(board == null) {
            Log.e("DroidTest", " no board");
            return null;
        }


        // find a win for PLAYER_TOKEN
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[i].length; j ++){
                int temp = board[i][j];
                if(temp == 0){
                    board[i][j] = PLAYER_TOKEN;
                    ///this shows a win
                    if (checker.isWonBy(PLAYER_TOKEN, board)){
                        //System.out.println("found a win");
                        return board;
                    }else{
                        board[i][j] = temp;
                    }

                }

            }
        }



        // find a block of OP_TOKEN
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[i].length; j ++){
                int temp = board[i][j];
                if(temp == 0){
                    board[i][j] = OP_TOKEN;
                    ///this shows a win
                    if (checker.isWonBy(OP_TOKEN, board)){
                        board[i][j] = PLAYER_TOKEN;
                        //System.out.println("found a block");
                        return board;
                    }else{
                        board[i][j] = temp;
                    }

                }

            }
        }

        // take center spot, else a side-center spot
        if(board[1][1] == 0){
            board[1][1] = PLAYER_TOKEN;
            return board;
        }else if(board[0][1] == 0){
            board[0][1] = PLAYER_TOKEN;
            return board;
        }else if(board[1][0] == 0){
            board[1][0] = PLAYER_TOKEN;
            return board;
        }else if(board[1][2] == 0){
            board[1][2] = PLAYER_TOKEN;
            return board;
        }else if(board[2][1] == 0){
            board[2][1] = PLAYER_TOKEN;
            return board;
        }



        // take any empty spot
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[i].length; j ++){
                int temp = board[i][j];
                if(temp == 0){
                    //System.out.println("found an empty");
                    board[i][j] = PLAYER_TOKEN;
                    return board;


                }

            }
        }
        // if win has been confirmed, this shouldn't be reached
        System.out.println("didn't find anything");
        return board;
    }
}
