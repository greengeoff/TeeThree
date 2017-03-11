package com.glt.tictac;

import android.util.Log;

/**
 * Created by gltrager on 2/27/17.
 */

public class DroidPlayer {

    private static int playerToken = 1;
    private static int opToken = 2;

    // Droid player outsources it's game logic to a util class GameChecker
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
        playerToken = player;

        // oppenents token used when checking for a blocking move
        opToken =   playerToken == 1 ? 2 : 1;
        if(board == null) {
            Log.e("DroidTest", " no board");
            return null;
        }


        // find a win for playerToken
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[i].length; j ++){
                int temp = board[i][j];
                if(temp == 0){
                    board[i][j] = playerToken;
                    ///this shows a win
                    if (checker.isWonBy(playerToken, board)){
                        //System.out.println("found a win");
                        return board;
                    }else{
                        board[i][j] = temp;
                    }

                }

            }
        }



        // find a block of opToken
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[i].length; j ++){
                int temp = board[i][j];
                if(temp == 0){
                    board[i][j] = opToken;
                    ///this shows a win
                    if (checker.isWonBy(opToken, board)){
                        board[i][j] = playerToken;
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
            board[1][1] = playerToken;
            return board;
        }else if(board[0][1] == 0){
            board[0][1] = playerToken;
            return board;
        }else if(board[1][0] == 0){
            board[1][0] = playerToken;
            return board;
        }else if(board[1][2] == 0){
            board[1][2] = playerToken;
            return board;
        }else if(board[2][1] == 0){
            board[2][1] = playerToken;
            return board;
        }



        // take any empty spot
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[i].length; j ++){
                int temp = board[i][j];
                if(temp == 0){
                    //System.out.println("found an empty");
                    board[i][j] = playerToken;
                    return board;


                }

            }
        }

        // if win has been confirmed, this shouldn't be reached
        System.out.println("didnt find anything");
        return board;
    }
}
